package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.User;
import view.dashboard.StockTable;

public abstract class DatabaseTable {

	private static final String dbUrl = "jdbc:derby:C:\\RMIT\\Programming Project\\Project Source\\Database;create=true;upgrade=true;user=username";
	private static Connection connection;
	private static User currentUser = null;
	
	private static UsersTable users; //= new UsersTable();
	private static TradingAccountsTable tradingAccounts; //= new TradingAccountsTable();
	private static StocksTable stocks; //= new StocksTable();	
	private static TransactionsTable transactions; //= new TransactionsTable();
	private static AccountHistoryTable accountHistory; //= new AccountHistoryTable();
	private static StockHistoryTable stockHistory; //= new StockHistoryTable();
	
	public static void initilize() {
		users = new UsersTable();
//		tradingAccounts = new TradingAccountsTable();
//		stocks = new StocksTable();	
//		transactions = new TransactionsTable();
//		accountHistory = new AccountHistoryTable();
//		stockHistory = new StockHistoryTable();
		System.out.println("users: " + users);
	}
	
	public static Connection getConnection() {
		
		try {
			if (connection == null) {
				Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	
				connection = DriverManager.getConnection(dbUrl);
			}
			
			return connection;
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("Error: Could not connect to database");
			e.printStackTrace();
		}

		return null;
	}
	
	public static void setCurrentUser(User user) {
		currentUser = user;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	// database table accessors
	public static UsersTable getUsers() { return users; }	
	public TradingAccountsTable getTradingAccounts() {	return tradingAccounts;	}
	
	
	/* Abstract Methods */
	public abstract List<?> getAll() throws SQLException;
//	public abstract List<?> getAllById(int id) throws SQLException;
//	public abstract Object getById(int id) throws SQLException;
//	public abstract Object getByName(String name) throws SQLException;
//	public abstract void add(Object obj) throws SQLException;
//	public abstract void update(int id, Object obj) throws SQLException;
//	public abstract void delete(int id) throws SQLException;
//	public abstract void printTable() throws SQLException;
}
