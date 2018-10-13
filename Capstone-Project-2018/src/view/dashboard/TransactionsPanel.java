package view.dashboard;

import view.AbstractFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TransactionsPanel extends AbstractTablePanel
{
	public TransactionsPanel(AbstractFrame parentFrame)
	{
		super(parentFrame);
		String linkedFrame = "transactions";
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));

		//Create Components
		Object[] columnNames = {"Stock Name", "+/-", "Price"};
		Object[][] rowData = {
				{"ABC", "+", 5.00 },
				{"DEF", "-", 4.00},
				{"GHI", "+", 3.00}
		};
		StockTableModel model = new StockTableModel(rowData, columnNames);
		StockTable transactions = new StockTable(linkedFrame, this, model);
		setTable(transactions.getTable());
		JLabel title = new JLabel("Recent Transactions:", SwingConstants.CENTER);


		//Add components
		add(title, BorderLayout.NORTH);
		add(transactions, BorderLayout.CENTER);
	}
}