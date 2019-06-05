package integrador_ii.services;

import java.sql.Date;
import java.util.ArrayList;

import integrador_ii.dao.RelatorioContasDao;
import integrador_ii.models.PlanoDeConta;

public class PlanoDeContaService {

	private RelatorioContasDao planoDeConta = new RelatorioContasDao();
	
	public ArrayList<PlanoDeConta> getRelatorio(int idCliente, Date dataIni, Date dataFin) {
		
		ArrayList<PlanoDeConta> dados =  planoDeConta.getRelatorio(idCliente, dataIni, dataFin);
		
		return dados;
	}

}
