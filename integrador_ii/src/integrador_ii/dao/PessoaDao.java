package integrador_ii.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Cidade;
import integrador_ii.models.Pessoa;

public class PessoaDao extends Dao {

	public void salvar(Pessoa pessoa) {
		conectar();

		try {
			
			String sql = "INSERT INTO pessoa (id_ibge, nome) VALUES ( ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, pessoa.getCidade().getCodigoIbge());
			ps.setString(2, pessoa.getNome());
			
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		desconectar();
	}

	public Pessoa getPessoaById(Pessoa pessoa) {
		conectar();

		String sql = null;
		CidadeDao cidadeDao = new CidadeDao();
		Pessoa result = null;

		try {
			
			Statement stmt = connection.createStatement();
			sql = "SELECT * FROM pessoa WHERE id_pessoa = " + pessoa.getId() + ";";
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet.next()) {			
				int cdIbge = resultSet.getInt("id_ibge");
				Cidade cidade = cidadeDao.getCidadeByCodigoIbge(new Cidade(cdIbge));
				result = new Pessoa(resultSet.getInt("id_pessoa"), cidade, resultSet.getString("nome"));
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		desconectar();
		return result;
	}

	public List<Pessoa> getPessoas() {
		conectar();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {

			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM pessoa";
			ResultSet resultSet = stmt.executeQuery(sql);
			CidadeDao cidadeDao = new CidadeDao();

			while (resultSet.next()) {

				int cdIbge = resultSet.getInt("id_ibge");
				Cidade cidade = cidadeDao.getCidadeByCodigoIbge(new Cidade(cdIbge));

				pessoas.add(new Pessoa(resultSet.getInt("id_pessoa"), cidade, resultSet.getString("nome")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		desconectar();
		return pessoas;
	}

	public void update(Pessoa pessoa) {
		conectar();

		try {

			Statement stmt = connection.createStatement();

			String sql = "UPDATE pessoa SET " + "id_ibge = " + pessoa.getCidade().getCodigoIbge() + ", " + "nome = '"
					+ pessoa.getNome() + "' WHERE id_pessoa = " + pessoa.getId() + ";";

			stmt.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}

	}


}







