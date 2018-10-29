package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.AccountTimeStamp;

public class AccountHistoryTable extends DatabaseTable {
	
	private static AccountHistoryTable accountHistory;
	private static Connection connec; 
	
	public static AccountHistoryTable getInstance() {
		
		if(accountHistory == null) {
			accountHistory = new AccountHistoryTable();
		}
		
		return accountHistory;
	}
	
	public AccountHistoryTable() {
		connec = super.getConnection();
	}
	
	@Override
	public List<?> getAll() throws SQLException {
		
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
	
	public List<AccountTimeStamp> getAccountHistory(int userId) throws SQLException {
    	
    	List<AccountTimeStamp> valueHistory = new LinkedList<AccountTimeStamp>();    	
    	
    	// create the statement
    	PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Account_History\n" + 
				"WHERE Trading_accounts.user_id = ?");

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
