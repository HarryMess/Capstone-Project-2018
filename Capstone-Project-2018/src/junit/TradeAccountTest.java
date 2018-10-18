package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.DerbyDB;
import database.StockMarket;
import database.TradeAccounts;
import model.TradingAccount;

class TradeAccountTest {
	
    private Connection connec = DerbyDB.getConnection(); /* Instance */
    private Statement statem = null;
	
    @Test
	void getAccount1() {
		StockMarket market;
		TradingAccount account;
		
		try {
			market = StockMarket.getInstance();
			account = TradeAccounts.getTradingAccount(1);
			
//			assert(account != null);
			
			System.out.println(account.toString());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
    
	@Test
	void getAccountStockMarket() {
		
		// get the main market trading account
		StockMarket market;
		TradingAccount account;
		
		try {
			market = StockMarket.getInstance();
			account = TradeAccounts.getTradingAccount("admin@asx.com.au");
			
			assert(account != null);
			
			System.out.println(account.toString());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	void getAccountPaul() {
		// get the main market trading account
		StockMarket market;
		TradingAccount account;
		
		try {
			market = StockMarket.getInstance();
			account = TradeAccounts.getTradingAccount("s3449513@student.rmit.edu.au");
			
			assert(account != null);
			
			System.out.println(account.toString());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	void getNonExistantAccount() {
		// get the main market trading account
		StockMarket market;
		TradingAccount account;
		String email = "aaa@somewhere.com";
		
		try {
			market = StockMarket.getInstance();
			account = TradeAccounts.getTradingAccount(email);
			
			assert(account != null);
			
			System.out.println(account.toString());
			
		} catch (SQLException e) {
			
			if(e.getMessage().equals("Invalid cursor state - no current row.")) {
				
				System.out.println("No trade account for the email address " + email
				+ " was found.");
				
			} else {
				
				e.printStackTrace();
				fail(e.getMessage());
			}
		}	
	}

}
