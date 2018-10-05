package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import main.DataGenerator;
import model.Company;
import model.Model;
import model.Stock;
import model.StockMarket;
import model.TradingAccount;
import model.Transaction;
import model.User;

class ModelTest {
	
	static Model model;
	
//	@Before
//	void begin() {
//		Model model = Model.getInstance();
//	}

	@Test
	void addUsers() {
		
		try {
			DataGenerator.addUsers();
		}
		catch(Exception e) {
			fail(""+e.getStackTrace());
		}
	}
	
	@Test
	void createCompanies() {
		
		try {
			DataGenerator.createCompanies();
			
			List<Company> companies = model.getCompanies();			
			assert(companies != null && companies.size() > 0);
			
			System.out.println("Companies");
			System.out.println("-------");
			for(Company company : companies)
				System.out.println(company.toString());
			System.out.println();
		}
		catch(Exception e) {
			e.printStackTrace();
			fail(""+e.getStackTrace());
		}
		
	}
	
	@Test
	void addStockToCompanies() {
		try {
			DataGenerator.addStockToCompanies();
			
			List<Stock> stocks = model.getStocks();
			assert(stocks != null && stocks.size() > 0);
			
			System.out.println("Stocks");
			System.out.println("------");
			for(Stock stock : stocks)
				System.out.println(stock.toString());
			System.out.println();
			
		}
		catch(Exception e) {
			fail(""+e.getStackTrace());
		}
	}
	
	@Test
	void makeTransaction() {
		
		try {			
			// gets model and stock market singleton
			Model model = Model.getInstance();
			StockMarket market = StockMarket.getInstance();
			
			// searches Arraylist for matches			
			TradingAccount buyer = model.getTradingAccount("s3449513@student.rmit.edu.au");
			TradingAccount seller = model.getTradingAccount("admin@asx.com.au");
			Stock stock = model.getStock("A2M", 0);
			Company company = model.getCompany("A2M");
		
			// create the transaction
			model.addTransaction(new Transaction(1, buyer, seller, stock, LocalDateTime.now(),
					company.getMarketPrice()));
			
			// transfer the money
			market.transferFunds(buyer, seller, company.getMarketPrice());
			
			List<Transaction> transactions = model.getTransactionHistory();
			assert(transactions != null && transactions.size() > 0);			
			
			System.out.println("Transactions");
			System.out.println("------------");
			for(Transaction transaction : transactions)
				System.out.println(transaction.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(""+ e.getStackTrace());
		}
 	}
	
	@Test
	void showData() {
		try {
			
			model = Model.getInstance();
			
			List<User> users = model.getUsers();
			
			assert(users != null && users.size() > 0);

			System.out.println("Users");
			System.out.println("-----");
			for(User user : users) {
				System.out.print(user.toString());
				System.out.println("Balance: $" + user.getTradingAccount().getBalance());
				System.out.println();		
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			fail(""+e.getStackTrace());
		}
	}
}
