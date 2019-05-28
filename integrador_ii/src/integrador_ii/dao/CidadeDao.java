package integrador_ii.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Cidade;
import integrador_ii.models.Estado;

public class CidadeDao extends Dao{

	public void salvar(Cidade cidade) {
		conectar();
		
		try {
			Statement stmt = connection.createStatement();
			
			String sql = "INSERT INTO cidade (uf, id_ibge, nome) VALUES ('"+
			cidade.getEstado().getSigla() + "', '" +
					cidade.getCodigoIbge() + "', '" +
					cidade.getNome() + "')";
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
	}
	
	public List<Cidade> getCidades() {
		conectar();
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		try {
			
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM cidade";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			if( resultSet != null) {
				while(resultSet.next()) {
					Cidade cidade = new Cidade();
					cidade.setCodigoIbge(resultSet.getInt("id_ibge"));
					cidade.setNome(resultSet.getString("nome"));
					cidade.setEstado(new Estado(resultSet.getString("uf")));
					
					cidades.add(cidade);
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		return cidades;
	}
	
	public Cidade getCidadeByCodigoIbge(Cidade cidade) {
		conectar();
		Cidade result = null;
		EstadoDao estadoDao = new EstadoDao();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM cidade WHERE id_ibge = " + cidade.getCodigoIbge();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while (resultSet.next()) {
				result = new Cidade(resultSet.getInt("id_ibge"),
						resultSet.getString("nome"),
						estadoDao.getEstadoBySigla(new Estado(resultSet.getString("uf")))
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		desconectar();
		return result;
	}

	public void update(Cidade cidade) {
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "UPDATE cidade SET uf = '" + cidade.getEstado().getSigla() +
					"', id_ibge = " + cidade.getCodigoIbge() +
					", nome = '" + cidade.getNome() +
					"' WHERE id_ibge = '" + cidade.getCodigoIbge() + "';";
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
	}
	
	
}









