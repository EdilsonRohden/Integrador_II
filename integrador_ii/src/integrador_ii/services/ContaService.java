package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.ContaDao;
import integrador_ii.models.Conta;

public class ContaService {

	private ContaDao contaDao = new ContaDao();
	
	public List<Conta> getContasPrimeiroNivel() {
		
		return contaDao.getContasPrimeiroNivel();
	}

	public void salvar(String id, String idP, String idS, String descricaoS) {
	
		try {
			Integer idPNum = Integer.parseInt(idP);
			Integer idSNum = Integer.parseInt(idS);
			
			Conta conta = new Conta(idPNum, idSNum, descricaoS);
			
			contaDao.salvar(conta);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Conta> getContas() {
		return contaDao.getContas();
	}

}
