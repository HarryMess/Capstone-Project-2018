package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class DatabaseTest {

	private static String dbURL = "jdbc:derby:Database;create=true;user='s3488361;password=password";
	private static Connection connec = null; /* Instance */
    private static Statement statem = null;
	
	@Test
	void testConnection() {
	 try
       {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
           connec = DriverManager.getConnection(dbURL);

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

}
