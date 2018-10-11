package model;

import database.DerbyDB;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TradingAccount {
	
	public final double STARTING_MONEY = 1000000.00;

	private final String email;
	private String name;
	private double balance;
	private int hoursActive;
	
	private List<ValueTimeStamp> valueHistory;
	private List<Stock> stocksOwned;	
	private List<Transaction> transactionHistory;

	public TradingAccount(String email, String name) {
		
		this.email = email;
		this.name = name;
		balance = STARTING_MONEY;
		hoursActive = 0;
		stocksOwned = new ArrayList<Stock>();
		valueHistory = new LinkedList<ValueTimeStamp>();
		transactionHistory = new ArrayList<Transaction>();
	}
	
	// new constructor	
	public TradingAccount(String email, String name, double balance, int hoursActive, List<ValueTimeStamp> valueHistory,
			List<Stock> stocksOwned, List<Transaction> transactionHistory) {
		
		this.email = email;
		this.name = name;
		this.balance = balance;
		this.hoursActive = hoursActive;
		this.valueHistory = valueHistory;
		this.stocksOwned = stocksOwned;
		this.transactionHistory = transactionHistory;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	
	public int getHoursActive() {
		return hoursActive;
	}

	public List<Stock> getStocksOwned() {
		return this.stocksOwned;
	}

	public double getBalance() {
		return balance;
	}
	
	/** end of Accessors **/
	
	public void incrementHours() {
		hoursActive++;
	}

	public void addFunds(float amount)
	{
		balance += amount;
	}

	public void removeFunds(float amount)
	{
		balance -= amount;
	}

	public void BuyStock(Stock stock)
	{
		/*
		if (balance >= Stock.amount() * Stock.quantity)
		{
		    removeFunds("stock amount() * Stock.quantity");
		    DerbyDB.getConnection();
		*/
		stocksOwned.add(stock);

	}

	public void sellStock(Stock stock, float price) {
		stocksOwned.remove(stock);
		balance += price;
	}
	
	public void sellStock(int index, float price) {
		stocksOwned.remove(index);
		balance += price;
	}

	public List<Transaction> getTransactions() {
		return transactionHistory;
	}

	public List<ValueTimeStamp> getValueHistory() {
		return valueHistory;
	}
	
	public String toString() {
		return "Account Details\n" +	
			   "---------------\n" +
			   "Name: " + name +
			   "\nBalance: " + balance + 
			   "\nHours active: "+ hoursActive +
			   "\nValues" + valueHistory.toString() +
			   "\nStocks" + stocksOwned.toString() +
			   "\nTransactions" + transactionHistory.toString();
	}

}