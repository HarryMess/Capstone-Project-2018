package model.database.staticmethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Company;

public class StockHistory{
	
	private static Connection connection = Database.getConnection();
	
	public static List<StockHistory> getAll() throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public static void addValueTimeStamp(Company company) throws SQLException {
	
		// SQL update transaction goes here 
		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Stock_History (COMPANY_ID, DATE_TIME, MARKET_PRICE) \n" +
				"VALUES ( ? , CURRENT_TIMESTAMP, ?)");
		
		statement.setString(1, company.getCode());
		statement.setDouble(2, company.getMarketPrice());
		
		statement.execute();
		statement.close();
	}
	
	public static void addValueTimeStamp(String code, double price) throws SQLException {
		
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
