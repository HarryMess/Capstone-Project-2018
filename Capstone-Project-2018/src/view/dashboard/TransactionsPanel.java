package view.dashboard;

import view.AbstractFrame;
import view.TableData;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Transaction;
import model.User;
import model.database.DatabaseTable;
import model.database.TransactionsTable;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class TransactionsPanel extends AbstractTablePanel implements TableData
{
	private StockTable transactions;
	public TransactionsPanel(AbstractFrame parentFrame)
	{
		super(parentFrame);
		String linkedFrame = "transactions";
		setLayout(new BorderLayout());
		setBorder(new LineBorder(Color.BLACK));

		// Create Components
		Object[] columnNames = getColumnNames();
		Object[][] rowData = getRowData(columnNames.length);
		
		StockTableModel model = new StockTableModel(rowData, columnNames);
		transactions = new StockTable(linkedFrame, this, model);
		setTable(transactions.getTable());
		JLabel title = new JLabel("Recent Transactions:", SwingConstants.CENTER);

		// Add components
		add(title, BorderLayout.NORTH);
		add(transactions, BorderLayout.CENTER);
	}

	@Override
	public Object[] getColumnNames() {
		
		return new Object[] {"Date & Time", "Company", "Buy/Sell", "Price"};
	}

	@Override
	public Object[][] getRowData(int columns) {
		
		try {
			TransactionsTable transactionsTable = TransactionsTable.getInstance();
			User currentUser = DatabaseTable.getCurrentUser();
			
			List<Transaction> transactions = transactionsTable.getTransactionHistory(currentUser.getId());			
			Object[][] rowData = new Object[transactions.size()][columns];
			
			// iterate through each transaction adding values to the row data
			for(int i=0; i<transactions.size(); i++)
			{
				String buyOrSell;
				int currentUserID = getParentFrame().getFrameManager().getCurrentUser().getId();
				if(currentUserID == transactions.get(i).getBuyerId()) buyOrSell = "Buy";
				else buyOrSell = "Sell";

				rowData[i][0] = transactions.get(i).getTimestamp();
				rowData[i][1] = transactions.get(i).getCompanyCode();
				rowData[i][2] = buyOrSell;
				rowData[i][3] = transactions.get(i).getPrice();
			}
			
			return rowData;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public StockTable getTablePanel()
	{
		return transactions;
	}
}