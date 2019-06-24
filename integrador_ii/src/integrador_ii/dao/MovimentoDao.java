package integrador_ii.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Cliente;
import integrador_ii.models.Conta;
import integrador_ii.models.Movimento;
import integrador_ii.models.MovimentoAlteracao;
import integrador_ii.models.Usuario;

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

	public List<Movimento> getRelatorio() {
		
		conectar();
		List<Movimento> movimentos = new ArrayList<Movimento>();
		
		try {
			String sql = "SELECT ma.tipo_alteracao, ma.data_alteracao, p.nome as usuario,\n" + 
					"	m.valor_movimentacao, m.data_movimentacao\n" + 
					"FROM \n" + 
					"	movimento_alteracao ma JOIN movimentacao m \n" + 
					"	ON ma.id_movimentacao = m.id_movimentacao\n" + 
					"	JOIN pessoa p \n" + 
					"	ON p.id_pessoa = ma.id_pessoa";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				while( rs.next() ) {
					
					Movimento mv = new Movimento();
					MovimentoAlteracao mvAlt = new MovimentoAlteracao();
					mv.setDataMovimento(rs.getDate("data_movimentacao"));
					mv.setValor(rs.getDouble("valor_movimentacao"));
					mvAlt.setAutor(new Usuario(rs.getString("usuario")));
					mvAlt.setData(rs.getDate("data_alteracao"));
					mvAlt.setTipo(rs.getString("tipo_alteracao"));
					
					mv.addMovimentoAlteracao(mvAlt);
					
					movimentos.add(mv);
					
				}
					
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		return movimentos;
	}

	public List<Movimento> getRelatorio(int idMov, Integer idUsuario) {
		
		conectar();
		List<Movimento> movimentos = new ArrayList<Movimento>();
		
		try {
			String sql = "SELECT ma.tipo_alteracao, ma.data_alteracao, p.nome as usuario,\n" + 
					"	m.valor_movimentacao, m.data_movimentacao\n" + 
					"FROM \n" + 
					"	movimento_alteracao ma JOIN movimentacao m \n" + 
					"	ON ma.id_movimentacao = m.id_movimentacao\n" + 
					"	JOIN pessoa p \n" + 
					"	ON p.id_pessoa = ma.id_pessoa " +
					"WHERE ma.id_pessoa = ? and\n" + 
					"	m.id_movimentacao = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, idUsuario);
			ps.setInt(2, idMov);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				while( rs.next() ) {
					
					Movimento mv = new Movimento();
					MovimentoAlteracao mvAlt = new MovimentoAlteracao();
					mv.setDataMovimento(rs.getDate("data_movimentacao"));
					mv.setValor(rs.getDouble("valor_movimentacao"));
					mvAlt.setAutor(new Usuario(rs.getString("usuario")));
					mvAlt.setData(rs.getDate("data_alteracao"));
					mvAlt.setTipo(rs.getString("tipo_alteracao"));
					
					mv.addMovimentoAlteracao(mvAlt);
					
					movimentos.add(mv);
					
				}
					
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		return movimentos;
	}

	public List<Movimento> getRelatorio(int idMov) {
		
		conectar();
		List<Movimento> movimentos = new ArrayList<Movimento>();
		
		try {
			String sql = "SELECT ma.tipo_alteracao, ma.data_alteracao, p.nome as usuario,\n" + 
					"	m.valor_movimentacao, m.data_movimentacao\n" + 
					"FROM \n" + 
					"	movimento_alteracao ma JOIN movimentacao m \n" + 
					"	ON ma.id_movimentacao = m.id_movimentacao\n" + 
					"	JOIN pessoa p \n" + 
					"	ON p.id_pessoa = ma.id_pessoa " +
					"WHERE m.id_movimentacao = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, idMov);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				while( rs.next() ) {
					
					Movimento mv = new Movimento();
					MovimentoAlteracao mvAlt = new MovimentoAlteracao();
					mv.setDataMovimento(rs.getDate("data_movimentacao"));
					mv.setValor(rs.getDouble("valor_movimentacao"));
					mvAlt.setAutor(new Usuario(rs.getString("usuario")));
					mvAlt.setData(rs.getDate("data_alteracao"));
					mvAlt.setTipo(rs.getString("tipo_alteracao"));
					
					mv.addMovimentoAlteracao(mvAlt);
					
					movimentos.add(mv);
					
				}
					
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		return movimentos;
	}
	
	public List<Movimento> getRelatorio(Integer idUsuario) {
		
		conectar();
		List<Movimento> movimentos = new ArrayList<Movimento>();
		
		try {
			String sql = "SELECT ma.tipo_alteracao, ma.data_alteracao, p.nome as usuario,\n" + 
					"	m.valor_movimentacao, m.data_movimentacao\n" + 
					"FROM \n" + 
					"	movimento_alteracao ma JOIN movimentacao m \n" + 
					"	ON ma.id_movimentacao = m.id_movimentacao\n" + 
					"	JOIN pessoa p \n" + 
					"	ON p.id_pessoa = ma.id_pessoa " +
					"WHERE ma.id_pessoa = ?\n";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs != null) {
				while( rs.next() ) {
					
					Movimento mv = new Movimento();
					MovimentoAlteracao mvAlt = new MovimentoAlteracao();
					mv.setDataMovimento(rs.getDate("data_movimentacao"));
					mv.setValor(rs.getDouble("valor_movimentacao"));
					mvAlt.setAutor(new Usuario(rs.getString("usuario")));
					mvAlt.setData(rs.getDate("data_alteracao"));
					mvAlt.setTipo(rs.getString("tipo_alteracao"));
					
					mv.addMovimentoAlteracao(mvAlt);
					
					movimentos.add(mv);
					
				}
					
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		return movimentos;
	}
	
}









