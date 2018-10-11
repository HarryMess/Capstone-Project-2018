package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class UserTest {

	
	private static String dbURL = "jdbc:derby:"; // Enter your local URL here
	private static Connection connection = null; /* Instance */
    private static Statement statement = null;
	
	@Test
	void registerUser() {
		try {
			connection = DriverManager.getConnection(dbURL);
			
			statement = connection.createStatement();
			statement.execute("INSERT INTO USERS VALUES('user1@gmail.com', 'abc123', 'false')");
    		statement.close();
			
	    } catch (Exception e) {    		
	    	e.printStackTrace();
	    	fail(e.getMessage());
	    }
	}
	
	@Test
	void showUsers() {
		
		try {
			connection = DriverManager.getConnection(dbURL);
			
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
	void DeleteUser() {
		try {
			connection = DriverManager.getConnection(dbURL);
			
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
