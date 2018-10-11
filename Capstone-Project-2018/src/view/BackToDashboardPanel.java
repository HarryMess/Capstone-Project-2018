package view;

import controller.BackToDashboardListener;
import controller.LogoutListener;

import javax.swing.*;
import java.awt.*;

public class BackToDashboardPanel extends JPanel
{
	public BackToDashboardPanel(AbstractFrame parentFrame, String pageTitle)
	{
		setLayout(new BorderLayout());

		//Create components
		JButton backButton = new JButton("Back to Dashboard");
		JLabel pageLabel = new JLabel(pageTitle);
		JPanel northPanel = new JPanel(new BorderLayout());
		JButton logOutButton = new JButton("Log Out");

		//Action listeners
		backButton.addActionListener(new BackToDashboardListener(parentFrame.getFrameManager(), parentFrame));
		logOutButton.addActionListener(new LogoutListener(parentFrame.getFrameManager(), parentFrame));

		//Customise components
		pageLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		pageLabel.setHorizontalAlignment(JLabel.CENTER);
		pageLabel.setVerticalAlignment(JLabel.NORTH);

		//Add components
		northPanel.add(backButton, BorderLayout.WEST);
		northPanel.add(logOutButton, BorderLayout.EAST);

		add(northPanel, BorderLayout.NORTH);
		add(pageLabel, BorderLayout.CENTER);
	}
}
