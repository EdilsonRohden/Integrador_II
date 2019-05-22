package integrador_ii.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Estado;

public class EstadoDao extends Dao{

	public void salvar(Estado estado) {
		conectar();
		
		try {
			Statement stmt = connection.createStatement();
			
			String sql = "INSERT INTO estado (uf, nome) VALUES (" +
			estado.getSigla() + "," +
			estado.getNome() + ");";
			
			stmt.execute(sql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
	}
	
	public Estado getEstadoBySigla(Estado param) {
		conectar();
		Estado estado = new Estado();
		
		try {
			Statement stmt = connection.createStatement();
			
			 String sql = "SELECT * FROM estado WHERE uf = '" + param.getSigla() + "';";
			
			 			 
			ResultSet result = stmt.executeQuery(sql);
			
			if( result != null) {
				while (result.next()) {
					estado.setNome(result.getString("nome"));
					estado.setSigla(result.getString("uf"));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		return estado;
	}
	
	public List<Estado> getEstados() { 
		conectar();
		
		List<Estado> estados = new ArrayList<Estado>();
		
		try {
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM estado";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if( resultSet != null) {
				
				while (resultSet.next()) {
					Estado estado = new Estado(resultSet.getString("uf"), resultSet.getString("nome"));
					
					estados.add(estado);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		return estados;
	}

	public void update(Estado estado) {
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "UPDATE estado SET uf = " +
					estado.getSigla() + 
					", nome = " + estado.getNome() +
					" WHERE uf = " + estado.getSigla();
			
			stmt.execute(sql);			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();			
		}
		
	}	
}
