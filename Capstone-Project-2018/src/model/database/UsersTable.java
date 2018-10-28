package model.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.Admin;
import model.TradingAccount;
import model.User;

public class UsersTable extends DatabaseTable{
	
	private static UsersTable users;
	private Connection connection;
	private TradingAccountsTable tradingAccounts;
	
	public static UsersTable getInstance() {
		
		if(users == null) {		
			users = new UsersTable();
		}
		
		return users;
	}
	
	public UsersTable() {
		connection = super.getConnection();
		tradingAccounts = TradingAccountsTable.getInstance();
	}
	
	// This method handles the login by comparing credentials with the database
	public boolean login(String email, String password)
	{        
        try
        {
        	// hash the email before adding it to the sql statement
        	String hashedPassword = hashPassword(password);
        	String correctPassword = null;
        	
        	PreparedStatement statement = connection.prepareStatement("select * from users where lower(email) = lower(?)");
        	
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
            	
            	if (hashedPassword.contentEquals(correctPassword)) // if password correct
	            {
            		DatabaseTable.setCurrentUser(getUser(email)); // set the logged in user
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
	
	public boolean register(String email, String password, String name) 
	{
		User user = new User(email, password);
		
		// has the password before adding the user
		user.hashPassword(user.getPassword());
		
		// System.out.println("Hashed password: "+ user.getPassword());
		
		// create a new trading account object
		
		try {
			// add data to the database tables
			addUser(user);
			
			// get the id generated from the new user
//			PreparedStatement statement = connection.prepareStatement("SELECT id FROM Users WHERE email = ?");			
//			statement.setString(1, user.getEmail());
//			
//			ResultSet result = statement.executeQuery();			
//			result.next(); // gets the first result			
//			int id = result.getInt("id");
//			
//			// add trading account to user object
//			user.setTradingAccount(new TradingAccount(id, name));
			
			// add the trading account to the trading account table			
			tradingAccounts.addTradingAccount(user, name);
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	// called in the register method to add a new user to the user table
	private boolean addUser(User user) {
		
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
	
	public void showUsersTable() throws SQLException {
		
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
	
	private String hashPassword(String password) {
		
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
	
	// returns a user object based on the id
	public User getUser(int id) throws SQLException {
		
		User user;
		
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Users WHERE id = ?");
		
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		String email = result.getString("email");
		String password = result.getString("password");
		boolean isAdmin = result.getBoolean("isAdmin");
		
		// get the player name from the trading account
		TradingAccount account = tradingAccounts.getTradingAccount(id);
		
		// create new user based on its type		
		if(isAdmin)
			user = new Admin(email, password, account);
		else
			user = new User(email, password, account);		
		return user;
	}
	
	// returns a user object based on the Username
	public User getUser(String email) throws SQLException {
		
		User user;
		
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Users WHERE email = ?");
		
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		String password = result.getString("password");
		boolean isAdmin = result.getBoolean("isAdmin");
		
		// get the player name from the trading account
		TradingAccount account = tradingAccounts.getTradingAccount(email);
		
		// create new user based on its type		
		if(isAdmin)
			user = new Admin(email, password, account);
		else
			user = new User(email, password, account);		
		return user;
	}
	
	// sets the state of the program to logged out
	public void Logout()
	{
		DatabaseTable.setCurrentUser(null);
	}

//	@Override
	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Users");
		ResultSet results = statement.executeQuery();
		
		// iterate through the results add each row to the array list
		while(results.next()) {
			String email = results.getString("email");
			String password = results.getString("password");
			TradingAccount account = null;
			
			users.add(new User(email, password, account));
		}
		
		return users; // returns th results as a list
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
