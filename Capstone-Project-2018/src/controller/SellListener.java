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

public class SellListener implements ActionListener {

	private JFrame frame;
	private String companyCode;
	private User user;
	
	// constructor
	public SellListener(JFrame frame, String companyCode) {
		
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
			TradingAccount seller = tradingAccounts.getTradingAccount(user.getEmail());
			TradingAccount buyer = tradingAccounts.getTradingAccount(DatabaseTable.STOCK_MARKET_ACCOUNT);
			
			// checks if the seller is the stock market
			if(user.getEmail().equals(DatabaseTable.STOCK_MARKET_ACCOUNT)) {
				JOptionPane.showMessageDialog(frame, "This is the stock market account and therefore cannot sell stock.", 
						"Invalid account action", JOptionPane.WARNING_MESSAGE);
			}
			
			// checks if the the stock is already owned by this trading account
			else if(!stock.isOwnedBy(seller.getId())) {
				
				JOptionPane.showMessageDialog(frame, "You do not own this stock and therefore cannot sell it.", 
				"Stock is not owned by " + buyer.getName(), JOptionPane.WARNING_MESSAGE);
			}
			
			else {
			
				// updates the information in the database
				if(stocksTable.transferStock(buyer, seller, stock.getCode(), 1, stock.getMarketPrice()))				
					JOptionPane.showMessageDialog(frame, "You have sold the following stock back to the stock market\n"
					+ stock.getCode() + "\t" + stock.getCompanyName(), "Sale Confirmation", 
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
