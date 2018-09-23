package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Stock;
import model.Transaction;
import model.ValueTimeStamp;

public class TradeAccounts {
	
	/* replace with your own connection string */
	private static String dbURL = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Capstone-Project-2018\\derby-10.14.2.0\\bin\\Database;create=false;user=username"; 
    private static Connection connec = null; /* Instance */
    private static Statement statem = null;
    
    public static List<ValueTimeStamp> getValueHistory(String user) throws SQLException {
    	
    	connec = DriverManager.getConnection(dbURL);
    	
    	List<ValueTimeStamp> valueHistory = new LinkedList<ValueTimeStamp>();    	
    	
    	// create the statement
    	PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Trade_History, Trade_Accounts \n" + 
				"WHERE Trade_accounts.email = ? AND Trade_History.account_id = Trade_accounts.id");
    	
    	// set variable for parameter
		statement.setString(1, user);
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
    
    // get stocks owned for specific user
    public static List<Stock> getStocksOwned(String user) throws SQLException {
		
    	connec = DriverManager.getConnection(dbURL);
    	
    	List<Stock> stocksOwned = new ArrayList<Stock>();
    	
    	PreparedStatement statement = connec.prepareStatement("SELECT * FROM Stock, trade_accounts \n" +
    			"WHERE trade_accounts.email = ? AND trade_accounts.id = stock.trade_accountsid");
    	
    	statement.setString(1, user);
    	ResultSet results = statement.executeQuery(); 
    	
    	while(results.next()) {
    		
    		String code = results.getString("Code");
    		String name = results.getString("Name");
    		
    		stocksOwned.add(new Stock(code, user, 1));
    	}
    	
    	return stocksOwned;
    }
    
    // 
    public static List<Transaction> getTransactionHistory(String user) throws SQLException {
		
    	connec = DriverManager.getConnection(dbURL);
    	
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	
    	PreparedStatement statement = connec.prepareStatement(
    			"SELECT * FROM Company_Transaction, trade_accounts \n" +
    			 "WHERE trade_accounts.email = ? AND Company_Transaction.client = trade_accounts.id");
    	
    	// set variable for parameter
    	statement.setString(1, user);
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
