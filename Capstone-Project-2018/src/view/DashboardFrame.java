package view;

import controller.FrameManager;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends AbstractFrame
{
	public DashboardFrame(FrameManager fm, String title)
	{
		super(fm, title);
		setLayout(new BorderLayout());

		//Create components
		DashboardToolBar toolBar = new DashboardToolBar(this);
		JPanel mainPanel = new JPanel(new BorderLayout());

		//Main panel
		mainPanel.add(new TitlePanel(this), BorderLayout.NORTH);
		mainPanel.add(new StockPanel(), BorderLayout.CENTER);

		add(toolBar, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);




		this.setVisible(true);
	}
}
