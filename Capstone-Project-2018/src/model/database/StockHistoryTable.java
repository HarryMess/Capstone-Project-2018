package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockHistoryTable extends DatabaseTable{
	
	private static StockHistoryTable stockHistory;
	private Connection connection;
	
	public static StockHistoryTable getInstance() {
		if(stockHistory == null) {
			stockHistory = new StockHistoryTable();			
		}		
		return stockHistory;
	}
	
	private StockHistoryTable() {
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

}
