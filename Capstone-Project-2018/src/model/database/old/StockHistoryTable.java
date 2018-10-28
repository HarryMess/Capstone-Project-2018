package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Company;

public class StockHistoryTable extends DatabaseTable{
	
	private static StockHistoryTable stockHistory;
	private Connection connection;
	
	public static StockHistoryTable getInstance() {
		if(stockHistory == null) {
			stockHistory = new StockHistoryTable();	
			System.out.println("stockHistory: " + stockHistory);
		}	
		
		return stockHistory;
	}
	
	public StockHistoryTable() {
		connection = super.getConnection();
	}
	
	@Override
	public List<StockHistoryTable> getAll() throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addValueTimeStamp(Company company) throws SQLException {
	
		// SQL update transaction goes here 
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Stock_History (COMPANY_ID, DATE_TIME, MARKET_PRICE) \n" +
				"VALUES ( ? , CURRENT_TIMESTAMP, ?)");
		
		statement.setString(1, company.getCode());
		statement.setDouble(2, company.getMarketPrice());
		
		statement.execute();
		statement.close();
	}
	
	public void addValueTimeStamp(String code, double price) throws SQLException {
		
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
