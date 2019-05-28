package integrador_ii.dao;

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
					"' WHERE idpessoa = " + usuario.getId();
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
	}

	public void salvar(Usuario usuario) {
		conectar();
		String sql = null;
		try {
			
			Statement stmt = connection.createStatement();
			
			if (usuario.getId() != null) {
				sql = "UPDATE usuario SET " +
						"login = '" + usuario.getLogin() + "', " +
						"senha = '" + usuario.getSenha() + "'," +
						"adm = '" + usuario.isAdm() +
						"' WHERE idpessoa = " + usuario.getId();
				stmt.execute(sql);
			}else {
				sql = "INSERT INTO usuario( login, senha, adm) VALUES ('"+
						usuario.getLogin()+"','"+usuario.getSenha()+"','"+usuario.isAdm()+"');";
				
				stmt.execute(sql);
			}
			
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
					Integer id = resultSet.getInt("idpessoa");
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
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM usuario WHERE idpessoa = " + user.getId();
			
			ResultSet result = stmt.executeQuery(sql);
			
			if (result != null) {
				Usuario usuario = new Usuario(user.getId());
				usuario.setAdm(result.getBoolean("adm"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}

		return null;
	}

}























