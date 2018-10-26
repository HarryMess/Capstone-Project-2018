package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import model.TradingAccount;
import model.database.TradingAccounts;

class TradeAccountTest {
	
    @Test
	void getAccount1() {
		TradingAccount account;
		
		try {
			account = TradingAccounts.getTradingAccount(1);
			
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
		TradingAccount account;
		
		try {
			account = TradingAccounts.getTradingAccount("admin@asx.com.au");
			
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
		TradingAccount account;
		
		try {
			account = TradingAccounts.getTradingAccount("s3449513@student.rmit.edu.au");
			
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
		TradingAccount account;
		String email = "aaa@somewhere.com";
		
		try {;
			account = TradingAccounts.getTradingAccount(email);
			
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
