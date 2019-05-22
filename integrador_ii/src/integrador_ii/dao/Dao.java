package integrador_ii.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {
	
	protected Connection connection;
	
	protected void conectar() {
		
		try {
			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/financeiro", "postgres", "101105");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void desconectar() {
		
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
