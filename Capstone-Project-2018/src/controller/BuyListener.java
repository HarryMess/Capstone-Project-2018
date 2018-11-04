package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Stock;
import model.TradingAccount;
import model.User;
import model.database.DatabaseTable;
import model.database.StocksTable;
import model.database.TradingAccountsTable;

public class BuyListener implements ActionListener {

	private JFrame frame;
	private String companyCode;
	private User user;
	
	// constructor
	public BuyListener(JFrame frame, String companyCode) {
		
		this.frame = frame;
		this.companyCode = companyCode;
		user = DatabaseTable.getCurrentUser();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		TradingAccountsTable tradingAccounts = TradingAccountsTable.getInstance();
		StocksTable stocksTable = StocksTable.getInstance();
		
		try {
			Stock stock = stocksTable.getStock(companyCode);			
			TradingAccount buyer = tradingAccounts.getTradingAccount(user.getEmail());
			TradingAccount seller = tradingAccounts.getTradingAccount(DatabaseTable.STOCK_MARKET_ACCOUNT);
			
			// checks if the the stock is already owned by this trading account
			if(stock.isOwnedBy(buyer.getId())) {
				JOptionPane.showMessageDialog(frame, "You are already the owner of this stock.", 
				"Stock already owned by " + buyer.getName(), JOptionPane.WARNING_MESSAGE);
			}
			
			// checks if the purchaser does not have enough money in the account
			else if(!buyer.enoughMoney(stock.getMarketPrice())) {
				JOptionPane.showMessageDialog(frame, "You don't have enough money to purchase this stock", 
						"Not enough money", JOptionPane.WARNING_MESSAGE);
			}
			
			else {
				// updates the information in the database
				stocksTable.transferStock(buyer, seller, stock.getCode(), 1, stock.getMarketPrice());
			
				if(stocksTable.transferStock(buyer, seller, stock.getCode(), 1, stock.getMarketPrice()))
					JOptionPane.showMessageDialog(frame, "You have bought the following from the stock market\n"
					+ stock.getCode() + "\t" + stock.getCompanyName(), "Purcahse Confirmation", 
					JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "The stock could not be purchased due to the following error: \n"
			+ ex.getMessage(), "Error purchasing stock", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void updateInfo(String newCode)
	{
		companyCode = newCode;
		user = DatabaseTable.getCurrentUser();
	}

}
