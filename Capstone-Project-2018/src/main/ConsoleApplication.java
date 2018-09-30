package main;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;
import java.sql.Connection;

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
	private static Connection connec = null; /* Instance */
    private static Statement statem = null;
	
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
		
		// get account ID
		String user = this.user.getEmail();
		DecimalFormat currency = new DecimalFormat("$.##");
		
		try {
	    PreparedStatement statement = connec.prepareStatement(
					"SELECT * FROM Trade_Accounts WHERE Email = ?");	

		statement.setString(1, user);
		ResultSet result = statement.executeQuery();
		result.next(); // gets the matching result
		
		// get Id from table
		int Id = result.getInt("Id");
		
		result.close();
		statement.close();
		
		// get trans Histroy
		PreparedStatement statementh = connec.prepareStatement(
				"SELECT * FROM Trade_History WHERE Account_Id = ?");
		
		statementh.setInt(1, Id);
		ResultSet resulth = statementh.executeQuery();
		resulth.next();
		
		int sharevalue = resulth.getInt(1);
		int balance = resulth.getInt(2);
		
		System.out.println("Share value "+ sharevalue);
		System.out.println("Balance: " + balance);
		
		resulth.close();
		statementh.close();
		
		} catch(SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		

	
			
	}	
	
}