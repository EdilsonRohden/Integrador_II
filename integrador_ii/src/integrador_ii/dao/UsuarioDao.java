package integrador_ii.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Usuario;

public class UsuarioDao extends Dao{
	
	public void update(Usuario usuario) {
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "UPDATE usuario SET " +
					"login = '" + usuario.getLogin() + "'," +
					"senha = '" + usuario.getSenha() + "'," +
					"adm = '" + usuario.isAdm() +
					"' WHERE id_pessoa = " + usuario.getId();
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
	}

	public void salvar(Usuario usuario) {
		conectar();
		try {
			
			String sql = "INSERT INTO usuario (id_pessoa, login, senha, adm) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, usuario.getId());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setBoolean(4, usuario.isAdm());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			desconectar();
		}
		
	}

	public List<Usuario> getUsuarios() {
		
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM usuario;";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			if (resultSet != null) {
				List<Usuario> usuarios = new ArrayList<Usuario>();
				while(resultSet.next()) {
					Integer id = resultSet.getInt("id_pessoa");
					String login = resultSet.getString("login");
					String senha = resultSet.getString("senha");
					boolean adm = resultSet.getBoolean("adm");
					
					usuarios.add(new Usuario(id, login, senha, adm));
				
				}
				return usuarios;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			desconectar();
		}
		
		
		return null;
	}

	public Usuario getUsuarioById(Usuario user) {
		conectar();
		Usuario usuario = null; 
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM usuario JOIN pessoa ON usuario.id_pessoa = pessoa.id_pessoa WHERE  usuario.id_pessoa = " + user.getId();
			
			ResultSet result = stmt.executeQuery(sql);
			
			if (result != null) {
				if(result.next()) {					
					usuario = new Usuario(user.getId());
					usuario.setNome(result.getString("nome"));
					usuario.setAdm(result.getBoolean("adm"));
					usuario.setLogin(result.getString("login"));
					usuario.setSenha(result.getString("senha"));
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}

		return usuario;
	}

	public Usuario autenticarERetornar(String login, String password) {
		conectar();
		Usuario usuario = null;
		try {
			
			String sql = "SELECT * FROM usuario WHERE login=? AND senha=?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet result = ps.executeQuery();
			
			if(result != null) {
				if(result.next()) {
					usuario = new Usuario();
					usuario.setId(result.getInt("id_pessoa"));
					usuario.setAdm(result.getBoolean("adm"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		return usuario;
	}

}























