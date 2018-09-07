/**
 * This is the main model class containing the lists of everything.
 * Methods that talk to other model classes will go here.
 */

package model;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {

	private List<User> users;
	private List<Company> companies;
	private List<Stock> stocks;	
	private List<Transaction> transactionHistory;

	/**
	 * 
	 * @param companies
	 */
	public StockMarket(List<Company> companies) {
		this.companies = companies;
		stocks = new ArrayList<Stock>(); // need to decide how we want to implement this
		users = new ArrayList<User>();
		transactionHistory = new ArrayList<Transaction>();
	}

	/**
	 * 
	 * @param id
	 */	
	public Stock getStocks(String id) {
		
		for(Stock s : stocks) {
			if(s.getId().equals(id))
				return s;
		}
		
		throw new NullPointerException("Error: Stock id'" + id + "' was not found.");
	}
	
	public Company getCompany(String id) {
		
		for(Company c : companies) {
			if(c.getId().equals(id))
				return c;
		}
			
		throw new NullPointerException("Error: Company id'" + id + "' was not found.");	
	}

	/**
	 * 
	 * @param id
	 */
	public User getUser(String userName) {
		
		for(User u : users) {
			if(u.getUserName().equals(userName))
				return u;
		}
		
		throw new NullPointerException("Error: Username '" + userName + "' was not found.");
	}
	
	public TradingAccount getTradingAccount(String userName) {
		
		for(User u : users) {
			if(u.getUserName().equals(userName))
				return u.getTradingAccount();
		}
		
		throw new NullPointerException("Error: Username '" + userName + "' was not found.");
	}

	/**
	 * 
	 * @param id
	 */
	public Transaction getTransaction(String id) {
		
		for(Transaction t : transactionHistory) {
			if(t.getTranasctionId().equals(id))
				return t;
		}
			
		throw new NullPointerException("Error: Transaction id'" + id + "' was not found.");	
	}

	// This method will be called each hour to update the prices in the stock market
	public void updateMarket() {	
		// TODO - implement StockMarket.updateMarket
		
	}
	
	/**
	 * 
	 * @param account
	 * @param stock
	 */
	public void buyStockFromMarket(TradingAccount account, Stock stock, int amount) {
		// TODO - implement StockMarketSystem.buyStockFromMarket
		
	}

	/**
	 * 
	 * @param sellAcount
	 * @param buyAccount
	 * @param stock
	 */
	public void sellStock(TradingAccount sellAccount, TradingAccount buyAccount, Stock stock, 
			int amount, float pricePerStock) {
		// TODO - implement StockMarketSystem.sellStock
		
	}

	/**
	 * 
	 * @param sender
	 * @param receiver
	 */
	public void transferFunds(int sender, int receiver) {
		// TODO - implement StockMarketSystem.transferFunds
		
	}

}