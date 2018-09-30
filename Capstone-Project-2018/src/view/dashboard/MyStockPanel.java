package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyStockPanel extends JPanel
{
	public MyStockPanel()
	{
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));

		//Create components
		JLabel title = new JLabel("My Stocks:", SwingConstants.CENTER);
		Object[] columnNames = {"Stock Name", "Amount", "Price"};
		Object[][] rowData = {
				{"ABC", 10, 5.00 },
				{"DEF", 15, 4.00},
				{"GHI", 20, 3.00}
		};
		StockTable myStocks = new StockTable(rowData, columnNames);


		//Add components
		add(title, BorderLayout.NORTH);
		add(myStocks, BorderLayout.CENTER);
	}
}
