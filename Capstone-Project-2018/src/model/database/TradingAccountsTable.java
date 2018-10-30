package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import model.TradingAccount;
import model.User;

public class TradingAccountsTable extends DatabaseTable {
	
	private static TradingAccountsTable tradingAccounts;
	
    private Connection connection; /* Instance */
    
    public static TradingAccountsTable getInstance() {
		if(tradingAccounts == null) {
			tradingAccounts = new TradingAccountsTable();
		}		
		return tradingAccounts;
	}
	
	private TradingAccountsTable() {
		connection = super.getConnection();
	}
    
	@Override
	public List<TradingAccount> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
    // returns the entire trading account matching a user id
 	public TradingAccount getTradingAccount(int userId) throws SQLException {
 		
 		PreparedStatement statement = connection.prepareStatement(
 				"SELECT * FROM Trading_Accounts WHERE User_id = ?");
 		
 		statement.setInt(1, userId);
 		ResultSet result = statement.executeQuery();
 		
 		result.next(); // gets the matching result
 		
 		// get values from table
 		String name = result.getString("Name");
 		double balance = (double) result.getFloat("Balance");
 		int hours = result.getInt("Hours_active");
 		
 		result.close();		
 		
 		// return new trading account object containing all matching values
 		return new TradingAccount(userId, name, balance, hours);
 	}
 	
 	// returns the entire trading account matching an email address	
 	public TradingAccount getTradingAccount(String email) throws SQLException {
 		
 		PreparedStatement statement = connection.prepareStatement(
 				"SELECT * FROM Trading_Accounts \n"
 			  + "INNER JOIN Users ON Trading_Accounts.user_id = Users.id \n"
 			  + "WHERE Users.email = ?");
 		
 		statement.setString(1, email);
 		ResultSet result = statement.executeQuery();
 		
 		result.next(); // gets the matching result
 		
 		// get values from table
 		int userId = result.getInt("Id");
 		String name = result.getString("Name");
 		double balance = (double) result.getFloat("Balance");
 		int hours = result.getInt("Hours_active");
 		
 		result.close();		
 		
 		// return new trading account object containing all matching values
 		return new TradingAccount(userId, name, balance, hours);
 	}
 	
 	// called in the method to add a new trading acount to the trade accounts table
 	public void addTradingAccount(User user, String name) throws SQLException{
 		
 		System.out.println("Old Trading Account table:");
 		showTradeAccountsTable();

 		// get the id from the email address
 		PreparedStatement statement1 = connection.prepareStatement("SELECT ID FROM USERS WHERE EMAIL = ?");
 		statement1.setString(1, user.getEmail());
 		ResultSet result = statement1.executeQuery();
 		
 		int userId = 0;
 		
 		while(result.next()) {
 			userId = result.getInt("ID");
 		}
 		
 		statement1.close();
 		
 		TradingAccount account = new TradingAccount(userId, name);
 		
 		// add the trading account to the database
 		PreparedStatement statement2 =connection.prepareStatement("INSERT INTO TRADING_ACCOUNTS (USER_ID, NAME, BALANCE, HOURS_ACTIVE)"
 																 + "VALUES (?, ?, ?, ?)");
 		
 		// add parameters to sql statement
 		statement2.setInt(1, userId);
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
    
    // prints out the trading account table
    public void showTradeAccountsTable() throws SQLException {
		
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
    
	// Transfers moneys from one player to another by updating the balance on both players on the database table
	public boolean transferFunds(TradingAccount sender, TradingAccount receiver, float amount) throws SQLException {
	
		PreparedStatement statement1 = connection.prepareStatement("UPDATE Trading_Accounts\n"
															+ "SET balance = balance - ?\n"
															+ "WHERE name = ?");
		PreparedStatement statement2 = connection.prepareStatement("UPDATE Trading_Accounts\n"
															+ "SET balance = balance + ?\n"
															+ "WHERE name = ?");	
		// add parameters to statement
		statement1.setFloat(1, amount);
		statement1.setString(2, sender.getName());
		statement2.setFloat(1, amount);
		statement2.setString(2, receiver.getName());
		
		// attempt to execute the query
		if(statement1.execute() && statement2.execute()) {
			statement1.close();
			statement2.close();
			return true;
		}
						
		return false;
		
	}
}