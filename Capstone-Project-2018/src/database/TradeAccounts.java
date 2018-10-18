package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Company;
import model.Stock;
import model.TradingAccount;
import model.Transaction;
import model.ValueTimeStamp;

public class TradeAccounts {
	
	/* replace with your own connection string */
    private static Connection connec = DerbyDB.getConnection(); /* Instance */
    private static Statement statem = null;
    
    // returns the entire trading account matching a user id
 	public static TradingAccount getTradingAccount(int userId) throws SQLException {
 		
 		PreparedStatement statement = connec.prepareStatement(
 				"SELECT * FROM Trading_Accounts WHERE User_id = ?");
 		
 		statement.setInt(1, userId);
 		ResultSet result = statement.executeQuery();
 		
 		result.next(); // gets the matching result
 		
 		// get values from table
 		String name = result.getString("Name");
 		double balance = (double) result.getFloat("Balance");
 		int hours = result.getInt("Hours_active");
 		
 		// run additional queries for other tables
 		List<Stock> stocksOwned = getStocksOwned(userId);
 		List<ValueTimeStamp> valueHistory = getValueHistory(userId);
 		List<Transaction> transactions = getTransactionHistory(userId);
 		
 		result.close();		
 		
 		// return new trading account object containing all matching values
 		return new TradingAccount(userId, name, balance, hours, valueHistory, stocksOwned, transactions);
 	}
 	
 	// returns the entire trading account matching an email address	
 	public static TradingAccount getTradingAccount(String email) throws SQLException {
 		
 		PreparedStatement statement = connec.prepareStatement(
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
 		
 		// run additional queries for other tables
 		List<Stock> stocksOwned = getStocksOwned(userId);
 		List<ValueTimeStamp> valueHistory = getValueHistory(userId);
 		List<Transaction> transactions = getTransactionHistory(userId);
 		
 		result.close();		
 		
 		// return new trading account object containing all matching values
 		return new TradingAccount(userId, name, balance, hours, valueHistory, stocksOwned, transactions);
 	}
 	
    public static List<ValueTimeStamp> getValueHistory(int userId) throws SQLException {
    	
    	connec = DerbyDB.getConnection();
    	
    	List<ValueTimeStamp> valueHistory = new LinkedList<ValueTimeStamp>();    	
    	
    	// create the statement
    	PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Account_History\n" + 
				"INNER JOIN Trading_Accounts ON Account_History.account_id = Trading_Accounts.user_id\n" + 
				"WHERE Trading_accounts.user_id = ?");
    	
    	
    	
    	// set variable for parameter
		statement.setInt(1, userId);
		ResultSet results = statement.executeQuery();
    	
    	while(results.next()) {
    		
    		// get the column values from the result
    		Timestamp timestamp = results.getTimestamp("date_time");
    		float balance = results.getFloat("balance");
    		float shareValue = results.getFloat("share_value");
    		
    		// add the values to the arrayList
    		valueHistory.add(new ValueTimeStamp(timestamp, balance, shareValue));
    	}
    	
		return valueHistory;
    }
    
    // get stocks owned for specific user by the id
    public static List<Stock> getStocksOwned(int userId) throws SQLException {
		
    	connec = DerbyDB.getConnection();
    	
    	List<Stock> stocksOwned = new ArrayList<Stock>();
    	
    	PreparedStatement statement = connec.prepareStatement("SELECT * FROM Stocks\n" + 
    														  "WHERE Stocks.owner_id = ?");
    	
    	statement.setInt(1, userId);
    	ResultSet results = statement.executeQuery(); 
    	
    	while(results.next()) {
    		
    		String code = results.getString("Code");
    		
    		stocksOwned.add(new Stock(code, userId, 1));
    	}
    	
    	return stocksOwned;
    }
    
    // get stocks owned for specific user by the user email address
    public static List<Stock> getStocksOwned(String email) throws SQLException {
		
    	connec = DerbyDB.getConnection();
    	
    	List<Stock> stocksOwned = new ArrayList<Stock>();
    	
    	PreparedStatement statement = connec.prepareStatement("SELECT * FROM Stocks\n" + 
    			"INNER JOIN Trading_Accounts ON Stocks.owner_id = Trading_Accounts.user_id\n" + 
    			"INNER JOIN Users ON Users.id = Trading_Accounts.user_id\n" + 
    			"WHERE Users.email = ?");
    	
    	statement.setString(1, email);
    	ResultSet results = statement.executeQuery(); 
    	
    	while(results.next()) {
    		
    		String code = results.getString("Code");
    		String name = results.getString("Name");
    		int totalShares = 1;
    		
    		Company company = new Company(code, name, totalShares);
    		
    		stocksOwned.add(new Stock(company, totalShares));
    	}
    	
    	return stocksOwned;
    }
    
    // Get the transaction history for a particular user using the id 
    public static List<Transaction> getTransactionHistory(int userId) throws SQLException {
		
    	connec = DerbyDB.getConnection();
    	
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	
    	PreparedStatement statement = connec.prepareStatement(
    			"SELECT * FROM Transactions\n" + 
    			"INNER JOIN Trading_Accounts ON Transactions.buyer = Trading_Accounts.user_id\r\n" + 
    			"OR Transactions.seller = Trading_Accounts.user_id\n" + 
    			"WHERE trading_accounts.user_id = ?");
    	
    	// set variable for parameter
    	statement.setInt(1, userId);
    	ResultSet results = statement.executeQuery();    	
    	
    	while(results.next()) {
    		
    		Timestamp dateTime = results.getTimestamp("date_time");
    		String client = results.getString("client");
    		String company = results.getString("company_id");
    		float price = results.getFloat("price");
    		int amount = results.getInt("amount");
	    	transactions.add(new Transaction(dateTime, client, company, company, price, amount));
    	}

    	return transactions;    	
    }
    
}