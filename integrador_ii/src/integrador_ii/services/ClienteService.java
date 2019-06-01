package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.ClienteDao;
import integrador_ii.dao.PessoaDao;
import integrador_ii.models.Cliente;
import integrador_ii.models.Pessoa;

public class ClienteService {

	public void salvar(String idPessoa, String bairro, String cep, String fone, String email) {
		PessoaDao pessoadao = new PessoaDao();
		ClienteDao clienteDao = new ClienteDao();
				
		Pessoa pessoa = pessoadao.getPessoaById(new Pessoa(Integer.parseInt(idPessoa)));
		Cliente cliente = new Cliente(pessoa.getId(), pessoa.getNome());
		
		cliente.setBairro(bairro);
		cliente.setCep(cep);
		cliente.setFone(fone);
		cliente.setEmail(email);
		if (clienteDao.getClienteById(cliente) != null) {
			clienteDao.update(cliente);
		}else {			
			clienteDao.salvar(cliente);
		}
		
	}

	public List<Cliente> getClientes() {
		
		ClienteDao clienteDao = new ClienteDao();
		
		List<Cliente> clientes = clienteDao.getClientes();
		
		
		return clientes;
	}

}
