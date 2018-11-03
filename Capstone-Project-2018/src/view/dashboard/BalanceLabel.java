package view.dashboard;

import model.TradingAccount;
import model.database.TradingAccountsTable;
import view.AbstractFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;

public class BalanceLabel extends JLabel
{
	private double balance = 1000000;
	private DashboardFrame parentFrame;
	public BalanceLabel(DashboardFrame parentFrame)
	{
		super("Current Balance: 1000000", LEFT);
		this.parentFrame = parentFrame;
		setBalance(); //Update the balance from the DB
		setBorder(new LineBorder(Color.BLACK)); //TODO: Make border look nicer

	}

	public double getBalance()
	{
		return balance;
	}

	public void setBalance()
	{
		TradingAccountsTable accounts = TradingAccountsTable.getInstance();
		try
		{
			TradingAccount currentAccount = accounts.getTradingAccount(parentFrame.getUserEmail());

			double newBalance = currentAccount.getBalance();
			if(newBalance >= 0)
			{
				balance = newBalance;
				this.setText("Current Balance: " + balance);
			}
			else
			{
				balance = 0;
				setText("You are out of money :(");
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}
}
