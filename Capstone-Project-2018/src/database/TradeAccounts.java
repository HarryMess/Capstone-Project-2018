package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Stock;
import model.Transaction;
import model.ValueTimeStamp;

public class TradeAccounts {
	
	private static DatabaseManager db = DatabaseManager.getInstance();
    
    public static List<ValueTimeStamp> getValueHistory(String user) throws SQLException {
    	
    	List<ValueTimeStamp> valueHistory = new LinkedList<ValueTimeStamp>();    	
    	ResultSet results = db.select("Trade_History, Trade_Accounts", "*",
    		"Trade_accounts.email = '" + user + " AND Trade_History.account_id = Trade_accounts.id");    	
    	
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
		
    	List<Stock> stocksOwned = new ArrayList<Stock>();
    	
    	ResultSet results = db.select("Trade_Accounts, Stock", "*", "trade_accounts.email = "+ user + 
    			" AND trade_accounts.id = stock.trade_accountsid");
    	
    	while(results.next()) {
    		
    		String code = results.getString("Code");
    		String name = results.getString("Name");
    		
    		stocksOwned.add(new Stock(code, user, 1));
    	}
    	
    	return stocksOwned;    	
    }
    
    public static List<Transaction> getTransactionHistory(String user) throws SQLException {
		
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	
    	ResultSet results = db.select("Company_Transaction, Trade_Accounts", "*", "trade_accounts.email = "+ user +
    								  "AND Company_Transaction.client = trade_accounts.id");
    	
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
