package view.dashboard;

import view.AbstractFrame;

import javax.swing.*;

public abstract class AbstractTablePanel extends JPanel
{
	private AbstractFrame parentFrame;
	private JTable table;
	public AbstractTablePanel(AbstractFrame parentFrame)
	{
		this.parentFrame = parentFrame;
	}

	public AbstractFrame getParentFrame()
	{
		return parentFrame;
	}

	public JTable getTable()
	{
		return table;
	}

	public void setTable(JTable table)
	{
		this.table=table;
	}

	public Object[] getColumnNames(){
		return new Object[0];
	}
	public Object[][] getRowData(int columns){
		return new Object[0][0];
	}

	public void updateTable()
	{
		Object[] columnNames = getColumnNames();
		Object[][] rowData = getRowData(columnNames.length);
		table.setModel(new StockTableModel(rowData, columnNames));
	}
}
