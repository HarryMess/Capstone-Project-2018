package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TradingAccount {
	
	public final double STARTING_MONEY = 1000000.00;

	private final String name;
	private double balance;
	private List<Float> valueHistory;
	private List<Stock> stocksOwned;	
	private List<Transaction> transactionHistory;

	public TradingAccount(String name) {
		
		this.name = name;
		balance = STARTING_MONEY;
		stocksOwned = new ArrayList<Stock>();
		valueHistory = new LinkedList<Float>();
		transactionHistory = new ArrayList<Transaction>();
	}
	
	public String getName() {
		return name;
	}

	public List<Stock> getStocksOwned() {
		return this.stocksOwned;
	}

	public double getBalance() {
		return balance;
	}

	public void addFunds(float amount) {
		balance += amount;
	}

	public void removeFunds(float amount) {
		balance -= amount;
	}

	public void BuyStock(Stock stock) {
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

	public List<Float> getValueHistory() {
		return valueHistory;
	}

}