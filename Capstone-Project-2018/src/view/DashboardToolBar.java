package view;

import javax.swing.*;
import java.awt.*;

public class DashboardToolBar extends JToolBar
{
	public DashboardToolBar(DashboardFrame parentFrame)
	{
		setFloatable(false);
		this.setMaximumSize(new Dimension(2000, 20));

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
		add(Box.createHorizontalGlue());
		add(leaderboardButton);
		add(accountButton);
		add(logOutButton);

		//Action listeners
		//TODO: ACTION LISTENERS
	}
}
