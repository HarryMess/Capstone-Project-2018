package model.database.staticmethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.database.DatabaseTable;

public class Transactions {
	
	private static Connection connection = Database.getConnection();
	
	public static List<Transaction> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	// returns a transaction based on an id
	public static Transaction getTransaction(int id) {
		return null;
	}
	
	// Get the transaction history for a particular user using the id 
    public static List<Transaction> getTransactionHistory(int userId) throws SQLException {
    	
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	
    	PreparedStatement statement = connection.prepareStatement(
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
 			PreparedStatement statement = connection.prepareStatement(
 					"INSERT INTO Transactions (Date_Time, Buyer, Seller, Company_Code, Price)\n"
 				  + "VALUES (?, ?, ?, ?, ?)");
 			
 			statement.setTimestamp(1, transaction.getTimestamp());
 			statement.setInt(2, transaction.getBuyerId());
 			statement.setInt(3, transaction.getSellerId());
 			statement.setString(4, transaction.getStockCode());
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
}
