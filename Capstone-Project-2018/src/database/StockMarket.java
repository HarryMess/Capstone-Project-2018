/**
 * This is the main model class containing the lists of everything.
 * Methods that talk to other model classes will go here.
 */

package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import model.*;

public class StockMarket {

	private static StockMarket market; // singleton object
	
	/* replace with your own connection string */
    private Connection connec = null; /* Instance */
    private Statement statem = null;
    
    // returns a singleton instance of StockMarket class
    public static StockMarket getInstance() {
    	if (market == null)
			try {
				market = new StockMarket();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
    	return market;
    }

    // constructor
	private StockMarket() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
	SQLException {
        connec = DerbyDB.getConnection();			
	}
    
	// This method gets called as soon as the game begins
    public void generateStartingPrices(float lowestCost, float highestCost) {
    	
    	Random random  = new Random(); // random number generator object
    	
    	try {
    	
	    	// code to get stocks from database goes here
    		statem = connec.createStatement();
    		ResultSet results = statem.executeQuery("SELECT * FROM Stock");
    		
    		// iterate through the table
    		while(results.next()) {
    			
    			String companyCode = results.getString(1);		
	    		float price = random.nextInt((int) (highestCost - lowestCost)) + lowestCost;	    		 		
    		
	    		// SQL update transaction goes here 
				statem = connec.createStatement();
				statem.execute("INSERT INTO Stock_History (COMPANY_ID, DATE_TIME, MARKET_PRICE) \n" +
								"VALUES (" + companyCode + ", CURRENT_TIMESTAMP, "+ price + ")");
				statem.close();
	    	}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}	
    	
    }

	public Stock getStocks(String id) {	
		
		try {
			statem = connec.createStatement();			
			statem.execute(""); // insert SQL query here
			statem.close();
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	public Company getCompany(String id) {
		return null;
	}


	// returns a user object based on the the user name
	public User getUser(int id) throws SQLException {
		
		User user;
		
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Users WHERE id = ?");
		
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		String email = result.getString("email");
		String password = result.getString("password");
		boolean isAdmin = result.getBoolean("isAdmin");
		
		// get the player name from the trading account
		TradingAccount account = getTradingAccount(id);
		
		// create new user based on its type		
		if(isAdmin)
			user = new Admin(email, password, account);
		else
			user = new User(email, password, account);		
		return user;
	}
	
	// 
	public TradingAccount getTradingAccount(int userId) throws SQLException {
		
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Trading_Accounts WHERE User_id = ?");
		
		statement.setInt(1, userId);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		// get values from table
		String name = result.getString("Name");
		double balance = (double) result.getFloat("Balance");
		int hours = result.getInt("Hours_active");
		
		// run additional queries for other tables
		List<Stock> stocksOwned = TradeAccounts.getStocksOwned(userId);
		List<ValueTimeStamp> valueHistory = TradeAccounts.getValueHistory(userId);
		List<Transaction> transactions = TradeAccounts.getTransactionHistory(userId);
		
		result.close();		
		
		// return new trading account object containing all matching values
		return new TradingAccount(userId, name, balance, hours, valueHistory, stocksOwned, transactions);
	}
	
	// 
	public TradingAccount getTradingAccount(String email) throws SQLException {
		
		PreparedStatement statement = connec.prepareStatement(
				"SELECT User_Id, Name, Balance, Hours_active FROM Trading_Accounts, Users\n" + 
				"WHERE Trading_accounts.user_id = Users.id\n" + 
				"AND Users.email = ?");
		
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		// get values from table
		int userId = result.getInt("user_id");
		double balance = (double) result.getFloat("Balance");
		int hours = result.getInt("Hours_active");
		
		// run additional queries for other tables
		List<Stock> stocksOwned = TradeAccounts.getStocksOwned(userId);
		List<ValueTimeStamp> valueHistory = TradeAccounts.getValueHistory(userId);
		List<Transaction> transactions = TradeAccounts.getTransactionHistory(userId);
		
		result.close();		
		
		// return new trading account object containing all matching values
		return new TradingAccount(userId, email, balance, hours, valueHistory, stocksOwned, transactions);		
	}

	// returns a transaction based on an id
	public Transaction getTransaction(String id) {
		return null;
	}

	// This method will be called each hour to update the prices in the stock market
	public void updateMarket() {	
		// TODO - implement StockMarket.updateMarket
		
	}
	
	// Transfers stock ownership from one account to the next
	public boolean transferStock(TradingAccount buyer, TradingAccount seller,  Stock stock, 
			int amount, float price) {
		
		try {			
			PreparedStatement statement = connec.prepareStatement("UPDATE TABLE stock "
																+ "SET Trade_Account_id = ? "
																+ "WHERE Owner = ? AND Company = ?");											
			statement.setInt(1, buyer.getId());
			statement.setInt(2, seller.getId());
			statement.setString(3, stock.getCompany().getCode());
			
			// run the query
			statement.execute();
			statement.close();
			
			transferFunds(buyer, seller, price);
			addTransaction(
					new Transaction(new Timestamp(System.currentTimeMillis()), buyer, seller, stock, price)
			);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		return false;
	}

	// 
	public boolean transferFunds(TradingAccount sender, TradingAccount receiver, float amount) {
		
		try {
			PreparedStatement statement = connec.prepareStatement("UPDATE TABLE Trade_Accounts"
																+ "SET balance = balance - ?"
																+ "WHERE email = ?"
																+ "SET balance = balance + ?"
																+ "WHERE email = ?");			
			// attempt to execute the query
			if(statement.execute()) {
				statement.close();
				return true;
			}
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
		
	}
	
	// 
	public boolean addTransaction(Transaction transaction) {
		try {
			PreparedStatement statement = connec.prepareStatement("INSERT INTO TRANSACTION Transaction VALUES (?, ?, ?, ?, ?, ?)");
			
			statement.setTimestamp(1, transaction.getTimestamp());
			statement.setString(2, transaction.getBuyerId());
			statement.setString(3, transaction.getSellerId());
			statement.setString(4, transaction.getStockId());
			statement.setFloat(5, transaction.getPrice());
			
			// attempt to execute the query
			if(statement.execute()) {
				statement.close();			
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
		
	}

}