package view.account;

import controller.FrameManager;
import javafx.embed.swing.JFXPanel;
import model.User;
import model.database.DatabaseTable;
import view.AbstractFrame;
import view.BackToDashboardPanel;
import view.dashboard.AbstractTablePanel;
import view.dashboard.MyStockPanel;

import java.awt.*;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AccountFrame extends AbstractFrame
{
	private AbstractTablePanel myStocks;
	public AccountFrame(FrameManager fm, String title) throws SQLException
	{
		super(fm, title);
		
		User user = DatabaseTable.getCurrentUser();
		
		setLayout(new GridLayout(3, 1));

		//Create components
		BackToDashboardPanel upperPanel = new BackToDashboardPanel(this, "Your Trading Account");
		myStocks = new MyStockPanel(this);
		JFXPanel graphPanel = new AccountHistoryChartPanel(user.getId());
//		JLabel graphLabel = new JLabel("GRAPH STUFF TO GO HERE");

		//Component settings
		//myStocks.getTable().

		//Add components
//		graphPanel.add(graphLabel);

		add(upperPanel);
		add(myStocks);
		add(graphPanel);


	}

	public void redraw()
	{
		myStocks.updateTable();
	}
}
