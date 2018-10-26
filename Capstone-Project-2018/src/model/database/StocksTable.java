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

import model.Company;
import model.Stock;
import model.TradingAccount;
import model.Transaction;

public class StocksTable extends DatabaseTable {
	
	private static StocksTable stocks;
	private Connection connection;
	private TransactionsTable transactions;
	
	public static StocksTable getInstance() {
		if(stocks == null) {
			stocks = new StocksTable();			
		}		
		return stocks;
	}
	
	private StocksTable() {
		connection = super.getConnection();
		transactions = TransactionsTable.getInstance();
	}
	
	@Override
	public List<Stock> getAll() throws SQLException {
		
		try {
			Statement statem = connection.createStatement();			
			statem.execute(""); // insert SQL query here
			statem.close();
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
    		
    		stocksOwned.add(new Stock(code, userId, 1));
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
    		int totalShares = 1;
    		
    		Company company = new Company(code, name, totalShares);
    		
    		stocksOwned.add(new Stock(company, totalShares));
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
 			statement.setString(3, stock.getCompany().getCode());
 			
 			// run the query
 			statement.execute();
 			statement.close();
 			
 			accounts.transferFunds(buyer, seller, price);
 			transactions.addTransaction(
 					new Transaction(new Timestamp(System.currentTimeMillis()), buyer, seller, stock, price)
 			);
 			
 		} catch (SQLException e) {
 			
 			e.printStackTrace();
 		}
 			
 		return false;
 	}
 	
	public Company getCompany(String id) {
		return null;
	}
 	
 	// This method gets called as soon as the game begins
    public void generateStartingPrices(float lowestCost, float highestCost) {
    	
    	Random random  = new Random(); // random number generator object
    	
    	try {
    	
	    	// code to get stocks from database goes here
    		Statement statem = connection.createStatement();
    		ResultSet results = statem.executeQuery("SELECT * FROM Stock");
    		
    		// iterate through the table
    		while(results.next()) {
    			
    			String companyCode = results.getString(1);		
	    		float price = random.nextInt((int) (highestCost - lowestCost)) + lowestCost;	    		 		
    		
	    		// SQL update transaction goes here 
				statem = connection.createStatement();
				statem.execute("INSERT INTO Stock_History (COMPANY_ID, DATE_TIME, MARKET_PRICE) \n" +
								"VALUES (" + companyCode + ", CURRENT_TIMESTAMP, "+ price + ")");
				statem.close();
	    	}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
    	
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
