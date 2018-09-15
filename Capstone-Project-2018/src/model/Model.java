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
}
