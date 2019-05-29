package integrador_ii.services;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import integrador_ii.dao.CidadeDao;
import integrador_ii.dao.EstadoDao;
import integrador_ii.models.Cidade;
import integrador_ii.models.Estado;

public class CidadeService {

	public DefaultComboBoxModel<String> getSiglas() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		EstadoDao estadoDao = new EstadoDao();
		List<Estado> estados = estadoDao.getEstados();
		
		for (Estado estado : estados) {
			model.addElement(estado.getSigla());
		}
		
		return model;
	}

	public boolean salvar(String nome, String codigoIbge, String sigla) {
		try {
			Integer cod = Integer.parseInt(codigoIbge);			
			if (nome.isEmpty() || sigla.isEmpty()) {
				return false;
			}
			CidadeDao cidadeDao = new CidadeDao();
			if (cidadeDao.getCidadeByCodigoIbge(new Cidade(cod)) != null) {
				cidadeDao.update(new Cidade(cod, nome, new Estado(sigla)));
			}else {				
				cidadeDao.salvar(new Cidade(cod, nome, new Estado(sigla)));
			}
			
			
		}catch (Exception e) {
			return false;
		}
		
		
		
		return true;
	}

	public DefaultComboBoxModel<String> getCidadesComboBox() {
		CidadeDao cidadeDao = new CidadeDao();
		List<Cidade> cidades = cidadeDao.getCidades();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for (Cidade cidade : cidades) {
			model.addElement(cidade.getCodigoIbge().toString() + "-" + cidade.getNome());;
		}
		
		return model;
	}
	
	

}
