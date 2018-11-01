package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.Stock;
import model.TradingAccount;
import model.database.StocksTable;
import model.database.TradingAccountsTable;

class BuySellStock {

	@Test
	void buyFromMarket() {
		
		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
		StocksTable stocks = StocksTable.getInstance();

		assert(accounts != null);
		assert(stocks != null);
		
		try {
			TradingAccount admin = accounts.getTradingAccount("admin@asx.com.au");
			TradingAccount paul = accounts.getTradingAccount("s3449513@student.rmit.edu.au");
				
			Stock stock = stocks.getStock("A2M");
			
			stocks.transferStock(paul, admin, "A2M", 1, (float) stock.getMarketPrice());
			
		} catch (SQLException | NullPointerException e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	void sellToMarket() {
		
		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
		StocksTable stocks = StocksTable.getInstance();

		assert(accounts != null);
		assert(stocks != null);
		
		try {
			TradingAccount admin = accounts.getTradingAccount("admin@asx.com.au");
			TradingAccount paul = accounts.getTradingAccount("s3449513@student.rmit.edu.au");
				
			Stock stock = stocks.getStock("A2M");
			
			stocks.transferStock(admin, paul, "A2M", 1, (float) stock.getMarketPrice());
			
		} catch (SQLException | NullPointerException e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}