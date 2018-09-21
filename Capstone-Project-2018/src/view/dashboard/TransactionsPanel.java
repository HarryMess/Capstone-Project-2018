package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TransactionsPanel extends JPanel
{
	public TransactionsPanel()
	{
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));

		//Create Components
		Object[] columnNames = {"Stock Name", "+/-", "Price"};
		Object[][] rowData = {
				{"ABC", "+", 5.00 },
				{"DEF", "-", 4.00},
				{"GHI", "+", 3.00}
		};
		StockTable transactions = new StockTable(rowData, columnNames);
		JLabel title = new JLabel("Recent Transactions:", SwingConstants.CENTER);


		//Add components
		add(title, BorderLayout.NORTH);
		add(transactions, BorderLayout.CENTER);
	}
}