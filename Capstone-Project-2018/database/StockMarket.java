/**
 * This is the main model class containing the lists of everything.
 * Methods that talk to other model classes will go here.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.*;

public class StockMarket {

	private static StockMarket market; // singleton object
	
	private String dbURL = "jdbc:derby:Database;create=true;user='s3488361;password=password"; /* This needs to be changed */
    private Connection connec = null; /* Instance */
    private Statement statem = null;
    
    // returns a singleton instance of StockMarket class
    public static StockMarket getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	if (market == null)
    		market = new StockMarket();
    	
    	return market;
    }

    // constructor
	private StockMarket() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        connec = DriverManager.getConnection(dbURL);
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


	public User getUser(String userName) {
		return null;
		
	}
	
	public TradingAccount getTradingAccount(String userName) {
		return null;
		
	}


	public Transaction getTransaction(String id) {
		return null;
	
	}

	// This method will be called each hour to update the prices in the stock market
	public void updateMarket() {	
		// TODO - implement StockMarket.updateMarket
		
	}
	

	public void buyStockFromMarket(TradingAccount account, Stock stock, int amount) {
		// TODO - implement StockMarketSystem.buyStockFromMarket
		
	}


	public void sellStock(TradingAccount sellAccount, TradingAccount buyAccount, Stock stock, 
			int amount, float pricePerStock) {
		// TODO - implement StockMarketSystem.sellStock
		
	}

	public void transferFunds(int sender, int receiver) {
		// TODO - implement StockMarketSystem.transferFunds
		
	}

}