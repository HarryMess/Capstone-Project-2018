package model;

public class TradingAccount {
	
	public final double STARTING_MONEY = 1000000.00;

	private final int userId;
	private String name;
	private double balance;
	private int hoursActive;
	
	public TradingAccount(int userId, String name) {
		
		this.userId = userId;
		this.name = name;
		balance = STARTING_MONEY;
		hoursActive = 0;
	}
	
public TradingAccount(int userId, String name, double balance, int hoursActive) {
		
		this.userId = userId;
		this.name = name;
		this.balance = balance;
		this.hoursActive = hoursActive;
	}
	
	public int getId() {
		return userId;
	}

	public String getName() {
		return name;
	}
	
	public int getHoursActive() {
		return hoursActive;
	}

	public double getBalance() {
		return balance;
	}
	
	/** end of Accessors **/
	
	public void incrementHours() {
		hoursActive++;
	}
	
	public String toString() {
		return "Account Details\n" +	
			   "---------------\n" +
			   "Name: " + name +
			   "\nBalance: " + balance + 
			   "\nHours active: "+ hoursActive;
	}

}