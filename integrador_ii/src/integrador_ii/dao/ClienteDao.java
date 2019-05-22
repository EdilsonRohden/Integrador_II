package integrador_ii.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Cliente;

public class ClienteDao extends Dao{

	public Cliente getClienteById(Cliente cliente) {
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM cliente WHERE id_pessoa = " + cliente.getId();
			
			ResultSet result = stmt.executeQuery(sql);
			
			if(result != null) {
				cliente.setId(result.getInt("id_pessoa"));
				cliente.setBairro(result.getString("bairro"));
				cliente.setCep(result.getString("cep"));
				cliente.setFone(result.getString("fone"));
				cliente.setEmail(result.getString("email"));

				return cliente;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		
		return null;
	}

	public void update(Cliente cliente) {

		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "UPDATE cliente SET " + 
					"bairro = " + cliente.getBairro() +
					", cep = " + cliente.getCep() +
					", fone = " + cliente.getFone() +
					", email = " + cliente.getEmail() + 
					" WHERE id_pessoa = " + cliente.getId() + ";";
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
	}

	public void salvar(Cliente cliente) {
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "INSERT INTO cliente (id_pessoa, bairro, cep, fone, email) VALUES (" + 
					cliente.getId() + ", " +
					cliente.getBairro() + ", " +
					cliente.getCep() + ", " +
					cliente.getFone() + ", " +
					cliente.getEmail() + ");";
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
				
		
	}

	public List<Cliente> getClientes() {
		conectar();
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM clientes JOIN pessoa ON pessoa.id_pessoa = cliente.id_pessoa ";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if (resultSet != null) {
				while(resultSet.next()) {
					Cliente cliente = new Cliente();
					cliente.setNome(resultSet.getString("nome"));
					cliente.setId(resultSet.getInt("id_pessoa"));
					cliente.setBairro(resultSet.getString("bairro"));
					cliente.setCep(resultSet.getString("cep"));
					cliente.setFone(resultSet.getString("fone"));
					cliente.setEmail(resultSet.getString("email"));
										
					clientes.add(cliente);
					
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		
		return clientes;
	}

}






























