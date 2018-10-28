package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.Stock;
import model.TradingAccount;
import model.database.UsersTable;
import model.database.DatabaseTable;
import model.database.StocksTable;
import model.database.TradingAccountsTable;

class BuySellStock {

	@Test
	void test() {
		
		DatabaseTable.initilize();
		
		UsersTable users = DatabaseTable.getUsers(); // UsersTable.getInstance();
		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
		StocksTable stocks = StocksTable.getInstance();
		
		System.out.println("users: " + users);
		System.out.println("accounts: " + accounts);
		System.out.println("stocks: " + stocks);
		
		assert(users != null);
		assert(accounts != null);
		assert(stocks != null);
		
		try {
			TradingAccount admin = accounts.getTradingAccount("admin@asx.com.au");
			TradingAccount paul = accounts.getTradingAccount("s3449513@student.rmit.edu.au");
				
			Stock stock = stocks.getStock("A2M");
			
			assert(stocks.transferStock(admin, paul, "A2M", 1, stock.getCompany().getMarketPrice()));
			
		} catch (SQLException | NullPointerException e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}