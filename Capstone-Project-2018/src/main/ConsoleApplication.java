package main;

import java.util.List;

import model.Company;
import model.Model;
import model.Stock;
import model.TradingAccount;
import model.Transaction;
import model.User;

public class ConsoleApplication
{
	private User user;
	private Model model;
	
	public ConsoleApplication(User user)
	{
		this.user = user;
		System.out.print("welcome " + user.getEmail() +"\n");
		
		model = Model.getInstance();
	} 
	
	public void showCompany() 
	{
		System.out.println("Company !!!");
		
		List<Company> companies = model.getCompanies();
		for(Company company : companies)
			System.out.println(company.toString());
	}
	
	public void showMyshare()
	{
		System.out.println("Stock Owned !!!");
		
		TradingAccount uta = user.getTradingAccount();
		List<Stock> stocksOwned = uta.getStocksOwned();
		for(Stock StocksOwned : stocksOwned)
			System.out.println(StocksOwned);
		
	}
	
	public void showRecenttrans()
	{
		System.out.println("Recent Transaction !!!");
		
		TradingAccount uta = user.getTradingAccount();
		List<Transaction> transHis = uta.getTransactions();
		for(Transaction TransHis : transHis)
			System.out.println(TransHis);
			
	}	
	
}