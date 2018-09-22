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
			stockMarket = new StockMarket();
		}
		
		return stockMarket;
	}

	private StockMarket() {
		
		Model model = Model.getInstance();
		
		this.users = model.getUsers();
		this.companies = model.getCompanies();
		this.stocks = model.getStocks(); // need to decide how we want to implement this
		this.transactionHistory =  model.getTransactionHistory();
	}

	// This method will be called each hour to update the prices in the stock market
	public void updateMarket() {	
		// TODO - implement StockMarket.updateMarket
		
	}
	
	public void buyStock(TradingAccount buyer, TradingAccount seller, int amount,
			float pricePerStock) {
		// TODO - implement StockMarketSystem.buyStock
		
	}

	public void sellStock(TradingAccount sellAccount, TradingAccount buyAccount, Stock stock, 
			int amount, float pricePerStock) {
		// TODO - implement StockMarketSystem.sellStock
		
	}

	public void transferFunds(int sender, int receiver, float amount) {
		// TODO - implement StockMarketSystem.transferFunds
		
	}
	
	public void transferFunds(TradingAccount sender, TradingAccount receiver, float amount) {
		sender.removeFunds(amount);
		receiver.addFunds(amount);
	}

}