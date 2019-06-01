package integrador_ii.services;

import integrador_ii.dao.UsuarioDao;
import integrador_ii.models.Usuario;

public class LoginService {

	private UsuarioDao usuarioDao = new UsuarioDao();
	
	public Usuario autenticar(String login, String password) {
		
		Usuario usuario = usuarioDao.autenticarERetornar(login, password);
		
		return usuario;
	}
	
	

}
