package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		String[][] names = {};
		if(names.length > 0)
		{
			for (int i = 0; i < names.length; i++)
			{
				sb.append(i + 1 + ". " + names[i][0] + " - $" + names[i][1] + '\n');
			}
		} else {
			//If no trading accounts retrieved, display this:
			sb.append("Not enough results to display.\nMust have at least 1 trading account.");
		}

		return sb.toString();
	}
}
