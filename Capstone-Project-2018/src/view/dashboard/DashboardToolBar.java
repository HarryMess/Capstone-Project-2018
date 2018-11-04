package view.dashboard;

import controller.*;
import model.TradingAccount;
import model.User;
import model.database.TradingAccountsTable;

import javax.swing.*;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class DashboardToolBar extends JToolBar
{
	private BalanceLabel currentBalance;
	private DashboardFrame parentFrame;
	private JLabel userName;
	public DashboardToolBar(DashboardFrame parentFrame)
	{
		setFloatable(false);
		this.parentFrame = parentFrame;

		//Create ToolBar items
		currentBalance = new BalanceLabel(parentFrame);
		userName = new JLabel("User's name here");
		JButton leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.setHorizontalAlignment(RIGHT);
		JButton accountButton = new JButton("My Account");
		accountButton.setHorizontalAlignment(RIGHT);
		JButton logOutButton = new JButton("Log Out");
		logOutButton.setHorizontalAlignment(RIGHT);

		//Add to toolbar
		add(currentBalance);
		add(Box.createHorizontalGlue());
		add(userName);
		add(Box.createHorizontalGlue()); //Pushes currentBalance to left side, and all other buttons to right side
		add(leaderboardButton);
		add(accountButton);
		add(logOutButton);

		//Action listeners
		logOutButton.addActionListener(new LogoutListener(parentFrame.getFrameManager(), parentFrame));
		leaderboardButton.addActionListener(new LeaderboardListener(parentFrame));
		accountButton.addActionListener(new AccountListener(parentFrame.getFrameManager(), parentFrame));
	}

	public BalanceLabel getBalanceLabel()
	{
		return currentBalance;
	}

	public void redrawName()
	{
		//String name = parentFrame.getFrameManager().getCurrentUser().getEmail();
		User curUser = parentFrame.getFrameManager().getCurrentUser(); //Get current user
		int id = curUser.getId(); //Get current user's ID

		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
		TradingAccount currentAccount;
		String name = "Could not retrieve name.";
		try
		{
			currentAccount = accounts.getTradingAccount(id); //From ID, get current trading account.
			name = currentAccount.getName();
		} catch(SQLException e)
		{
			System.out.println(e.toString());
		}

		//String name = curUser.getEmail();

		userName.setText(String.format("%s's trading account.", name));
	}
}
