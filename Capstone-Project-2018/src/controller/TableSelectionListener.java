package controller;

import view.AbstractFrame;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableSelectionListener implements ListSelectionListener
{
	private AbstractFrame parentFrame;
	public TableSelectionListener(AbstractFrame parentFrame)
	{
		this.parentFrame = parentFrame;
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		parentFrame.redraw();

	}
}
