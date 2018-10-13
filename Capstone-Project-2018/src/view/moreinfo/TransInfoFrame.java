package view.moreinfo;

import controller.FrameManager;
import view.AbstractFrame;
import view.BackToDashboardPanel;

import javax.swing.*;
import java.awt.*;

public class TransInfoFrame extends AbstractFrame
{
	public TransInfoFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Frame settings
		setLayout(new GridLayout(3, 1));

		//Create components
		BackToDashboardPanel upperPanel = new BackToDashboardPanel(this, "Your Transactions");
		JPanel middlePanel = new JPanel(new BorderLayout());


		//Component settings

		//Add components
		//middlePanel.add()

		add(upperPanel);
		add(middlePanel);
	}
}
