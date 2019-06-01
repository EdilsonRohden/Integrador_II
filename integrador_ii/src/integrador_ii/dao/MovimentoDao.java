package integrador_ii.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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

	public int salvar(Cliente cliente, Movimento movimento) {
		conectar();
		int id_movimentacao = 0;
		
		try {
			
			String sql = "INSERT INTO movimentacao "
					+ "(id_pessoa, id_conta, data_movimentacao, valor_movimentacao, descricao, excluido)"
					+ " VALUES (?, ?, ?, ?, ?, false)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, cliente.getId());
			ps.setInt(2, movimento.getConta().getId());
			ps.setDate(3, movimento.getDataMovimento());
			ps.setDouble(4, movimento.getValor());
			ps.setString(5, movimento.getDescricao());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			id_movimentacao =  rs.getInt("id_movimentacao");
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			desconectar();
		}
		return id_movimentacao;
	}

}







