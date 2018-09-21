package view.dashboard;

import javax.swing.table.DefaultTableModel;

public class StockTableModel extends DefaultTableModel
{

	public StockTableModel(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}

	@Override //Makes sure table cells aren't editable
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}