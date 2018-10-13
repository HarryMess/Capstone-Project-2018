package controller;

import view.*;
import view.account.AccountFrame;
import view.dashboard.DashboardFrame;
import view.forgotpass.ForgotPasswordFrame;
import view.login.LoginFrame;
import view.register.RegisterFrame;
import view.moreinfo.StockInfoFrame;

import javax.swing.*;
import java.awt.*;

public class FrameManager
{
	private String frameTitle;
	private AbstractFrame loginFrame, registerFrame, forgotPassFrame, dashboardFrame, accountFrame,
			myStocksFrame, buyStocksFrame, transactionsFrame;
	public FrameManager(String frameTitle)
	{
		loginFrame = new LoginFrame(this, frameTitle);
		this.frameTitle = frameTitle;
	}

	public JFrame getFrame(String frame)
	{
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
	}

	public void switchFrame(JFrame oldFrame, JFrame newFrame)
	{
		//Make sure new frame is same size/place as old one.
		Dimension currentSize = oldFrame.getSize();
		Point currentPoint = oldFrame.getLocation();
		newFrame.setSize(currentSize);
		newFrame.setLocation(currentPoint);
		newFrame.setExtendedState(oldFrame.getExtendedState());

		//System.out.println("Switching from " + oldFrame.getClass().getName() + " to " + newFrame.getClass().getName());

		//Switch visible frame
		newFrame.setVisible(true);
		oldFrame.setVisible(false);
	}

//	public AbstractTablePanel getTable(AbstractFrame stockFrame)
//	{
//		if(stockFrame.equals(myStocksFrame)) System.out.println("My Stocks");
//		else if(stockFrame.equals(buyStocksFrame)) System.out.println("Buy stocks");
//		else System.out.println("neither");
//
//		return new AbstractTablePanel(stockFrame); //TODO: obvs fix
//	}

}
