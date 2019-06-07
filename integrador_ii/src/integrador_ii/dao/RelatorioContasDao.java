package integrador_ii.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import integrador_ii.models.PlanoDeConta;

public class RelatorioContasDao extends Dao{
	
	
	private	final String PLANO_DE_CONTAS = "SELECT tipo_de_conta, totalizadores FROM (\n" + 
			"SELECT p.id || '.' || p.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores,\n" + 
			"	p.id AS p, 0 AS s, 0 AS m \n" + 
			"FROM \n" + 
			"	primeiro_nivel p \n" + 
			"	JOIN \n" + 
			"	conta c\n" + 
			"	ON p.id = c.id_primeiro_nivel\n" + 
			"	JOIN\n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON m.id_conta = c.id_conta\n" + 
			"GROUP BY 3, 4,5 \n" + 
			"\n" + 
			"UNION\n" + 
			"\n" + 
			"SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || ' ' || c.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores, \n" + 
			"	c.id_primeiro_nivel AS p, c.id_segundo_nivel AS s, 0 AS m\n" + 
			"FROM \n" + 
			"	conta c \n" + 
			"	JOIN \n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON c.id_conta = m.id_conta\n" + 
			"GROUP BY 1,3, 4, 5\n" + 
			"\n" + 
			"UNION\n" + 
			"\n" + 
			"SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || '.' || m.id_movimentacao || ' ' || m.descricao AS tipo_de_conta, m.valor_movimentacao AS totalizadores,\n" + 
			"	c.id_primeiro_nivel AS p, c.id_segundo_nivel AS s, m.id_movimentacao AS m\n" + 
			"FROM \n" + 
			"	conta c \n" + 
			"	JOIN \n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON c.id_conta = m.id_conta\n" + 
			"ORDER BY 1, 2, 4, 5\n" + 
			") AS t1\n" + 
			"ORDER BY p, s, m\n"; 
	
	
	public ArrayList<PlanoDeConta> getRelatorio(int idCliente, Date dataIni, Date dataFin) {
		
		ArrayList<PlanoDeConta> dados = null;
		
		conectar();
		
		try {
			
			
			PreparedStatement ps = connection.prepareStatement(PLANO_DE_CONTAS);
			
			ps.setInt(1, idCliente);
			ps.setDate(2, dataIni);
			ps.setDate(3, dataFin);
			ps.setInt(4, idCliente);
			ps.setDate(5, dataIni);
			ps.setDate(6, dataFin);
			ps.setInt(7, idCliente);
			ps.setDate(8, dataIni);
			ps.setDate(9, dataFin);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet != null) {
				dados = new ArrayList<PlanoDeConta>();
				while(resultSet.next()) {
					dados.add(new PlanoDeConta(
						resultSet.getString("tipo_de_conta"),
						resultSet.getDouble("totalizadores")
						));
				}
			}
			
		} catch (Exception e) {
			
		}finally {
			desconectar();	
		}
		
		
		return dados;
	}
	
}
