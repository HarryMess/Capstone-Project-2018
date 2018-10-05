package controller;

import javax.swing.*;
import java.awt.*;

import view.AbstractFrame;
import view.ForgotPasswordFrame;
import view.LoginFrame;
import view.RegisterFrame;
import view.account.AccountFrame;
import view.dashboard.DashboardFrame;

public class FrameManager
{
	private String frameTitle;
	private AbstractFrame loginFrame, registerFrame, forgotPassFrame, dashboardFrame, accountFrame;
	
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

		//Switch visible frame
		newFrame.setVisible(true);
		oldFrame.setVisible(false);
	}
}
