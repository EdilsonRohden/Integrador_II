package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.ClienteDao;
import integrador_ii.dao.PessoaDao;
import integrador_ii.models.Cliente;
import integrador_ii.models.Pessoa;

public class ClienteService {

	private PessoaDao pessoadao = new PessoaDao();
	private ClienteDao clienteDao = new ClienteDao();
	
	public void salvar(String idPessoa, String bairro, String cep, String fone, String email, boolean excluido) {
		
		ClienteDao clienteDao = new ClienteDao();
				
		Pessoa pessoa = pessoadao.getPessoaById(new Pessoa(Integer.parseInt(idPessoa)));
		Cliente cliente = new Cliente(pessoa.getId(), pessoa.getNome());
		
		cliente.setBairro(bairro);
		cliente.setCep(cep);
		cliente.setFone(fone);
		cliente.setEmail(email);
		cliente.setExcluido(excluido);
		if (clienteDao.getClienteById(cliente) != null) {
			clienteDao.update(cliente);
		}else {			
			clienteDao.salvar(cliente);
		}
		
	}

	public List<Cliente> getClientes() {
		
		List<Cliente> clientes = clienteDao.getClientes();
		
		
		return clientes;
	}

	public Cliente getCliente(Integer id) {
		
		Cliente cliente = clienteDao.getClienteById(new Cliente(id));
		
		return cliente;
	}

}
