/** Model.java
 * @author Paul King - s3449513
 * This class is created to store data temporarily without the use of a database.
 */

package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	private static Model model;

	/** Array lists **/
	private List<User> users;
	private List<Company> companies;
	private List<Stock> stocks;	
	private List<Transaction> transactionHistory;
	
	public static Model getInstance() {
		
		if(model == null)
			model = new Model();
		
		return model;
	}
	
	private Model() {
		this.companies = new ArrayList<Company>();
		stocks = new ArrayList<Stock>(); // need to decide how we want to implement this
		users = new ArrayList<User>();
		transactionHistory = new ArrayList<Transaction>();
	}	
	
	/** Getters **/	
	public List<User> getUsers() {
		return users;
	}
	public List<Company> getCompanies() {
		return companies;
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}	
	
	/** add data methods **/
	public void addUser(User user) {
		users.add(user);
	}	
	public void addCompany(Company company) {
		companies.add(company);
	}	
	public void addStock(Stock stock) {
		stocks.add(stock);
	}	
	public void addTransaction(Transaction transaction) {
		transactionHistory.add(transaction);
	}
	
	public Stock getStock(String code, String ownerId) throws NotFoundException {
		
		for(Stock s : stocks) {
			if(s.getCompanyByCode() == code && s.getOwner() == ownerId)
				return s;
		}
		
		throw new NotFoundException("No stocks from company '" + code + 
				"' is owned by id '" + ownerId + "'");
	}
	
	public Company getCompany(String id) {
		
		for(Company c : companies) {
			if(c.getCode().equals(id))
				return c;
		}
			
		throw new NullPointerException("Error: Company id'" + id + "' was not found.");	
	}

	public User getUser(String userName) {
		
		for(User u : users) {
			if(u.getEmail().equals(userName))
				return u;
		}
		
		return null;
	}
	
	public TradingAccount getTradingAccount(String userName) {
		
		for(User u : users) {
			if(u.getEmail().equals(userName))
				return u.getTradingAccount();
		}
		
		throw new NullPointerException("Error: Username '" + userName + "' was not found.");
	}

	public Transaction getTransaction(int id) {
		
		for(Transaction t : transactionHistory) {
			if(t.getTranasctionId() == id)
				return t;
		}
			
		throw new NullPointerException("Error: Transaction id'" + id + "' was not found.");	
	}
}
