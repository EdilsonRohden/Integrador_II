package integrador_ii.services;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import integrador_ii.dao.PessoaDao;
import integrador_ii.models.Cidade;
import integrador_ii.models.Pessoa;

public class PessoaService {

	public void salvar(String nome, String id, Integer codIbge) {
		PessoaDao pessoaDao = new PessoaDao();
		
		if (nome.isEmpty() || id.isEmpty()) {
			return;
		}
		
		Integer idPessoa = Integer.parseInt(id);
		Pessoa pessoa = new Pessoa(idPessoa, new Cidade(codIbge), nome);
		if (pessoaDao.getPessoaById(pessoa) != null) {
			pessoaDao.update(pessoa);
		}else{
			pessoaDao.salvar(pessoa);
		}
		
		
	}

	public List<Pessoa> getPessoas() {
		PessoaDao pessoaDao = new PessoaDao();
		
		List<Pessoa> pessoas = pessoaDao.getPessoas();
		
		return pessoas;
	}

}
