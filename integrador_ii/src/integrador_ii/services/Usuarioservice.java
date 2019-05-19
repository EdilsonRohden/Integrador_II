package integrador_ii.services;

import java.util.ArrayList;
import java.util.List;

import integrador_ii.dao.PessoaDao;
import integrador_ii.dao.UsuarioDao;
import integrador_ii.models.Pessoa;
import integrador_ii.models.Usuario;

public class Usuarioservice {

	public List<Usuario> getUsuarios() {
		
		UsuarioDao usuarioDao = new UsuarioDao();
		PessoaDao pessoaDao = new PessoaDao();
		
		List<Usuario> usuarios = usuarioDao.getUsuarios();
		List<Usuario> usuariosCompletos = new ArrayList<Usuario>();
		for (Usuario usuario : usuarios) {
			Pessoa pessoa = pessoaDao.getPessoaById(usuario);
			usuario.setNome(pessoa.getNome());
			usuario.setCidade(pessoa.getCidade());
			usuariosCompletos.add(usuario);
		}
		
		return usuariosCompletos;
	}

	public void salvar(Integer id, String login, String senha, boolean adm) {
		
		UsuarioDao usuarioDao = new UsuarioDao();
		
		Usuario usuario = new Usuario(id, login, senha, adm);
		
		if(usuarioDao.getUsuarioById(usuario) != null) {
			usuarioDao.update(usuario);
		}else {
			usuarioDao.salvar(usuario);			
		}
			
	}

}










