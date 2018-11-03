package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockTableListener extends MouseAdapter
{
	private AbstractFrame oldFrame;
	private String newFrameStr;
	private FrameManager fm;
	private JTable table;
	
	public StockTableListener(FrameManager fm, AbstractFrame oldFrame, String newFrameStr, JTable table)
	{
		this.oldFrame = oldFrame;
		this.newFrameStr = newFrameStr;
		this.fm = fm;
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		String code = (String) table.getValueAt(table.getSelectedRow(), 0);
		fm.SetCompanyCode(code);
		
		// get new frame from Frame Manager
		AbstractFrame newFrame = fm.getFrame(newFrameStr);
		fm.switchFrame(oldFrame, newFrame);
	}
}
