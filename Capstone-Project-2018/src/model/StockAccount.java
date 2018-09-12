package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TradingAccount {
	
	public final float STARTING_MONEY = 1000000;

	private float balance;
	private List<Float> valueHistory;
	private List<Stock> stocksOwned;	
	private List<Transaction> transactionHistory;

	public void stockAccount() {
		
		balance = STARTING_MONEY;
		stocksOwned = new ArrayList<Stock>();
		valueHistory = new LinkedList<Float>();
		transactionHistory = new ArrayList<Transaction>();
	}

	public List<Stock> getStocksOwned() {
		return this.stocksOwned;
	}

	public float getBalance() {
		return balance;
	}

	/**
	 * 
	 * @param amount
	 */
	public void addFunds(float amount) {
		balance += amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void removeFunds(float amount) {
		balance -= amount;
	}

	/**
	 * 
	 * @param stock
	 */
	public void BuyStock(Stock stock) {
		// TODO - implement TradingAccount.BuyStock
		stocksOwned.add(stock);
	}

	/**
	 * 
	 * @param stock
	 */
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