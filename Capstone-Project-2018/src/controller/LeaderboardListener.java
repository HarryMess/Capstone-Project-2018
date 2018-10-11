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
		String[][] names = {{"Harry", "1,000,000"}, {"Josh", "900,000"}, {"Paul", "800,000"},
				{"Frank", "700,000"}, {"Melissa", "600,000"}, {"Fiona", "500,000"}, {"Kathleen", "400,000"},
				{"Michael", "300,000"}, {"Chris", "200,000"}, {"Sam", "100,000"}};
		for(int i=0;i<10;i++)
		{
			sb.append(i+1 + ". " + names[i][0] + " - $" + names[i][1] + '\n');
		}

		return sb.toString();
	}
}
