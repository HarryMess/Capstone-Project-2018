package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Transaction;

/**
 * 
 * @author Paul King - s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class TransactionsTable extends DatabaseTable {
	
	private static TransactionsTable transactions;	
	private Connection connection = DatabaseTable.getConnection();
	
	public static TransactionsTable getInstance() {
		if(transactions == null) {
			transactions = new TransactionsTable();
		}
		
		return transactions;
	}
	
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
    public List<Transaction> getTransactionHistory(int userId) throws SQLException {
    	
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
    		int buyer = results.getInt("buyer");
    		int seller = results.getInt("seller");
    		String company = results.getString("company_code");
    		float price = results.getFloat("price");
    		int amount = results.getInt("amount");
	    	transactions.add(new Transaction(dateTime, buyer, seller, company, price, amount));
    	}

    	return transactions;    	
    }
    
    // Adds a new transaction record to the transactions table
 	public boolean addTransaction(Transaction transaction) {
 		try {
 			PreparedStatement statement = connection.prepareStatement(
 					"INSERT INTO Transactions (Date_Time, Buyer, Seller, Company_Code, Price)\n"
 				  + "VALUES (?, ?, ?, ?, ?)");
 			
 			statement.setTimestamp(1, transaction.getTimestamp());
 			statement.setInt(2, transaction.getBuyerId());
 			statement.setInt(3, transaction.getSellerId());
 			statement.setString(4, transaction.getCompanyCode());
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
