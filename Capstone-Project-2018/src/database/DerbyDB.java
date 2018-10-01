package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDB {
	
	public static final String dbUrl = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Capstone-Project-2018\\derby-10.14.2.0\\bin\\Database;create=false;user=username";  // Enter your local URL after the second colon
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
	
}