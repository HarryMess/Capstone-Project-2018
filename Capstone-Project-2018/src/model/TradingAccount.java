package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TradingAccount {
	
	public final double STARTING_MONEY = 1000000.00;

	private final int userId;
	private String name;
	private double balance;
	private int hoursActive;
	
//	private List<AccountTimeStamp> valueHistory;
//	private List<Stock> stocksOwned;	
//	private List<Transaction> transactionHistory;

	public TradingAccount(int userId, String name) {
		
		this.userId = userId;
		this.name = name;
		balance = STARTING_MONEY;
		hoursActive = 0;
//		stocksOwned = new ArrayList<Stock>();
//		valueHistory = new LinkedList<AccountTimeStamp>();
//		transactionHistory = new ArrayList<Transaction>();
	}
	
	// new constructor	
	public TradingAccount(int userId, String name, double balance, int hoursActive, List<AccountTimeStamp> valueHistory,
			List<Stock> stocksOwned, List<Transaction> transactionHistory) {
		
		this.userId = userId;
		this.name = name;
		this.balance = balance;
		this.hoursActive = hoursActive;
//		this.valueHistory = valueHistory;
//		this.stocksOwned = stocksOwned;
//		this.transactionHistory = transactionHistory;
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

//	public List<Stock> getStocksOwned() {
//		return this.stocksOwned;
//	}

	public double getBalance() {
		return balance;
	}
	
	/** end of Accessors **/
	
	public void incrementHours() {
		hoursActive++;
	}	

//	public List<Transaction> getTransactions() {
//		return transactionHistory;
//	}
//
//	public List<AccountTimeStamp> getValueHistory() {
//		return valueHistory;
//	}
	
	public String toString() {
		return "Account Details\n" +	
			   "---------------\n" +
			   "Name: " + name +
			   "\nBalance: " + balance + 
			   "\nHours active: "+ hoursActive; // +
//			   "\nValues" + valueHistory.toString() +
//			   "\nStocks" + stocksOwned.toString() +
//			   "\nTransactions" + transactionHistory.toString();
	}

}