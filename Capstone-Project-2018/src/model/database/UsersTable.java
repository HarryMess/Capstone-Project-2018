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
import model.User;

/**
 * Contains methods that run SQL queries relevant to the Users Table. Registration and login is also done here.
 * @author Paul King - s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class UsersTable extends DatabaseTable{
	
	private static UsersTable users;
	private Connection connection;
	private TradingAccountsTable tradingAccounts;
	
	/**
	 * Used to get the static object of this class
	 * If the value is null it will be instantiated using a private constructor
	 * @return gets a singleton instance of this class
	 */
	public static UsersTable getInstance() {
		
		if(users == null) {		
			users = new UsersTable();
		}
		
		return users;
	}
	
	/**
	 * Constructor
	 */
	private UsersTable() {
		connection = super.getConnection();
		tradingAccounts = TradingAccountsTable.getInstance();
	}
	
	/**
	 * Handles the login by comparing credentials with the database
	 * @param email used as the username
	 * @param password the user's password
	 * @return returns true of login was successful, fals if it failed
	 */
	public boolean login(String email, String password)
	{        
        try
        {
        	// hash the email before adding it to the sql statement
        	String hashedPassword = hashPassword(password);
        	String correctPassword = null;
        	
        	PreparedStatement statement = connection.prepareStatement("select * from users where lower(email) = lower(?)");
            
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
	
	/**
	 * Runs the registration process by first hashing the password then adding a new user to the database
	 * @param email This is the username
	 * @param password this is the un-hashed password
	 * @param name This is a combination of the first name and last name
	 * @return returns true if login was successful, false if it failed
	 */
	public boolean register(String email, String password, String name) 
	{
		User user = new User(email, hashPassword(password));
		
		try {
			// add data to the database tables
			addUser(user);
			
			// add the trading account to the trading account table			
			tradingAccounts.addTradingAccount(user, name);

			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Called from register method to add a user to the database
	 * @param user takes a user object once the user has been created
	 * @return returns true on success, false on failure
	 */
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
	
	/**
	 * prints the entire Users table to the console
	 * @throws SQLException
	 */
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
	
	/**
	 * Hashes the password during the registration process using the SHA1 algorithm.
	 * @param password take the unhashed password as an argument
	 * @return The hashed password
	 */
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
	
	/**
	 * Find a user in the database matching a specific id 
	 * @param id the id to search the Users table for
	 * @return returns the user object created from result set
	 * @throws SQLException
	 */
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
		
		// create new user based on its type		
		if(isAdmin)
			user = new Admin(id, email, password);
		else
			user = new User(id, email, password);		
		return user;
	}
	
	/**
	 * Find a user in the database matching a specific username
	 * @param email takes an email address as the username to search the users table with
	 * @return returns the user object created from result set
	 * @throws SQLException
	 */
	// returns a user object based on the Username
	public User getUser(String email) throws SQLException {
		
		User user;
		
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Users WHERE email = ?");
		
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		int id = result.getInt("id");
		String password = result.getString("password");
		boolean isAdmin = result.getBoolean("isAdmin");
		
		// create new user based on its type		
		if(isAdmin)
			user = new Admin(id, email, password);
		else
			user = new User(id, email, password);		
		return user;
	}
	
	/**
	 * Logs out the user by setting the the static current user variable in DatabaseTable to null
	 */
	// sets the state of the program to logged out
	public void Logout()
	{
		DatabaseTable.setCurrentUser(null);
	}

	/*
	 * (non-Javadoc)
	 * @see model.database.DatabaseTable#getAll()
	 */
	/**
	 * Returns all rows in the Users table as one list containing 'User' objects
	 */
	@Override
	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM Users");
		ResultSet results = statement.executeQuery();
		
		// iterate through the results add each row to the array list
		while(results.next()) {
			int id = results.getInt("id");
			String email = results.getString("email");
			String password = results.getString("password");
			boolean isAdmin = results.getBoolean("isAdmin");
			
			users.add(new User(id, email, password, isAdmin));
		}
		
		return users; // returns the results as a list
	}
}
