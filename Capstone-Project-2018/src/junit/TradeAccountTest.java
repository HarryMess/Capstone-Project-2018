package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import model.TradingAccount;
import model.database.TradingAccountsTable;

class TradeAccountTest {
	
    @Test
	void getAccount1() {
		TradingAccount account;
		TradingAccountsTable tradingAccounts = TradingAccountsTable.getInstance();
		
		try {
			account = tradingAccounts.getTradingAccount(1);
			
			assert(account != null);
			
			System.out.println(account.toString());
			
		} catch (SQLException | NullPointerException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
    
	@Test
	void getAccountStockMarket() {
		
		// get the main market trading account
		TradingAccount account;
		TradingAccountsTable tradingAccounts = TradingAccountsTable.getInstance();
		
		try {
			account = tradingAccounts.getTradingAccount("admin@asx.com.au");
			
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
		TradingAccountsTable tradingAccounts = TradingAccountsTable.getInstance();
		
		try {
			account = tradingAccounts.getTradingAccount("s3449513@student.rmit.edu.au");
			
			assert(account != null);
			
			System.out.println(account.toString());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	void getNonExistantAccount() {
		TradingAccountsTable tradingAccounts = TradingAccountsTable.getInstance();
		
		// get the main market trading account
		TradingAccount account;
		String email = "aaa@somewhere.com";
		
		try {;
			account = tradingAccounts.getTradingAccount(email);
			
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
		catch(NullPointerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
