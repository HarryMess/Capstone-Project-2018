package view.dashboard;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class StockTableModel extends DefaultTableModel
{

	public StockTableModel(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}
	
	// Added by Paul
	public StockTableModel(Vector<?> data, Vector<?> columNames) {
		super(data, columNames);
	}

	@Override //Makes sure table cells aren't editable
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}


}