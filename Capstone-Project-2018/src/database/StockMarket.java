/**
 * This is the main model class containing the lists of everything.
 * Methods that talk to other model classes will go here.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.*;

public class StockMarket {

	private static StockMarket market; // singleton object
	
	private String dbURL = ""; 
	
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
		//Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        connec = DerbyDB.getConnection();
			
	}
    
	// This method gets called as soon as the game begins
    public void generateStartingPrices(float lowestCost, float highestCost) {
    	
    	Random random  = new Random(); // random number generator object
    	
    	List<Company> companies = new ArrayList<Company>();
    	
    	try {
    	
	    	// code to get stocks from database goes here
    		statem = connec.createStatement();
    		ResultSet results = statem.executeQuery("SELECT * FROM Stock");
	    	// iterate through list generating a new price for each stock
    		
    		// iterate through the table
    		while(results.next()) {
    			
    			String companyCode = results.getString(1);		
	    		float price = random.nextInt((int) (highestCost - lowestCost)) + lowestCost;	    		 		
    		
	    		// SQL update transaction goes here 
				statem = connec.createStatement();
				statem.execute("INSERT INTO Stock_History (COMPANY_ID, DATE_TIME, MARKET_PRICE) \n" +
								"VALUES (" + results.getString(1) + ", CURRENT_TIMESTAMP, "+ price + ")");
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


	// returns a user object based on the the username
	public User getUser(String username) throws SQLException {
		
		User user;
		
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Users WHERE Email = ?");
		
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		String email = result.getString("email");
		String password = result.getString("password");
		boolean isAdmin = result.getBoolean("isAdmin");
		
		// get the player name from the trading account
		TradingAccount account = getTradingAccount(username);
		
		// create new user based on its type		
		if(isAdmin)
			user = new Admin(email, password, account);
		else
			user = new User(email, password, account);		
		return user;
	}
	
	public TradingAccount getTradingAccount(String user) throws SQLException {
		
		PreparedStatement statement = connec.prepareStatement(
				"SELECT * FROM Trade_Accounts WHERE Email = ?");
		
		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		
		result.next(); // gets the matching result
		
		// get values from table
		String name = result.getString("Email");
		double balance = (double) result.getFloat("Balance");
		int hours = result.getInt("Hours_active");
		
		// run additional queries for other tables
		List<Stock> stocksOwned = TradeAccounts.getStocksOwned(user);
		List<ValueTimeStamp> valueHistory = TradeAccounts.getValueHistory(user);
		List<Transaction> transactions = TradeAccounts.getTransactionHistory(user);
		
		result.close();		
		
		// return new trading account object containing all matching values
		return new TradingAccount(user, name, balance, hours, valueHistory, stocksOwned, transactions);
	}

	// returns a transaction based on an id
	public Transaction getTransaction(String id) {
		return null;
	}

	// This method will be called each hour to update the prices in the stock market
	public void updateMarket() {	
		// TODO - implement StockMarket.updateMarket
		
	}
	
	// transfers stock ownership from onw account to the next
	public boolean transferStock(TradingAccount buyer, TradingAccount seller,  Stock stock, 
			int amount, float price) {
		
		try {
			
			PreparedStatement statement = connec.prepareStatement("UPDATE TABLE stock "
																+ "SET Trade_Account_id = ? "
																+ "WHERE Owner = ? AND Company = ?");											
			statement.setString(1, buyer.getEmail());
			statement.setString(2, seller.getEmail());
			statement.setString(3, stock.getCompany().getCode());
			
			// run the querey
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