package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.StockMarket;
import model.TradingAccount;

class TradeAccountTest {
	
	/* replace with your own connection string */
	private String dbURL = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Capstone-Project-2018\\derby-10.14.2.0\\bin\\Database;create=false;user=username"; 
    private Connection connec = null; /* Instance */
    private Statement statem = null;
	
	@Test
	void getAccountStockMarket() {
		
		// get the main market trading account
		StockMarket market;
		TradingAccount account;
		
		try {
			market = StockMarket.getInstance();
			account = market.getTradingAccount("admin@asx.com.au");
			
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
			account = market.getTradingAccount("s3449513@student.rmit.edu.au");
			
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
			account = market.getTradingAccount(email);
			
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
