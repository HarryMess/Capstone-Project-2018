package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import database.DerbyDB;

class DatabaseTest {

	private static Connection connec = null; /* Instance */
    private static Statement statem = null;
	
	@Test
	void testConnection() {
	 try
       {
		   Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		   connec = DerbyDB.getConnection();
           

           /* Old method of mapping driver to URL, refer to
            * https://docs.oracle.com/javase/6/docs/api/java/sql/DriverManager.html
            * Swap to the method outlined in the url when possible
           */
           
           System.out.println("Connection successful");
       }

       catch (Exception e)
       {    	       	   
           e.printStackTrace(); /* Calls the toString method of whatever exception was thrown */
           fail(e.getMessage());
       }
	}
	
	@Test
	void getAdminTradingAccount() {
		
		String user = "admin@asx.com.au";
		DecimalFormat currency = new DecimalFormat("$.##");
		
		try {
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Trade_Accounts WHERE Email = ?");
		
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		// get values from table
		String name = result.getString("Name");
		float balance =  result.getFloat("Balance");
		int hours = result.getInt("Hours_active");
		
		result.close();
		statement.close();
		
		System.out.println("Name: "+name);
		System.out.println("Balance: " +currency.format(balance));
		System.out.println("Hours active: "+hours);
		System.out.println();
		
		} catch(SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	void getPaulTradingAccount() {
		
		DecimalFormat currency = new DecimalFormat("$.##");
		
		String user = "s3449513@student.rmit.edu.au";
		
		try {
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Trade_Accounts WHERE Email = ?");
		
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		// get values from table
		String name = result.getString("Name");
		float balance =  result.getFloat("Balance");
		int hours = result.getInt("Hours_active");
		
		result.close();
		statement.close();
		
		System.out.println("Name: "+name);
		System.out.println("Balance: "+ currency.format(balance));
		System.out.println("Hours active: "+hours);
		System.out.println();
		
		} catch(SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
