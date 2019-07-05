package integrador_ii.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Conta;

public class ContaDao extends Dao{
	public List<Conta> getContasPrimeiroNivel(){
		conectar();
		List<Conta> contas = null;
		try {
			String sql = "SELECT * FROM primeiro_nivel";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet !=  null) {
				contas = new ArrayList<Conta>();
				while(resultSet.next()) {
					contas.add(new Conta(resultSet.getInt("id"),
							resultSet.getString("descricao")
							)
					);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		return contas;
	}

	public List<Conta> getContas() {
		conectar();
		
		List<Conta> contas = new ArrayList<Conta>();
		try {
			
			Statement stmt = connection.createStatement();
			String sql = "SELECT conta.*, primeiro_nivel.descricao AS descricaoP FROM primeiro_nivel LEFT JOIN conta ON conta.id_primeiro_nivel = primeiro_nivel.id";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet != null) {
				while(resultSet.next()) {
					contas.add(new Conta(
							resultSet.getInt("id_conta"),
							resultSet.getInt("id_primeiro_nivel"),
							resultSet.getInt("id_segundo_nivel"),
							resultSet.getString("descricaoP"),
							resultSet.getString("descricao")
							));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		
		return contas;
	}

	public Conta getContaById(Conta conta) {
		conectar();
		Conta resultado = new Conta();
		
		try {
			
			Statement stmt = connection.createStatement();
			String sql = "SELECT id_conta, id, id_segundo_nivel, primeiro_nivel.descricao as p_desc, conta.descricao "
					+ "FROM conta JOIN primeiro_nivel ON conta.id_primeiro_nivel = primeiro_nivel.id WHERE id_conta = " + conta.getId();
			
			ResultSet result = stmt.executeQuery(sql);
			
			if(result != null) {
				result.next();
				resultado.setId(result.getInt("id_conta"));
				resultado.setIdP(result.getInt("id"));
				resultado.setIdS(result.getInt("id_segundo_nivel"));
				resultado.setDescricaoP(result.getString("p_desc"));
				resultado.setDescricaoS(result.getString("descricao"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		
		return resultado;
	}

	public void update(Conta conta) {
		// TODO Auto-generated method stub
		
	}

	public void salvar(Conta conta) {
		
		conectar();
		
		try {
			
			String sql = "INSERT INTO conta (id_primeiro_nivel, id_segundo_nivel, descricao) VALUES (?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, conta.getIdP());
			ps.setInt(2, conta.getIdS());
			ps.setString(3, conta.getDescricaoS());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
	}

}















