package view.dashboard;

import controller.FrameManager;
import view.AbstractFrame;
import view.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends AbstractFrame
{
	private AbstractTablePanel buyStockPanel, myStockPanel, transactionsPanel;
	private String userEmail;
	private DashboardToolBar toolBar;
	public DashboardFrame(FrameManager fm, String title)
	{ //TODO: Space everything out nicely
		super(fm, title);
		setLayout(new BorderLayout());

		//Layout settings for mainPanel
		GridLayout mainLayout = new GridLayout(1, 3);
		mainLayout.setHgap(20);
		mainLayout.setVgap(10);

		//Create components
		toolBar = new DashboardToolBar(this);
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

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	@Override
	public void redraw()
	{
		toolBar.getBalanceLabel().setBalance();
		toolBar.redrawName();

		//Also redraw tables
		if(!isVisible())
		{
			myStockPanel.updateTable();
			buyStockPanel.updateTable();
			transactionsPanel.updateTable();
		}

		updateSelection();
	}

	public void updateSelection()
	{ //If no selection in table, set 'more info' colour to grey.
		AbstractTablePanel[] panels = {myStockPanel, buyStockPanel};
		for(AbstractTablePanel panel : panels)
		{
			JLabel moreInfo = panel.getTablePanel().getMoreInfo();
			if (panel.getTable().getSelectedRow() == -1)
			{
				moreInfo.setForeground(Color.GRAY);
				moreInfo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); //Change to hand cursor on hover
			}
			else
			{
				moreInfo.setForeground(Color.BLUE);
				moreInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Change to hand cursor on hover
			}
		}
	}
}
