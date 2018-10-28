package model.database.staticmethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.ValueTimeStamp;

public class AccountHistory {
	
	private static Connection connection = Database.getConnection(); 
	
	public static List<ValueTimeStamp> getValueHistory(int userId) throws SQLException {
    	
    	List<ValueTimeStamp> valueHistory = new LinkedList<ValueTimeStamp>();    	
    	
    	// create the statement
    	PreparedStatement statement = connection.prepareStatement(
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

	public static List<?> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public static void printTable() {
		// TODO Auto-generated method stub

	}
}
