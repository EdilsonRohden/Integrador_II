package integrador_ii.dao;

import java.sql.PreparedStatement;

import integrador_ii.models.Usuario;

public class MovimentoAlteracaoDao extends Dao{

	public void inserirAlteracao(Usuario usuario, int id_movimentacao, String tipo) {
		conectar();
		
		try {
			
			String sql = "INSERT INTO movimento_alteracao "
					+ "(id_movimentacao, id_pessoa, tipo_alteracao, data_alteracao)"
					+ "VALUES (?, ?, ?, CURRENT_DATE)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id_movimentacao);
			ps.setInt(2, usuario.getId());
			ps.setString(3, tipo);
			
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
	}

}
