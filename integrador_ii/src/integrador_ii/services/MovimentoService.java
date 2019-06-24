package integrador_ii.services;

import java.util.List;

import integrador_ii.dao.MovimentoAlteracaoDao;
import integrador_ii.dao.MovimentoDao;
import integrador_ii.models.Cliente;
import integrador_ii.models.Movimento;
import integrador_ii.view.TelaPrincipal;

public class MovimentoService {

	private MovimentoDao movimentoDao = new MovimentoDao();
	private MovimentoAlteracaoDao movimentoAlteracaoDao = new MovimentoAlteracaoDao();
	
	public List<Movimento> getMovimentos() {
		
		List<Movimento> movimentos =  movimentoDao.getMovimentos();
		
		return movimentos;
	}

	public void salvar(Cliente cliente, Movimento movimento) {
		int id_movimentacao = movimentoDao.salvar(cliente, movimento);
		if (id_movimentacao != 0) {			
			movimentoAlteracaoDao.inserirAlteracao(TelaPrincipal.usuario, id_movimentacao, "Inserção");
		}
		
	}

	public List<Movimento> relatorioMovimentacao(String idMovimentacao, Integer idUsuario) {

		
		if( !idMovimentacao.isEmpty() && idUsuario != null ) {
			try {
				int idMov = Integer.parseInt(idMovimentacao);
				return movimentoDao.getRelatorio(idMov, idUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if( !idMovimentacao.isEmpty() && idUsuario == null ) {
			try {
				int idMov = Integer.parseInt(idMovimentacao);
				return movimentoDao.getRelatorio(idMov);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if( idMovimentacao.isEmpty() && idUsuario != null ) {
			try {
				return movimentoDao.getRelatorio(idUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		return movimentoDao.getRelatorio();
	}

}
