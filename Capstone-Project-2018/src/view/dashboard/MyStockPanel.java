package view.dashboard;

import view.AbstractFrame;
import view.TableData;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Stock;
import model.User;
import model.database.DatabaseTable;
import model.database.StocksTable;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class MyStockPanel extends AbstractTablePanel implements TableData
{
	public MyStockPanel(AbstractFrame parentFrame)
	{
		super(parentFrame);
		String linkedFrame = "mystocks";
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));

		//Create components
		JLabel title = new JLabel("My Stocks:", SwingConstants.CENTER);
		Object[] columnNames = getColumnNames();
		Object[][] rowData = getRowData(columnNames.length);
		
		StockTableModel model = new StockTableModel(rowData, columnNames);
		StockTable myStocks = new StockTable(linkedFrame, this, model);
		setTable(myStocks.getTable());

		//Add components
		add(title, BorderLayout.NORTH);
		add(myStocks, BorderLayout.CENTER);
	}

	@Override
	public Object[] getColumnNames() {
		return new Object[]{"Stock Name", "Amount", "Price"};
	}

	@Override
	public Object[][] getRowData(int columns) {
		User user = DatabaseTable.getCurrentUser();
		StocksTable stocks = StocksTable.getInstance();
		
		try {
			List<Stock> stocksOwned = stocks.getStocksOwned(user.getId());
			Object[][] rowData = new Object[stocksOwned.size()][columns];
			
			for(int i=0; i<stocksOwned.size(); i++) {
				rowData[i][0] = stocksOwned.get(i).getCode();
				rowData[i][1] = stocksOwned.get(i).getCompanyName();
				rowData[i][2] = stocksOwned.get(i).getMarketPrice();
			}
			
			return rowData;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
