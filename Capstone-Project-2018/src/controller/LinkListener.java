package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LinkListener extends MouseAdapter
{
	private AbstractFrame oldFrame;
	private String newFrameStr;
	private FrameManager fm;
	
	public LinkListener(FrameManager fm, AbstractFrame oldFrame, String newFrameStr)
	{
		this.oldFrame = oldFrame;
		this.newFrameStr = newFrameStr;
		this.fm = fm;
	}
	
	public LinkListener(FrameManager fm, AbstractFrame oldFrame, String newFrameStr, JTable table)
	{
		this.oldFrame = oldFrame;
		this.newFrameStr = newFrameStr;
		this.fm = fm;
		
		// get the company code from the selected item
	}

	public void mouseClicked(MouseEvent e)
	{
		fm.switchFrame(oldFrame, fm.getFrame(newFrameStr));
	}


	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

		//Get new frame from Frame Manager
//		JFrame newFrame = fm.getFrame(newFrameStr);
//		fm.switchFrame(oldFrame, newFrame);
	}
	
	// Added by Paul
	public void setCompanyCode(String code) {
		fm.SetCompanyCode(code);
	}
}
