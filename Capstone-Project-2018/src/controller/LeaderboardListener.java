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
		String[] names = {"Harry", "Josh", "Paul", "Frank", "Melissa", "Fiona", "Kathleen", "Michael", "Chris", "Sam"};
		for(int i=0;i<10;i++)
		{
			sb.append(i+1 + ". " + names[i] + '\n');
		}

		return sb.toString();
	}
}
