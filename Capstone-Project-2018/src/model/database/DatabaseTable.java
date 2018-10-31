package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.User;

/**
 * This is an abstract class at the top of the hierarchy containing the connection string for the derby database,
 * the User object for the user logged in to the current session, and abstract methods to be overridden by classes inheriting this one.
 * @author Paul King - s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public abstract class DatabaseTable {

	private static final String dbUrl = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Database;create=true;upgrade=true;user=username";
	private static Connection connection;
	private static User currentUser = null;
	
	/**
	 * Singleton method
	 * @return retrieves the connection from the database
	 */
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
	
	/**
	 * Used to set the user that is currently active after login was successful
	 * @param user The user to set as logged in for this session
	 */
	public static void setCurrentUser(User user) {
		currentUser = user;
	}
	
	/**
	 * 
	 * @return gets the user that is currenty logged in
	 */
	public static User getCurrentUser() {
		return currentUser;
	}	
	
	/* Abstract Methods */
	/**
	 * 
	 * @return retrieves all records from the database table saved as a generic list
	 * @throws SQLException
	 */
	public abstract List<?> getAll() throws SQLException;
//	public abstract List<?> getAllById(int id) throws SQLException;
//	public abstract Object getById(int id) throws SQLException;
//	public abstract Object getByName(String name) throws SQLException;
//	public abstract void add(Object obj) throws SQLException;
//	public abstract void update(int id, Object obj) throws SQLException;
//	public abstract void delete(int id) throws SQLException;
//	public abstract void printTable() throws SQLException;
}
