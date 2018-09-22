package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	
	private static DatabaseManager dbManager;
	
	/* Insert your own connection string here */
	private String url = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Capstone-Project-2018\\derby-10.14.2.0\\bin\\Database;create=false;user=username";
    private Connection connection; /* Instance */
    private Statement statement;
    
    public static DatabaseManager getInstance() {
    	if (dbManager == null)
    		dbManager = new DatabaseManager();
    	
    	return dbManager;
    }
    
    private DatabaseManager()  {
    	    	
    	try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			connection = DriverManager.getConnection(url);
			statement = null;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
        
    }
	
	// create
	public void insert(String table, String columns, String values) throws SQLException {
		statement = connection.createStatement();
		statement.execute("INSERT INTO " + table + "(" + columns + ") \n" +
						"VALUES (" + values + ")");
		statement.close();
	}
	
	// read
	public ResultSet select(String table, String columns) throws SQLException {
		
		statement = connection.createStatement();
		ResultSet results = statement.executeQuery("SELECT " + columns + " FROM " + table);
		statement.close();
		
		return results;		
	}
	
	// read
	public ResultSet select(String table, String columns, String condition) throws SQLException {
		
		statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT " + columns + " FROM " + table + 
											"WHERE " + condition);
		statement.close();
		return set;		
	}
	
	//update	
	public ResultSet update(String table, String set, String condition) throws SQLException {
		statement = connection.createStatement();
		ResultSet results = statement.executeQuery("UPDATE " + table + " SET " + set + " WHERE " + condition);
		statement.close();
		return results;	
	}
	
	//delete
	public void delete(String table, String condition) throws SQLException {
		statement = connection.createStatement();
		statement.executeQuery("DELETE FROM " + table + " WHERE " + condition);
		statement.close();
	}
}
