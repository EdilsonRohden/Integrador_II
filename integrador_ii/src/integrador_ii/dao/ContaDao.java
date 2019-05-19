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
			String sql = "SELECT * FROM conta JOIN primeiro_nivel ON conta.idprimeironivel = primeiro_nivel.id";
			
			ResultSet resultSet = stmt.executeQuery(sql);
			if(resultSet != null) {
				while(resultSet.next()) {
					contas.add(new Conta(
							resultSet.getInt("idconta"),
							resultSet.getInt("id"),
							resultSet.getInt("idsegundonivel"),
							resultSet.getString("descricao"),
							resultSet.getString("descricaosnivel")
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
			String sql = "SELECT * FROM conta JOIN primeiro_nivel ON conta.idprimeironivel = primeironivel.id WHERE idconta = " + conta.getId();
			
			ResultSet result = stmt.executeQuery(sql);
			
			if(result != null) {
				resultado.setId(result.getInt("idconta"));
				resultado.setIdP(result.getInt("idprimeironivel"));
				resultado.setIdS(result.getInt("idsegundonivel"));
				resultado.setDescricaoP(result.getString("descricao"));
				resultado.setDescricaoS(result.getString("descricaosnivel"));
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
			
			String sql = "INSERT INTO conta (idconta, idprimeironivel, idsegundonivel, descricaosegundonivel) VALUES ( null, "+
					conta.getIdP()+", " + conta.getIdS() + ", " + conta.getDescricaoS() + ");";
			
			stmt.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			desconectar();
		}
	}

}















