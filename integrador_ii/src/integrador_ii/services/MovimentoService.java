package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.MovimentoDao;
import integrador_ii.models.Movimento;

public class MovimentoService {

	private MovimentoDao movimentoDao = new MovimentoDao();
	
	public List<Movimento> getMovimentos() {
		
		List<Movimento> movimentos =  movimentoDao.getMovimentos();
		
		return movimentos;
	}

}
