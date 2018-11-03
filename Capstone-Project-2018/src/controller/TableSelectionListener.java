package controller;

import view.dashboard.DashboardFrame;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableSelectionListener implements ListSelectionListener
{
	private DashboardFrame parentFrame;
	public TableSelectionListener(DashboardFrame parentFrame)
	{
		this.parentFrame = parentFrame;
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		parentFrame.updateSelection();

	}
}
