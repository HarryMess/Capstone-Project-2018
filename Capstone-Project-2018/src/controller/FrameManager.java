package controller;


import view.account.AccountFrame;
import view.dashboard.DashboardFrame;

import view.forgotpass.ForgotPasswordFrame;
import view.login.LoginFrame;
import view.register.RegisterFrame;
import view.moreinfo.StockInfoFrame;
import view.*;


import javax.swing.*;

import model.Stock;
import model.User;
import model.database.DatabaseTable;

import java.awt.*;
import java.sql.SQLException;

public class FrameManager
{
	private String frameTitle;
	private AbstractFrame loginFrame, registerFrame, forgotPassFrame, dashboardFrame, accountFrame,
			myStocksFrame, buyStocksFrame, transactionsFrame;
	
	private User user;
	private String companyCode;

	public FrameManager(String frameTitle)
	{
		loginFrame = new LoginFrame(this, frameTitle);
		this.frameTitle = frameTitle;
		user = DatabaseTable.getCurrentUser();
		companyCode = "";
	}

	public AbstractFrame getFrame(String frame)
	{
		try {
		
			switch(frame)
			{
				case("login"):
					return loginFrame;
	
				case("register"):
					if(registerFrame == null)
					{
						registerFrame = new RegisterFrame(this, frameTitle);
					}
					return registerFrame;
	
				case("forgotpass"):
					if(forgotPassFrame == null)
					{
						forgotPassFrame = new ForgotPasswordFrame(this, frameTitle);
					}
					return forgotPassFrame;
	
				case("dashboard"):
					if(dashboardFrame == null)
					{
						dashboardFrame = new DashboardFrame(this, frameTitle);
					}
					return dashboardFrame;
	
				case("account"):
					if(accountFrame == null)
					{
						accountFrame = new AccountFrame(this, frameTitle);
					}
					return accountFrame;
				case("mystocks"):
					if(myStocksFrame == null)
					{
						myStocksFrame = new StockInfoFrame("mystocks", this, frameTitle);
					}
					return myStocksFrame;
				case("buystocks"):
					if(buyStocksFrame == null)
					{
						buyStocksFrame = new StockInfoFrame("buystocks", this, frameTitle);
					}
					return buyStocksFrame;
				case("transactions"):
					if(transactionsFrame == null)
					{
						transactionsFrame = new StockInfoFrame("transactions", this, frameTitle);
					}
					return transactionsFrame;
				default:
					return loginFrame; //If something goes wrong, return user to login
					//TODO: Add error screen?
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void switchFrame(AbstractFrame oldFrame, AbstractFrame newFrame)
	{
		//Make sure new frame is same size/place as old one.
		Dimension currentSize = oldFrame.getSize();
		Point currentPoint = oldFrame.getLocation();
		newFrame.setSize(currentSize);
		newFrame.setLocation(currentPoint);
		newFrame.setExtendedState(oldFrame.getExtendedState());

		//System.out.println("Switching from " + oldFrame.getClass().getName() + " to " + newFrame.getClass().getName());

		//Redraw necessary components
		newFrame.redraw();

		//Switch visible frame
		newFrame.setVisible(true);
		oldFrame.setVisible(false);
	}
	
	// Added by Paul
	public void SetCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public User getCurrentUser()
	{
		return user;
	}

	public void updateUser()
	{
		user = DatabaseTable.getCurrentUser();
	}

}
