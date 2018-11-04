package controller;

import model.TradingAccount;
import model.database.TradingAccountsTable;
import view.AbstractFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LeaderboardListener implements ActionListener
{
	private AbstractFrame parentFrame;

	public LeaderboardListener(AbstractFrame parentFrame)
	{
		this.parentFrame = parentFrame;
	}

	public void actionPerformed(ActionEvent e)
	{ //TODO: logic
		//default title and icon
		JOptionPane.showMessageDialog(parentFrame,
				getLeaderboard(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Leaderboard button clicked.");
	}

	public String getLeaderboard()
	{
		StringBuilder sb = new StringBuilder();
		TradingAccountsTable accountsTable = TradingAccountsTable.getInstance();
		ArrayList<String> names = new ArrayList<>();

		try
		{
			List<TradingAccount> accounts = accountsTable.getAll();
			Collections.sort(accounts, new Comparator<TradingAccount>()
			{
				@Override
				public int compare(TradingAccount ta1, TradingAccount ta2)
				{
					return (int)(ta2.getTotalValue() - ta1.getTotalValue());
				}
			});
			int i=0;
			for(TradingAccount account : accounts)
			{
				if(!account.getName().equals("Stock Market"))
					names.add(String.format("%s - $%f\n", account.getName(), account.getTotalValue()));
			}

		} catch(SQLException e) {
			System.out.println(e.toString());
		}




		if(names.size() > 0)
		{
			for (int i=0;i < names.size();i++)
			{
				sb.append(i + 1 + ". " + names.get(i));
			}
		} else {
			//If no trading accounts retrieved, display this:
			sb.append("Not enough results to display.\nMust have at least 1 trading account.");
		}

		return sb.toString();
	}
}
