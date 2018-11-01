package model;

import java.sql.Timestamp;

/**
 * This class contains all the variables needed for the Account value history.
 * It determines how much he player was worth over each time interval.
 * Contains values from the StockHistory table 
 * @author Paul King
 * @version 1.0
 * @since 30/10/2018 
 */
public class AccountTimeStamp {

	// member variables
	private final Timestamp timestamp;
	private final int accountId;
	private final float balance;
	private final float shareValue;
	
	/**
	 * 
	 * @param timestamp
	 * @param accountId
	 * @param balance
	 * @param shareValue
	 */
	// constructors
	public AccountTimeStamp(Timestamp timestamp, int accountId, float balance,
			float shareValue) {
		
		this.timestamp = timestamp;
		this.accountId = accountId;
		this.balance = balance;		
		this.shareValue = shareValue;
	}

	/**
	 * 
	 * @return
	 */
	// getters	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getAccountId() {
		return accountId;
	}

	/**
	 * 
	 * @return
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * 
	 * @return
	 */
	public float getShareValue() {
		return shareValue;
	}
	
	/**
	 * Calculates the total worth of a player by adding balance and share value together
	 * @return the total value of a player. This value is to be plotted to the graph
	 */
	public float getTotalValue() {
		return balance + shareValue;
	}
	
}