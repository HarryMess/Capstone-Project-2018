package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import model.Stock;
import model.database.StocksTable;

class GeneratePrices {

	@Test
	void generateStartingPrices() {
		
		StocksTable stocksTable = StocksTable.getInstance();		
		
		assert(stocksTable != null);
		
		try {
//			stocks.generateStartingPrices(10000, 100000);
			
			List<Stock> stocks = stocksTable.getAll();
			
			for(Stock stock : stocks) {
				System.out.println(stock.toString());
			}
			
		} catch(SQLException | NullPointerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
