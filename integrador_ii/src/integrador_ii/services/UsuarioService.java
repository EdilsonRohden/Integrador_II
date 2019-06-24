package integrador_ii.services;

import java.util.ArrayList;
import java.util.List;

import integrador_ii.dao.PessoaDao;
import integrador_ii.dao.UsuarioDao;
import integrador_ii.models.Pessoa;
import integrador_ii.models.Usuario;

public class UsuarioService {
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	private PessoaDao pessoaDao = new PessoaDao();

	public List<Usuario> getUsuarios() {
		
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
		
		Usuario usuario = new Usuario(id, login, senha, adm);
		
		if(usuarioDao.getUsuarioById(usuario) != null) {
			usuarioDao.update(usuario);
		}else {
			usuarioDao.salvar(usuario);			
		}
			
	}

	public Usuario getUsuarioById(Integer id) {
		Usuario usuario = null;
		
		usuario = usuarioDao.getUsuarioById(new Usuario(id));
		
		return usuario;
	}

}










