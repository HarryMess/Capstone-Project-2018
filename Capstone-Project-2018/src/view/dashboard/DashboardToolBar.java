package view.dashboard;

import controller.AccountListener;
import controller.LeaderboardListener;
import controller.LinkListener;
import controller.LogoutListener;

import javax.swing.*;
import java.awt.*;

public class DashboardToolBar extends JToolBar
{
	public DashboardToolBar(DashboardFrame parentFrame)
	{
		setFloatable(false);

		//Create ToolBar items
		BalanceLabel currentBalance = new BalanceLabel();
		JButton leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.setHorizontalAlignment(RIGHT);
		JButton accountButton = new JButton("My Account");
		accountButton.setHorizontalAlignment(RIGHT);
		JButton logOutButton = new JButton("Log Out");
		logOutButton.setHorizontalAlignment(RIGHT);

		//Add to toolbar
		add(currentBalance);
		add(Box.createHorizontalGlue()); //Pushes currentBalance to left side, and all other buttons to right side
		add(leaderboardButton);
		add(accountButton);
		add(logOutButton);

		//Action listeners
		logOutButton.addActionListener(new LogoutListener(parentFrame.getFrameManager(), parentFrame));
		leaderboardButton.addActionListener(new LeaderboardListener());
		accountButton.addActionListener(new AccountListener());
	}
}
