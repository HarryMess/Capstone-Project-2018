package model.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockHistory extends DatabaseTable{

	Connection connection = DatabaseTable.getConnection();
	
	@Override
	public List<?> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
