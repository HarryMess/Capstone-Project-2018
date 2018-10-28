package main;

import model.database.TradingAccountsTable;
import model.database.UsersTable;

public class CreateTablesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UsersTable users = new UsersTable();
		System.out.println(users);
		
		TradingAccountsTable tradingAccounts = new TradingAccountsTable();
		System.out.println(tradingAccounts);
		
		users = new UsersTable();
		System.out.println(users);
		
	}

}
