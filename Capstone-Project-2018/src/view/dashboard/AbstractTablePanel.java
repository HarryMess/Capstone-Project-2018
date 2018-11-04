package view.dashboard;

import view.AbstractFrame;

import javax.swing.*;

@SuppressWarnings("serial")
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

	public abstract StockTable getTablePanel();

	public void setTable(JTable table)
	{
		this.table=table;
	}

	public abstract Object[] getColumnNames();
	public abstract Object[][] getRowData(int columns);

	public void updateTable()
	{
		Object[] columnNames = getColumnNames();
		Object[][] rowData = getRowData(columnNames.length);
		table.setModel(new StockTableModel(rowData, columnNames));
	}
}
