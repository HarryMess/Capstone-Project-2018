/** ValueTimeStamp.java
 * @author Paul King
 * This class is used to create an object for the Trade_History table which records
 * how much the player was worth over each time interval.
 */

package model;

import java.sql.Timestamp;

public class AccountTimeStamp {

	// member variables
	private final Timestamp timestamp;
	private final int accountId;
	private final float balance;
	private final float shareValue;
	
	// constructors
	public AccountTimeStamp(Timestamp timestamp, int accountId, float balance,
			float shareValue) {
		
		this.timestamp = timestamp;
		this.accountId = accountId;
		this.balance = balance;		
		this.shareValue = shareValue;
	}

	// getters	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public float getAccountId() {
		return accountId;
	}

	public float getBalance() {
		return balance;
	}

	public float getShareValue() {
		return shareValue;
	}
	
	// calculate the total worth combine balance and share value
	public float getTotalValue() {
		return balance + shareValue;
	}
	
}