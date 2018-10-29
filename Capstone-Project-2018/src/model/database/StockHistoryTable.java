package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.Stock;
import model.StockTimeStamp;

public class StockHistoryTable extends DatabaseTable{
	
	private static StockHistoryTable stockHistory;
	private Connection connection;
	
	public static StockHistoryTable getInstance() {
		if(stockHistory == null) {
			stockHistory = new StockHistoryTable();	
		}	
		
		return stockHistory;
	}
	
	public StockHistoryTable() {
		connection = super.getConnection();
	}
	
	// Returns every row from the StockHistory table
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
	
	// returns all rows in the StockHistory table matching a company's code
	public List<StockTimeStamp> getStockHistory(String companyCode) throws SQLException {
		
		List<StockTimeStamp> timestamps = new LinkedList<StockTimeStamp>();
		
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
	
	public void addTimeStamp(Stock stock) throws SQLException {
	
		// SQL update transaction goes here 
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Stock_History (COMPANY_ID, DATE_TIME, MARKET_PRICE) \n" +
				"VALUES ( ? , CURRENT_TIMESTAMP, ?)");
		
		statement.setString(1, stock.getCode());
		statement.setDouble(2, stock.getMarketPrice());
		
		statement.execute();
		statement.close();
	}
	
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
