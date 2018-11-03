package view.dashboard;

import model.Stock;
import model.database.DatabaseTable;
import model.database.StocksTable;
import view.AbstractFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("serial")
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
		Object[] columnNames = getColumnNames();
		Object[][] rowData = getRowData(columnNames.length);
		
		StockTableModel model = new StockTableModel(rowData, columnNames);
		
		StockTable buyStocks = new StockTable(linkedFrame, this, model); //TODO: Add search?
		setTable(buyStocks.getTable());

		//Add components
		add(title, BorderLayout.NORTH);
		add(buyStocks, BorderLayout.CENTER);
	}
	
	/** Added by Paul **/
	@Override
	public Object[] getColumnNames() {
		return new Object[]{"Stock Code", "Stock Name", "Price"};
	}
	@Override
	public Object[][] getRowData(int columns) {
		
		DatabaseTable stockTable = StocksTable.getInstance();
		
		try {
			@SuppressWarnings("unchecked")
			ArrayList<Stock> stocks = (ArrayList<Stock>) stockTable.getAll();
			
			Object[][] rowData = new Object[stocks.size()][3];
			
			for(int i=0; i< stocks.size(); i++) {
				rowData[i][0] = stocks.get(i).getCode();
				rowData[i][1] = stocks.get(i).getCompanyName();
				rowData[i][2] = stocks.get(i).getMarketPrice();
			}
			
			return rowData;
						
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}	
	}
	
}
