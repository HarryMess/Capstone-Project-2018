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

public class StocksTable extends DatabaseTable {
	
	private static StocksTable stocks;
	private Connection connection;
	private TransactionsTable transactions;
	private StockHistoryTable stockHistory;
	
	public static StocksTable getInstance() {
		if(stocks == null) {
			stocks = new StocksTable();				
		}
		
		return stocks;
	}
	
	public StocksTable() {
		connection = super.getConnection();
		transactions = TransactionsTable.getInstance();
		stockHistory = StockHistoryTable.getInstance();
	}
	
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
	
	// returns an individual stock object by finding the matching code in the database
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
//    		
//    		Company company = new Company(code, name, totalShares);
    		
    		stocksOwned.add(new Stock(code, name, ownerId, marketPrice));
    	}
    	
    	return stocksOwned;
    }
    
    // Transfers stock ownership from one account to the next
 	public boolean transferStock(TradingAccount buyer, TradingAccount seller,  Stock stock, 
 			int amount, float price) {
 		
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
 			
 			accounts.transferFunds(buyer, seller, price);
 			transactions.addTransaction(
 					new Transaction(new Timestamp(System.currentTimeMillis()), 
 							buyer.getId(), seller.getId(), stock.getCode(), price, 1)
 			);
 			
 		} catch (SQLException e) {
 			
 			e.printStackTrace();
 		}
 			
 		return false;
 	}
 	
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
 	public void updateMarket() {	
 		// TODO - implement StockMarket.updateMarket
 		
 	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
 	
}
