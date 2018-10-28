package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Company;
import model.database.StocksTable;

class GeneratePrices {

	@Test
	void generateStartingPrices() {
		
		StocksTable stocks = StocksTable.getInstance();		
		
		assert(stocks != null);
		
		try {
			stocks.generateStartingPrices(10000, 100000);
			
			List<Company> companies = stocks.getCompanies();
			
			for(Company company : companies) {
				System.out.println(company.toString());
			}
			
		} catch(SQLException | NullPointerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
