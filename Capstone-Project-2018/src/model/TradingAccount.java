package model;

/**
 * This class contains all the variables relevant to a trading account
 * Matches the Users Table table
 * @author Paul King -s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class TradingAccount {
	
	public final double STARTING_MONEY = 1000000.00; // each trading account starts with 1 million dollars

	// member variables
	private final int userId;
	private String name;
	private double balance;
	private int hoursActive;
	
	/**
	 * This constructor will set the balance to the starting value of 1 million
	 * The number of hours active will be set to zero
	 * @param userId References the id variable in the User
	 * @param name This is a combination of the player's first name and last name
	 */
	public TradingAccount(int userId, String name) {
		
		this.userId = userId;
		this.name = name;
		balance = STARTING_MONEY;
		hoursActive = 0;
	}
	
	/**
	 * @param userId References the id variable in the User
	 * @param name This is a combination of the player's first name and last name
	 * @param balance The balance saved in TradingAccounts database table is to be inserted here
	 * @param hoursActive the hours in the TradingAccounts database table is to be inserted here
	 */
	public TradingAccount(int userId, String name, double balance, int hoursActive) {
		
		this.userId = userId;
		this.name = name;
		this.balance = balance;
		this.hoursActive = hoursActive;
	}
	
	/**
	 * Accessor
	 * @return returns the id of the user account
	 */
	public int getId() {
		return userId;
	}

	/**
	 * Accessor
	 * @return returns the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Accessor
	 * @return returns the number of hours that the trading account has been in the database since creation
	 */
	public int getHoursActive() {
		return hoursActive;
	}

	/**
	 * Accessor
	 * @return returns how much money the player currently has on their trading account
	 */
	public double getBalance() {
		return balance;
	}
	
	/* end of Accessors */
	
	/**
	 * Increments the number of hours player by 1
	 * @deprecated due to database implementation
	 */
	public void incrementHours() {
		hoursActive++;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account Details\n" +	
			   "---------------\n" +
			   "Name: " + name +
			   "\nBalance: " + balance + 
			   "\nHours active: "+ hoursActive;
	}

}