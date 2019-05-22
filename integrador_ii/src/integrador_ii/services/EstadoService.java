package integrador_ii.services;

import integrador_ii.dao.EstadoDao;
import integrador_ii.models.Estado;

public class EstadoService {
	
	public boolean salvar(String nome, String sigla) {
		
		if(sigla.isEmpty() || sigla.length() != 2 || nome.isEmpty()) {
			return false;
		}
		
		EstadoDao estadoDao = new EstadoDao();
		Estado estado = new Estado(sigla, nome);
		if (estadoDao.getEstadoBySigla(estado) != null) {
			estadoDao.update(estado);
			System.out.println("UPDATE");
		}else {
			estadoDao.salvar(estado);			
			System.out.println("INSERT");
		}
		
		return true;
	}

}
