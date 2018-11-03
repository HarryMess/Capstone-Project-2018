package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.AccountTimeStamp;

/**
 * Contains methods that run SQL queries relevant to the AccountHistory Table
 * This is needed to create and return timestamps producing a line graph of the trading account value history
 * used on the portfolio screen
 * @author Paul King - s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class AccountHistoryTable extends DatabaseTable {
	
	private static AccountHistoryTable accountHistory;
	private static Connection connec; 
	
	/**
	 * Used to get the static object of this class
	 * If the value is null it will be instantiated using a private constructor
	 * @return gets a singleton instance of this class
	 */
	public static AccountHistoryTable getInstance() {
		
		if(accountHistory == null) {
			accountHistory = new AccountHistoryTable();
		}
		
		return accountHistory;
	}
	
	/**
	 * Constructor. Retrieves the database connection.
	 */
	private AccountHistoryTable() {
		connec = super.getConnection();
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.database.DatabaseTable#getAll()
	 */
	@Override
	public List<AccountTimeStamp> getAll() throws SQLException {
		
		List<AccountTimeStamp> valueHistory = new LinkedList<AccountTimeStamp>();    	
    	
    	// create the statement
    	PreparedStatement statement = connec.prepareStatement("SELECT * FROM Account_History");

		ResultSet results = statement.executeQuery();
    	
    	while(results.next()) {
    		
    		// get the column values from the result
    		Timestamp timestamp = results.getTimestamp("date_time");
    		int accountId = results.getInt("account_id");
    		float balance = results.getFloat("balance");
    		float shareValue = results.getFloat("share_value");
    		
    		// add the values to the arrayList
    		valueHistory.add(new AccountTimeStamp(timestamp, accountId, balance, shareValue));
    	}
    	
		return valueHistory;
	}
	
	/**
	 * Retrieves the entire account history of a specific trading account using it's user id
	 * This list is used to for drawing a graph of the history on the GUI
	 * @param userId takes the id number of a user account for use in SQL query
	 * @return returns a linked list of objects from the 'AccountTimeStamp' class built from the result set
	 * @throws SQLException
	 */
	public List<AccountTimeStamp> getAccountHistory(int userId) throws SQLException {
    	
    	List<AccountTimeStamp> valueHistory = new LinkedList<AccountTimeStamp>();    	
    	
    	// create the statement
    	PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Account_History\n" + 
				"WHERE account_id = ?");

    	// set variable for parameter
		statement.setInt(1, userId);
		ResultSet results = statement.executeQuery();
    	
    	while(results.next()) {
    		
    		// get the column values from the result
    		Timestamp timestamp = results.getTimestamp("date_time");
    		int accountId = results.getInt("account_id");
    		float balance = results.getFloat("balance");
    		float shareValue = results.getFloat("share_value");
    		
    		// add the values to the arrayList
    		valueHistory.add(new AccountTimeStamp(timestamp, accountId, balance, shareValue));
    	}
    	
		return valueHistory;
    }
}
