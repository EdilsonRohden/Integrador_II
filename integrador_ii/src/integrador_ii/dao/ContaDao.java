package integrador_ii.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integrador_ii.models.Conta;

public class ContaDao extends Dao{

	public List<Conta> getContas() {
		conectar();
		
		List<Conta> contas = new ArrayList<Conta>();
		try {
			
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM conta JOIN primeiro_nivel ON conta.id_primeiro_nivel = primeiro_nivel.id";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet != null) {
				while(resultSet.next()) {
					contas.add(new Conta(
							resultSet.getInt("conta.id_conta"),
							resultSet.getInt("primeiro_nivel.id"),
							resultSet.getInt("conta.id_segundo_nivel"),
							resultSet.getString("primeiro_nivel.descricao"),
							resultSet.getString("conta.descricao")
							));
				}
				
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		
		return contas;
	}

	public Conta getContaById(Conta conta) {
		conectar();
		Conta resultado = new Conta();
		
		try {
			
			Statement stmt = connection.createStatement();
			String sql = "SELECT * FROM conta JOIN primeiro_nivel ON conta.id_primeiro_nivel = primeiro_nivel.id WHERE id_conta = " + conta.getId();
			
			ResultSet result = stmt.executeQuery(sql);
			
			if(result != null) {
				resultado.setId(result.getInt("conta.id_conta"));
				resultado.setIdP(result.getInt("primeiro_nivel.id"));
				resultado.setIdS(result.getInt("conta.id_segundo_nivel"));
				resultado.setDescricaoP(result.getString("primeiro_nivel.descricao"));
				resultado.setDescricaoS(result.getString("conta.descricao"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
		
		
		return resultado;
	}

	public void update(Conta conta) {
		// TODO Auto-generated method stub
		
	}

	public void salvar(Conta conta) {
		
		conectar();
		
		try {
			
			Statement stmt = connection.createStatement();
			
			String sql = "INSERT INTO conta (id_conta, id_primeiro_nivel, id_segundo_nivel, descricao) VALUES ( null, "+
					conta.getIdP()+", " + conta.getIdS() + ", '" + conta.getDescricaoS() + "');";
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
	}

}















