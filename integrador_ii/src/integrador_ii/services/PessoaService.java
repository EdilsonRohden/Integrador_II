package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.PessoaDao;
import integrador_ii.models.Cidade;
import integrador_ii.models.Pessoa;

public class PessoaService {

	private PessoaDao pessoaDao = new PessoaDao();
	
	public void salvar(String nome, String id, Integer codIbge) {
		Pessoa pessoa = null;
		if(!id.isEmpty()) {			
			Integer idPessoa = Integer.parseInt(id);
			pessoa = new Pessoa(idPessoa, new Cidade(codIbge), nome);
		}else {
			pessoa = new Pessoa(null, new Cidade(codIbge), nome);
		}
		if ((pessoa.getId() != null) && pessoaDao.getPessoaById(pessoa) != null) {
			pessoaDao.update(pessoa);
		}else{
			pessoaDao.salvar(pessoa);
		}
		
		
	}

	public List<Pessoa> getPessoas() {
		
		List<Pessoa> pessoas = pessoaDao.getPessoas();
		
		return pessoas;
	}

	public Pessoa getPessoa(Integer id) {
		
		Pessoa pessoa = pessoaDao.getPessoaById(new Pessoa(id));
		
		return pessoa;
	}

}
