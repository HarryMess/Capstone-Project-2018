package view.dashboard;

import javax.swing.*;
import java.awt.*;

public class StockTable extends JPanel
{
	public StockTable(Object[][] rowData, Object[] columnNames)
	{ //TODO: Actually pull stock data from DataBase
		setLayout(new BorderLayout());
		StockTableModel model = new StockTableModel(rowData, columnNames);
		JTable table = new JTable(model);

		//Table settings
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		//Add table header, then table itself
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);

	}
}

