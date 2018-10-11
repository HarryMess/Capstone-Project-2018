package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;
import model.TradingAccount;
import model.User;

public class Users {

	private static Connection connection = DerbyDB.getConnection();
	
	public static boolean Login(String email, String password)
	{
		Connection connec = null;
		
		//Database CANNOT be connected to by DTP before running program, else will throw error
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); // use org.apache.derby.jdbc.EmbeddedDriver
            connec = DerbyDB.getConnection();
        }

        catch (Exception exception)
        {
            exception.printStackTrace(); /* Calls the toString method of whatever exception was thrown */
            Statement statem = null;
        }
        
        
        try
        {
            Statement statem = connec.createStatement();	
            String sql2 = "select email from username.users where email='"+email+"'";
            
            ResultSet res = statem.executeQuery(sql2);
            int counter = 0;
            while (res.next())
            {
                counter += 1; //counting how many columns are present
            }

            if (counter == 1)
            {
            	if (password.contentEquals("password")) // if password correct
	            {
	                return true;
	            }

	            else if (password != "password") //if password incorrect
	            {	    	                
	                return false;

	            }
            }

            else if (counter == 2) //if username not found
            {
                return false;

            }
        }

        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        
        return false;
    }
	
	public static boolean Register(User user) 
	{
		// has the password before adding the user
		user.hashPassword(user.getPassword());
		
		System.out.println("Hashed password: "+ user.getPassword());
		
		try {
			// add data to the database tables
			addUser(user);
			addTradingAccount(user);
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	// called in the register method to add a new user to the user table
	private static boolean addUser(User user) {
		
		PreparedStatement statement = null;
		
		try {
			System.out.println("Old Users table:");
			showUsersTable();
			
			statement = connection.prepareStatement("INSERT INTO Users VALUES (?, ?, ?)");
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			if(user instanceof Admin)
				statement.setBoolean(3, true);
			else
				statement.setBoolean(3, false);
			
				statement.execute();
				statement.close();
				
				System.out.println("New Users table:");
				showUsersTable();
				
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to execute addUser statement");
			
			return false;
		}		
	}
	
	// called in the method to add a new trading acount to the trade accounts table
	private static void addTradingAccount(User user) throws SQLException{
		
		System.out.println("Old Trading Account table:");
		showTradeAccountsTable();
		
		TradingAccount account = user.getTradingAccount();
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO TRADE_ACCOUNTS (ID, EMAIL, NAME, BALANCE, HOURS_ACTIVE)"
																+ "VALUES (6, ?, ?, ?, ?)");			
		statement.setString(1, account.getEmail());
		statement.setString(2, account.getName());			
		statement.setDouble(3, account.getBalance());
		statement.setInt(4, account.getHoursActive());
		
		statement.close();
		
		System.out.println("New Trading Account table:");
		showTradeAccountsTable();
			
		
		System.out.println("New Trading Account table:");
		showTradeAccountsTable();
		
		statement.close();
	}
	
	public static void Logout()
	{
		
	}
	
	public static void showUsersTable() throws SQLException {
		
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery("SELECT * FROM USERS");
		
		// iterate through the table
		while(results.next()) {
			
			String email = results.getString("EMAIL");
			String password = results.getString("PASSWORD");
			boolean isAdmin = results.getBoolean("ISADMIN");
			
			System.out.println(email + " | " + password + " | " + isAdmin);
    	}
		
		statement.close();
	}
	
	public static void showTradeAccountsTable() throws SQLException {
		
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery("SELECT * FROM TRADE_ACCOUNTS");
		
		System.out.println();
		
		// iterate through the table
		while(results.next()) {
			
			int id = results.getInt("ID");
			String email = results.getString("EMAIL");
			String name = results.getString("NAME");
			double balance = results.getDouble("BALANCE");
			int hours = results.getInt("HOURS_ACTIVE");
			
			System.out.println(id + " | " + email + " | " + name + " | " + balance + " | " + hours);
    	}
		
		statement.close();
	}
}
