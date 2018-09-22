/** ValueTimeStamp.java
 * @author Paul
 * This class is used to create an object for the Trade_History table which records
 * how much the player was worth over each time interval.
 */

package model;

import java.sql.Timestamp;

public class ValueTimeStamp {

	// member variables
	private final Timestamp timestamp;
	private final float balance;
	private final float shareValue;
	
	// constructors
	public ValueTimeStamp(Timestamp timestamp, float balance, float shareValue) {
		
		this.timestamp = timestamp;
		this.balance = balance;
		this.shareValue = shareValue;
	}

	// getters	
	public Timestamp getTimestamp() {
		return timestamp;
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
