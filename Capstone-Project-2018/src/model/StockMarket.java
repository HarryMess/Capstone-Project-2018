/**
 * This is the main model class containing the lists of everything.
 * Methods that talk to other model classes will go here.
 */

package model;

import java.util.List;

public class StockMarket {

	private static StockMarket stockMarket;	
	
	private List<User> users;
	private List<Company> companies;
	private List<Stock> stocks;	
	private List<Transaction> transactionHistory;
	
	public static StockMarket getInstance() {

		if (stockMarket == null) {
			Model model = Model.getInstance();
			stockMarket = new StockMarket(model.getUsers(), model.getCompanies(),
						  model.getStocks(), model.getTransactionHistory());
		}
		
		return stockMarket;
	}

	private StockMarket(List<User> users, List<Company> companies, 
			List<Stock> stocks, List<Transaction> transactionHistory) {
		
		this.companies = companies;
		this.stocks = stocks; // need to decide how we want to implement this
		this.users = users;
		this.transactionHistory = transactionHistory;
	}

	/**
	 * 
	 * @param id
	 * @throws NotFoundException 
	 */	
	public Stock getStock(String code, int ownerId) throws NotFoundException {
		
		for(Stock s : stocks) {
			if(s.getCompany() == code && s.getOwner() == ownerId)
				return s;
		}
		
		throw new NotFoundException("No stocks from company '" + code + 
				"' is owned by id '" + ownerId + "'");
	}
	
	public Company getCompany(String id) {
		
		for(Company c : companies) {
			if(c.getCode().equals(id))
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
			if(u.getEmail().equals(userName))
				return u;
		}
		
		throw new NullPointerException("Error: Username '" + userName + "' was not found.");
	}
	
	public TradingAccount getTradingAccount(String userName) {
		
		for(User u : users) {
			if(u.getEmail().equals(userName))
				return u.getTradingAccount();
		}
		
		throw new NullPointerException("Error: Username '" + userName + "' was not found.");
	}

	/**
	 * 
	 * @param id
	 */
	public Transaction getTransaction(int id) {
		
		for(Transaction t : transactionHistory) {
			if(t.getTranasctionId() == id)
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
	public void transferFunds(int sender, int receiver, float amount) {
		// TODO - implement StockMarketSystem.transferFunds
		
	}
	
	public void transferFunds(TradingAccount sender, TradingAccount receiver, float amount) {
		sender.removeFunds(amount);
		receiver.addFunds(amount);
	}

}