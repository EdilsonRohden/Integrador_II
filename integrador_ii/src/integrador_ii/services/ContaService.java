package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.ContaDao;
import integrador_ii.models.Conta;

public class ContaService {

	private ContaDao contaDao = new ContaDao();
	
	public List<Conta> getContas() {
		
		return contaDao.getContas();
	}

	public void salvar(String id, String idP, String idS, String descricaoS) {
		
		if (!id.isEmpty()) {
			try {
				Integer idNum = Integer.parseInt(id);
				Integer idPNum = Integer.parseInt(idP);
				Integer idSNum = Integer.parseInt(idS);
				
				Conta conta = new Conta(idNum, idPNum, idSNum, descricaoS);
				
				Conta contaId = contaDao.getContaById(new Conta(idNum));
				if (contaId != null) {
					contaDao.update(conta);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		contaDao.salvar(new Conta());
		
	}

}
