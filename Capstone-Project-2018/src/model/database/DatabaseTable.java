package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DatabaseTable {

	private static final String dbUrl = ""; 
	private static Connection connection;
	
	public static Connection getConnection() {
		
		try {
			if (connection == null) {
				Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	
				connection = DriverManager.getConnection(dbUrl);
			}
			
			return connection;
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("Error: Could not connect to database");
			e.printStackTrace();
		}

		return null;
	}
	
	/* Abstract Methods */
	public abstract List<?> getAll() throws SQLException;
//	public abstract List<?> getAllById(int id) throws SQLException;
//	public abstract Object getById(int id) throws SQLException;
//	public abstract Object getByName(String name) throws SQLException;
//	public abstract void add(Object obj) throws SQLException;
//	public abstract void update(int id, Object obj) throws SQLException;
//	public abstract void delete(int id) throws SQLException;
	public abstract String toString();
}
