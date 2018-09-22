package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseManager {
	
	/* This needs to be changed */
	private String dbURL = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Capstone-Project-2018\\derby-10.14.2.0\\bin\\Database;create=false;user=username";
    private Connection connec = null; /* Instance */
    private Statement statem = null;
	
	// create
	public void insert(String table, String columns, String values) throws SQLException {
		statem = connec.createStatement();
		statem.execute("INSERT INTO " + table + "(" + columns + ") \n" +
						"VALUES (" + values + ")");
		statem.close();
	}
	
	// read
	public ResultSet select(String table, String columns) throws SQLException {
		
		statem = connec.createStatement();
		ResultSet results = statem.executeQuery("SELECT " + columns + " FROM " + table);
		statem.close();
		
		return results;		
	}
	
	// read
	public ResultSet select(String table, String columns, String condition) throws SQLException {
		
		statem = connec.createStatement();
		ResultSet set = statem.executeQuery("SELECT " + columns + " FROM " + table + 
											"WHERE " + condition);
		statem.close();
		return set;		
	}
	
	//update	
	public ResultSet update(String table, String set, String condition) throws SQLException {
		statem = connec.createStatement();
		ResultSet results = statem.executeQuery("UPDATE " + table + " SET " + set + " WHERE " + condition);
		statem.close();
		return results;	
	}
	
	//delete
	public void delete(String table, String condition) throws SQLException {
		statem = connec.createStatement();
		statem.executeQuery("DELETE FROM " + table + " WHERE " + condition);
		statem.close();
	}
}
