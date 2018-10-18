package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.DatatypeConverter;

import model.Admin;
import model.TradingAccount;
import model.User;

public class Users {

	private static Connection connection = DerbyDB.getConnection();
	
	public static boolean login(String email, String password)
	{        
        try
        {
        	// hash the email before adding it to the sql statement
        	String hashedPassword = hashPassword(password);
        	String correctPassword = null;
        	
        	PreparedStatement statement = connection.prepareStatement("select * from users where email = ?");
        	
//            Statement statem = connec.createStatement();	
//            String sql2 = "select * from users where email = '"+email+"' ";
            
        	statement.setString(1, email);
        	
            ResultSet res = statement.executeQuery(); // statem.executeQuery(sql2);
            int counter = 0;
            while (res.next())
            {
                counter += 1; //counting how many columns are present
                correctPassword = res.getString("password");
            }

            if (counter == 1) // ensures that there is only one copy in the database
            {            	
            	System.out.println("Hashed password entered = " + hashedPassword);            	
            	System.out.println("Correct Hashed Password = " + correctPassword);
            	
            	if (hashedPassword.contentEquals(correctPassword)) // if password correct
	            {
	                return true;
	            }

	            else if (hashedPassword != correctPassword) //if password incorrect
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
	
	public static boolean register(User user) 
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
			
			statement = connection.prepareStatement("INSERT INTO Users (EMAIL, PASSWORD, ISADMIN) VALUES (?, ?, ?)");
			
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
		
		// get the id from the email address
		PreparedStatement statement1 = connection.prepareStatement("SELECT ID FROM USERS WHERE EMAIL = ?");
		statement1.setString(1, user.getEmail());
		ResultSet result = statement1.executeQuery();
		
		int user_id = 0;
		
		while(result.next()) {
			user_id = result.getInt("ID");
		}
		
		statement1.close();
		
		// add the trading account to the database
		PreparedStatement statement2 = connection.prepareStatement("INSERT INTO TRADING_ACCOUNTS (USER_ID, NAME, BALANCE, HOURS_ACTIVE)"
																+ "VALUES (?, ?, ?, ?)");	
		statement2.setInt(1, user_id);
		statement2.setString(2, account.getName());			
		statement2.setDouble(3, account.getBalance());
		statement2.setInt(4, account.getHoursActive());
		
		System.out.println("New Trading Account table:");
		showTradeAccountsTable();
			
		statement2.execute();
		statement2.close();
		
		System.out.println("New Trading Account table:");
		showTradeAccountsTable();	
		
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
		ResultSet results = statement.executeQuery("SELECT * FROM TRADING_ACCOUNTS");
		
		System.out.println();
		
		// iterate through the table
		while(results.next()) {
			
			int id = results.getInt("User_ID");
			String name = results.getString("NAME");
			double balance = results.getDouble("BALANCE");
			int hours = results.getInt("HOURS_ACTIVE");
			
			System.out.println(id + " | " + name + " | " + balance + " | " + hours);
    	}
		
		statement.close();
	}
	
	private static String hashPassword(String password) {
		
		String hashedPassword;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(password.getBytes());
			
			// create a string format of hash
			byte[] digest = md.digest();			
			hashedPassword = DatatypeConverter.printHexBinary(digest);
			
			// update the password
			return hashedPassword;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return password;
	}
}
