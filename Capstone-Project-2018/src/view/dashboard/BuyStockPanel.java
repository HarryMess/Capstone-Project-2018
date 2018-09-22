package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BuyStockPanel extends JPanel
{
	public BuyStockPanel()
	{
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));

		//Create components
		JLabel title = new JLabel("Buy New Stocks:", SwingConstants.CENTER);
		Object[] columnNames = {"Stock Code", "Stock Name", "Price"};
		Object[][] rowData = {
				{"ABC", "Stock 1", 5.00 },
				{"DEF", "Stock 2", 4.00},
				{"GHI", "Stock 3", 3.00}
		};
		StockTable buyStocks = new StockTable(rowData, columnNames);


		//Add components
		add(title, BorderLayout.NORTH);
		add(buyStocks, BorderLayout.CENTER);
	}
}
