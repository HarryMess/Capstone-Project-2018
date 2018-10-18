package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import database.DerbyDB;

class UserTest {

	
	private static Connection connection = DerbyDB.getConnection(); /* Instance */
    private static Statement statement = null;
	
	@Test
	void addUser() {
		try {
			
			statement = connection.createStatement();
			statement.execute("INSERT INTO USERS (email, password, isadmin) VALUES('user1@gmail.com', 'abc123', 'false')");
    		statement.close();
			
	    } catch (Exception e) {    		
	    	e.printStackTrace();
	    	fail(e.getMessage());
	    }
	}
	
	@Test
	void showUsers() {
		
		try {			
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery("select * from USERS");
			
			// iterate through the table
    		while(results.next()) {
    			
    			String email = results.getString("EMAIL");
    			String password = results.getString("PASSWORD");
    			boolean isAdmin = results.getBoolean("ISADMIN");
    			
    			System.out.println(email + " | " + password + " | " + isAdmin);
	    	}
    		
    		statement.close();
			
	    } catch (Exception e) {    		
	    	e.printStackTrace();
	    	fail(e.getMessage());
	    }		
	}
    
    @Test
	void deleteUser() {
		try {			
			statement = connection.createStatement();
			statement.execute("DELETE FROM Users WHERE email='user1@gmail.com'");
    		statement.close();
			
	    } catch (Exception e) {    		
	    	e.printStackTrace();
	    	fail(e.getMessage());
	    }		
	}
	
	@Test
	void loginUser(){
		fail("Not yet implemented");
	}
	
	
//	@Test
//	void test() {
//		try {
//			connection = DriverManager.getConnection(dbURL);
//			
//			statement = connection.createStatement();
//			
//    		statement.close();
//			
//	    } catch (Exception e) {    		
//	    	e.printStackTrace();
//	    	fail(e.getMessage());
//	    }		
//	}

}
