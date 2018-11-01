package view.dashboard;

import controller.FrameManager;
import view.AbstractFrame;
import view.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends AbstractFrame
{
	private AbstractTablePanel buyStockPanel, myStockPanel, transactionsPanel;
	public DashboardFrame(FrameManager fm, String title)
	{ //TODO: Space everything out nicely
		super(fm, title);
		setLayout(new BorderLayout());

		//Layout settings for mainPanel
		GridLayout mainLayout = new GridLayout(1, 3);
		mainLayout.setHgap(20);
		mainLayout.setVgap(10);

		//Create components
		DashboardToolBar toolBar = new DashboardToolBar(this);
		JPanel upperPanel = new JPanel(new BorderLayout());
		JPanel mainPanel = new JPanel(mainLayout);
		JPanel lowerPanel = new JPanel();

		buyStockPanel = new BuyStockPanel(this);
		myStockPanel = new MyStockPanel(this);
		transactionsPanel = new TransactionsPanel(this);
		//Upper panel
		upperPanel.add(toolBar, BorderLayout.NORTH);
		upperPanel.add(new TitlePanel(this), BorderLayout.CENTER);

		//Main panel
		mainPanel.add(buyStockPanel);
		mainPanel.add(myStockPanel);
		mainPanel.add(transactionsPanel);

		add(upperPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(lowerPanel, BorderLayout.SOUTH); //currently just exists for spacing purposes

		this.setVisible(true);
	}

	public JTable getTable(String tableName)
	{
		if(tableName.equals("mystocks"))
		{
			return myStockPanel.getTable();
		}
		else if(tableName.equals("buystocks"))
		{
			return buyStockPanel.getTable();
		}
		else if(tableName.equals("transactions"))
		{
			return transactionsPanel.getTable();
		}
		else return null; //TODO: Make this handle errors better?
	}
}
