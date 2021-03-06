package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import model.database.DatabaseTable;

class DatabaseTest {

	private static Connection connec = DatabaseTable.getConnection(); /* Instance */
	
	@Test
	void testConnection() {
	 try
       {
		   connec = DatabaseTable.getConnection();

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
	void getAdminUser() {
		
//		connec = DatabaseTable.getConnection();		
		String user = "admin@asx.com.au";
		
		try {
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Users WHERE Email = ?");
		
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		// get values from table
		int id = result.getInt("ID");
		String email = result.getString("Email");
		String password = result.getString("Password");
		boolean isAdmin = result.getBoolean("isAdmin");
		
		result.close();
		statement.close();
		
		System.out.println("Id: "+ id);
		System.out.println("Email: " + email);
		System.out.println("password: "+ password);
		System.out.println("Admin: " + isAdmin);
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
				"SELECT * FROM Users, Trading_Accounts WHERE Users.Email = ?"
				+ "AND Trading_Accounts.user_id = Users.id");
		
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
