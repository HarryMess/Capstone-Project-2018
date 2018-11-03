package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Stock;
import model.StockTimeStamp;

/**
 * Contains methods that run SQL queries relevant to the StockHistory Table
 * This is needed to create and return timestamps producing a line graph of the company stock price history
 * used on the company information screen.
 * @author Paul King - s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class StockHistoryTable extends DatabaseTable{
	
	private static StockHistoryTable stockHistory;
	private Connection connection;
	
	/**
	 * Used to get the static object of this class
	 * If the value is null it will be instantiated using a private constructor
	 * @return gets a singleton instance of this class
	 */
	public static StockHistoryTable getInstance() {
		if(stockHistory == null) {
			stockHistory = new StockHistoryTable();	
		}	
		
		return stockHistory;
	}
	
	/**
	 * Constructor. Retrieves the database connection.
	 */
	private StockHistoryTable() {
		connection = super.getConnection();
	}
	
	/* Returns every row from the StockHistory table
	 * (non-Javadoc)
	 * @see model.database.DatabaseTable#getAll()
	 */
	@Override
	public List<StockTimeStamp> getAll() throws SQLException {
		
		List<StockTimeStamp> timestamps = new LinkedList<StockTimeStamp>();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM STOCK_HISTORY");		
		ResultSet results = statement.executeQuery();
		
		while(results.next()) {
			Timestamp timestamp = results.getTimestamp("date_time");
			String code = results.getString("company_code");
			float price = (float) results.getDouble("market_price");
			
			timestamps.add(new StockTimeStamp(timestamp, code, price));
		}
		
		return timestamps;
	}
	
	/**
	 * retrieves all time stamps in the StockHistory table matching a company's unique code
	 * This list is used to for drawing a graph of the history on the GUI
	 * @param companyCode takes the company code as string for use in SQL query
	 * @return returns ab array list of objects from 'StockTimeStamp' built from the result set
	 * @throws SQLException
	 */
	// 
	public List<StockTimeStamp> getStockHistory(String companyCode) throws SQLException {
		
		List<StockTimeStamp> timestamps = new ArrayList<StockTimeStamp>();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM STOCK_HISTORY "
																+ "WHERE Company_Code = ?");
		statement.setString(1, companyCode);
		
		ResultSet results = statement.executeQuery();
		
		while(results.next()) {
			Timestamp timestamp = results.getTimestamp("date_time");
			String code = results.getString("company_code");
			float price = (float) results.getDouble("market_price");
			
			timestamps.add(new StockTimeStamp(timestamp, code, price));
		}
		
		return timestamps;
	}
	
	/**
	 * Adds a new timestamp to the database contianing the stock value by referencing object
	 * @param stock taks a stock object as an argument to retrieve the code and the price
	 * @throws SQLException
	 */
	public void addTimeStamp(Stock stock) throws SQLException {
	
		// SQL update transaction goes here 
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Stock_History (COMPANY_CODE, DATE_TIME, MARKET_PRICE) \n" +
				"VALUES ( ? , CURRENT_TIMESTAMP, ?)");
		
		statement.setString(1, stock.getCode());
		statement.setDouble(2, stock.getMarketPrice());
		
		statement.execute();
		statement.close();
	}
	
	/**
	 * Adds a new timestamp to the database using the company code and price
	 * @param code string reference of the company code for use in the sql query
	 * @param price decimal number for the current market price of the stock
	 * @throws SQLException
	 */
	public void addTimeStamp(String code, double price) throws SQLException {
		
		// SQL update transaction goes here 
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Stock_History (COMPANY_CODE, DATE_TIME, MARKET_PRICE) \n" +
				"VALUES ( ? , CURRENT_TIMESTAMP, ?)");
		
		statement.setString(1, code);
		statement.setDouble(2, price);
		
		statement.execute();
		statement.close();
	}
}
