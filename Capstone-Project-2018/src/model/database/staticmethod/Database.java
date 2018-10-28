package model.database.staticmethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.User;

public class Database {
	
	private static final String dbUrl = "jdbc:derby:;create=true;upgrade=true;user=username"; // add local path after derby:
	private static Connection connection;
	private static User currentUser = null;

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
		
		public static void setCurrentUser(User user) {
			currentUser = user;
		}
		
		public User getCurrentUser() {
			return currentUser;
		}
}
