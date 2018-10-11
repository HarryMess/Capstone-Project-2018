package view.dashboard;

import controller.LinkListener;
import view.AbstractFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BuyStockPanel extends AbstractTablePanel
{
	public BuyStockPanel(AbstractFrame parentFrame)
	{
		super(parentFrame);
		String linkedFrame = "buystocks";
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
		StockTableModel model = new StockTableModel(rowData, columnNames);
		StockTable buyStocks = new StockTable(linkedFrame, this, model); //TODO: Add search?
		setTable(buyStocks.getTable());

		//Add components
		add(title, BorderLayout.NORTH);
		add(buyStocks, BorderLayout.CENTER);
	}
}
