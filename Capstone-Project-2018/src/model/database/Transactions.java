package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class Transactions extends DatabaseTable {
	
	private static Connection connec = DatabaseTable.getConnection();
	
	@Override
	public List<Transaction> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// returns a transaction based on an id
	public Transaction getTransaction(int id) {
		return null;
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
    
    // Adds a new transaction record to the transactions table
 	public static boolean addTransaction(Transaction transaction) {
 		try {
 			PreparedStatement statement = connec.prepareStatement("INSERT INTO TRANSACTION Transaction VALUES (?, ?, ?, ?, ?, ?)");
 			
 			statement.setTimestamp(1, transaction.getTimestamp());
 			statement.setString(2, transaction.getBuyerId());
 			statement.setString(3, transaction.getSellerId());
 			statement.setString(4, transaction.getStockId());
 			statement.setFloat(5, transaction.getPrice());
 			
 			// attempt to execute the query
 			if(statement.execute()) {
 				statement.close();			
 				return true;
 			}
 			
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			
 		}
 		return false;
 		
 	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
