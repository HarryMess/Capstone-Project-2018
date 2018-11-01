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
}
