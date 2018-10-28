package view.dashboard;

import view.AbstractFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Stock;

import java.awt.*;
import java.util.ArrayList;

public class MyStockPanel extends AbstractTablePanel
{
	public MyStockPanel(AbstractFrame parentFrame)
	{
		super(parentFrame);
		String linkedFrame = "mystocks";
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
		StockTableModel model = new StockTableModel(rowData, columnNames);
		StockTable myStocks = new StockTable(linkedFrame, this, model);
		setTable(myStocks.getTable());

		//Add components
		add(title, BorderLayout.NORTH);
		add(myStocks, BorderLayout.CENTER);
	}
	
	private ArrayList<Stock> getStockData() {
		return null;
		
	}
}
