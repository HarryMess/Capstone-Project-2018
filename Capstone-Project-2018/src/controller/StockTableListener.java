package controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockTableListener extends MouseAdapter
{
	private JFrame oldFrame;
	private String newFrameStr;
	private FrameManager fm;
	private JTable table;
	
	public StockTableListener(FrameManager fm, JFrame oldFrame, String newFrameStr, JTable table)
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
		JFrame newFrame = fm.getFrame(newFrameStr);
		fm.switchFrame(oldFrame, newFrame);
	}
}
