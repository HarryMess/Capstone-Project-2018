package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.ValueTimeStamp;

public class AccountHistoryTable extends DatabaseTable {
	
	private static AccountHistoryTable accountHistory;
	private static Connection connec; 
	
	public static AccountHistoryTable getInstance() {
		
		if(accountHistory == null)
			accountHistory = new AccountHistoryTable();
		
		return accountHistory;
	}
	
	private AccountHistoryTable() {
		connec = super.getConnection();
	}
	
	public List<ValueTimeStamp> getValueHistory(int userId) throws SQLException {
    	
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

	@Override
	public List<?> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
