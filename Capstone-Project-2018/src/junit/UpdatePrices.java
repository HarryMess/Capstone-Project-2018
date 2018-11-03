package junit;

import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import model.database.StocksTable;

class UpdatePrices {

	@Test
	void updatePrices() {
		
		StocksTable stocks = StocksTable.getInstance();
		
		try {
			stocks.updateMarket();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
