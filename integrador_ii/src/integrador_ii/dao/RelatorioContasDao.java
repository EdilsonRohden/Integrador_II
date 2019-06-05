package integrador_ii.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import integrador_ii.models.PlanoDeConta;

public class RelatorioContasDao extends Dao{
	
	
	private	final String PLANO_DE_CONTAS = "SELECT p.id || '.' || p.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores\n" + 
			"FROM \n" + 
			"	(SELECT * FROM primeiro_nivel WHERE id = 1) AS p \n" + 
			"	JOIN \n" + 
			"	conta c\n" + 
			"	ON p.id = c.id_primeiro_nivel\n" + 
			"	JOIN\n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON m.id_conta = c.id_conta\n" + 
			"GROUP BY 1\n" + 
			"\n" + 
			"UNION ALL\n" + 
			"\n" + 
			"SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || ' ' || c.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores\n" + 
			"FROM \n" + 
			"	(SELECT * FROM conta WHERE id_primeiro_nivel = 1) AS c \n" + 
			"	JOIN \n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON c.id_conta = m.id_conta\n" + 
			"GROUP BY 1\n" + 
			"\n" + 
			"UNION ALL\n" + 
			"\n" + 
			"SELECT p.id || '.' || p.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores\n" + 
			"FROM \n" + 
			"	(SELECT * FROM primeiro_nivel WHERE id = 2) AS p \n" + 
			"	JOIN conta c ON p.id = c.id_primeiro_nivel\n" + 
			"JOIN \n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON m.id_conta = c.id_conta\n" + 
			"GROUP BY 1\n" + 
			"\n" + 
			"UNION ALL\n" + 
			"\n" + 
			"SELECT c.id_primeiro_nivel || '.' || c.id_segundo_nivel || ' ' || c.descricao AS tipo_de_conta, SUM(m.valor_movimentacao) AS totalizadores\n" + 
			"FROM \n" + 
			"	(SELECT * FROM conta WHERE id_primeiro_nivel = 2) AS c \n" + 
			"	JOIN \n" + 
			"	(SELECT * FROM movimentacao WHERE id_pessoa = ? AND data_movimentacao BETWEEN ? AND ?) AS m \n" + 
			"	ON c.id_conta = m.id_conta\n" + 
			"GROUP BY 1\n"; 
	
	
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
			ps.setInt(10, idCliente);
			ps.setDate(11, dataIni);
			ps.setDate(12, dataFin);

			
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
