package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Stock;
import model.TradingAccount;
import model.Transaction;

/**
 * Contains methods that run SQL queries relevant to the Stocks Table
 * Buy and selling of stock as well as updating the prices and dividend is also handled here
 * @author Paul King - s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class StocksTable extends DatabaseTable {
	
	private static StocksTable stocks;
	private Connection connection;
	private TransactionsTable transactions;
	private StockHistoryTable stockHistory;
	
	/**
	 * Used to get the static object of this class
	 * If the value is null it will be instantiated using a private constructor
	 * @return gets a singleton instance of this class
	 */
	public static StocksTable getInstance() {
		if(stocks == null) {
			stocks = new StocksTable();				
		}
		
		return stocks;
	}
	
	/**
	 * Constructor. Retrieves the connection along with the transactions and stockhistory table classes
	 */
	private StocksTable() {
		connection = super.getConnection();
		transactions = TransactionsTable.getInstance();
		stockHistory = StockHistoryTable.getInstance();
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.database.DatabaseTable#getAll()
	 */
	@Override
	public List<Stock> getAll() throws SQLException {
		
		List<Stock> stocks = new ArrayList<Stock>();
		
		Statement statem = connection.createStatement();			
		ResultSet results = statem.executeQuery("SELECT * FROM STOCKS");
		
		while(results.next()) {
		
			String code = results.getString("code");
			String companyName = results.getString("name");
			int ownerId = results.getInt("owner_Id");
			int quantity = results.getInt("amount");
			float marketPrice = (float) results.getDouble("current_price");
			float profitPerHour = (float) results.getDouble("profit_per_hour");
		
			stocks.add(new Stock(code, companyName, ownerId, marketPrice, quantity, profitPerHour));
		}
		
		statem.close();
		results.close();
        
		return stocks;
	}
	
	/**
	 * Queries the Stocks table for an individual stock matching a company code
	 * @param code takes the company code as an argument. This is unique
	 * @return returns a stock object created from the result set
	 * @throws SQLException
	 */
	public Stock getStock(String code) throws SQLException {
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM STOCKS\n"
																+ "WHERE Code = ?");
		statement.setString(1, code);
		
		ResultSet results = statement.executeQuery();
		
		results.next(); // get the first match	
		
		String name = results.getString("Name");
		int ownerId = results.getInt("Owner_Id");
		float price = (float) results.getDouble("Current_Price");	
		
		return new Stock(code, name, ownerId, price);
	}
	
	/**
	 * Queries the stock table, returning all stocks owned by a particular trading account
	 * using the user account id
	 * @param userId this is the id foreign key referencing the trading account
	 * @return returns a list of the type 'Stock' built from the result set
	 * @throws SQLException
	 */
	// get stocks owned for specific user by the id
    public List<Stock> getStocksOwned(int userId) throws SQLException {
    	
    	List<Stock> stocksOwned = new ArrayList<Stock>();
    	
    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM Stocks\n" + 
    														  "WHERE Stocks.owner_id = ?");
    	
    	statement.setInt(1, userId);
    	ResultSet results = statement.executeQuery(); 
    	
    	while(results.next()) {
    		
    		String code = results.getString("Code");
    		String name = results.getString("name");
    		float price = (float) results.getDouble("current_price"); 
    		
    		stocksOwned.add(new Stock(code, name, userId, price));
    	}
    	
    	return stocksOwned;
    }
    
    /**
     * Queries the stock table, returning all stocks owned by a particular trading account
	 * using the user account email address/username
     * @param email takes the name of the user account as an argument for referencing
     * @return returns a list of the type 'Stock' built from the result set
     * @throws SQLException
     */
    // get stocks owned for specific user by the user email address
    public List<Stock> getStocksOwned(String email) throws SQLException {
    	
    	List<Stock> stocksOwned = new ArrayList<Stock>();
    	
    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM Stocks\n" + 
    			"INNER JOIN Trading_Accounts ON Stocks.owner_id = Trading_Accounts.user_id\n" + 
    			"INNER JOIN Users ON Users.id = Trading_Accounts.user_id\n" + 
    			"WHERE Users.email = ?");
    	
    	statement.setString(1, email);
    	ResultSet results = statement.executeQuery(); 
    	
    	while(results.next()) {
    		
    		String code = results.getString("Code");
    		String name = results.getString("Name");
    		int ownerId = results.getInt("Owner");
    		float marketPrice = (float) results.getDouble("Current_Price");
    		
    		stocksOwned.add(new Stock(code, name, ownerId, marketPrice));
    	}
    	
    	return stocksOwned;
    }
    
    /**
     * Transfers ownership of the stock from one account to the other by updating the owner id in Stocks table
     * @param buyer The account that is purchasing the stock
     * @param seller The account that is selling the stock (Note: this can include the company themselves)
     * @param stock takes a full stock object which contains the company code
     * @param amount the amount of stocks being purchased of the specified company. Set this argument to 1
     * @param price the price of the stock determining the amount of money involved in the transaction
     * @return returns true on success, false on failure
     * @deprecated
     */
    // Transfers stock ownership from one account to the next
 	public boolean transferStock(TradingAccount buyer, TradingAccount seller, Stock stock, 
 			int amount) {
 		
 		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
 		
 		try {			
 			PreparedStatement statement = connection.prepareStatement("UPDATE TABLE stock "
 																+ "SET Trade_Account_id = ? "
 																+ "WHERE Owner = ? AND Company = ?");											
 			statement.setInt(1, buyer.getId());
 			statement.setInt(2, seller.getId());
 			statement.setString(3, stock.getCode());
 			
 			// run the query
 			statement.execute();
 			statement.close();
 			
 			accounts.transferFunds(buyer, seller, stock.getMarketPrice());
 			transactions.addTransaction(
 					new Transaction(new Timestamp(System.currentTimeMillis()), 
 							buyer.getId(), seller.getId(), stock.getCode(), stock.getMarketPrice(), 1)
 			);
 			
 		} catch (SQLException e) {
 			
 			e.printStackTrace();
 		}
 			
 		return false;
 	}
 	
 	/**
     * Transfers ownership of the stock from one account to the other by updating the owner id in Stocks table
     * @param buyer The account that is purchasing the stock
     * @param seller The account that is selling the stock (Note: this can include the company themselves)
     * @param stockCode takes the company code as a string referencing matching code in the database
     * @param amount the amount of stocks being purchased of the specified company. Set this argument to 1
     * @param price the price of the stock determining the amount of money involved in the transaction
     * @return returns true on success, false on failure
     */
 	// Transfers stock ownership from one account to the next
  	public boolean transferStock(TradingAccount buyer, TradingAccount seller, String stockCode, 
  			int amount, float price) {
  		
  		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
  		
  		try {			
  			PreparedStatement statement = connection.prepareStatement("UPDATE Stocks "
  																+ "SET Owner_id = ? "
  																+ "WHERE Owner_id = ? AND Code = ?");											
  			statement.setInt(1, buyer.getId());
  			statement.setInt(2, seller.getId());
  			statement.setString(3, stockCode);
  			
  			// run the query
  			statement.execute();
  			statement.close();
  			
  			accounts.transferFunds(buyer, seller, price);
  			transactions.addTransaction(
  					new Transaction(new Timestamp(System.currentTimeMillis()), 
  							buyer.getId(), seller.getId(), stockCode, price, 1)
  			);
  			
  			return true;
  			
  		} catch (SQLException e) {
  			
  			e.printStackTrace();
  		}
  			
  		return false;
  	}
 	
 	/**
 	 * This is called before the start of the game to generating the initial market price of stocks from each
 	 * company at random
 	 * @param lowestCost sets the minimum cost of a stock
 	 * @param highestCost sets the highest cost of a stock
 	 * @throws SQLException
 	 */
 	// This method gets called as soon as the game begins
    public void generateStartingPrices(float lowestCost, float highestCost) throws SQLException {
    	
    	Random random  = new Random(); // random number generator object
    		
    	// code to get stocks from database goes here
		Statement statement1 = connection.createStatement();
		PreparedStatement statement2;
		ResultSet results = statement1.executeQuery("SELECT * FROM Stocks");
		
		statement2 = connection.prepareStatement("UPDATE Stocks\n"
			    + "SET current_price = ? WHERE code = ?");
		
		// iterate through the table
		while(results.next()) {
			
			String code = results.getString(1);		
    		float price = random.nextInt((int) (highestCost - lowestCost)) + lowestCost;	    		
    		
    		statement2.setFloat(1, price);
    		statement2.setString(2, code);
    		
    		statement2.execute();
    		
    		stockHistory.addTimeStamp(code, price);
    	}
		
		statement1.close();
		statement2.close();				
    }
    
    // This method will be called each hour to update the prices in the stock market
 	public void updateMarket() throws SQLException {	
 		
 		// Get all the stocks from the database
 		List<Stock> stocks = getAll();
 		
 		PreparedStatement statement = connection.prepareStatement("UPDATE Stocks\n"
				+ "SET current_price = ? WHERE code = ?");
 		
 		// Iterate through the list updating the price for each stock
 		for(Stock stock : stocks) {
 			
 			// Update the list using the an algorithm
 			stock.updatePrice();
 			
 			statement.setDouble(1, stock.getMarketPrice());
 			statement.setString(2, stock.getCode());
 			
 			// run the query
 			statement.executeQuery();
 			
 			// add time to the stock history after price change
 			stockHistory.addTimeStamp(stock);
 		}
 		
 	}
 	
 	/**
 	 * Calculates the total stock value based on all the stocks owned by the trading account
	 * @param stocks takes a list of stock objects to get the prices from them
 	 * @return returns the stock value based on the calculated total
 	 */
	public double getTotalStockValue(List<Stock> stocks) {
		double stockValue = 0;
		
		for(Stock s: stocks) {
			stockValue += s.getMarketPrice();
		}
		
		return stockValue;
	}
 	
}
