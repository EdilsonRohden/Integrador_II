package integrador_ii.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import integrador_ii.models.Cliente;
import integrador_ii.models.Conta;
import integrador_ii.models.Movimento;

public class MovimentoDao extends Dao{

	public List<Movimento> getMovimentos() {
		conectar();
		
		List<Movimento> movimentos = new ArrayList<Movimento>();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM movimentacao";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if(resultSet != null) {
				while(resultSet.next()) {
					
					int idPessoa = resultSet.getInt("idPessoa");
					int idConta = resultSet.getInt("idConta");
					int idMovimento = resultSet.getInt("idMovimentacao");
					Date dataMovimento = resultSet.getDate("datamovimentacao");
					double valor = resultSet.getDouble("valormovimentacao");
					
					
					movimentos.add(new Movimento(
							new Cliente(idPessoa),
							new Conta(idConta),
							idMovimento,
							dataMovimento,
							valor
							));
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			desconectar();
		}
		
		
		return movimentos;
	}

}







