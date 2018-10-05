package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class LinkListener extends MouseAdapter
{
	private JFrame oldFrame;
	private String newFrameStr;
	private FrameManager fm;
	public LinkListener(FrameManager fm, JFrame oldFrame, String newFrameStr)
	{
		this.oldFrame = oldFrame;
		this.newFrameStr = newFrameStr;
		this.fm = fm;
	}

	public void mouseClicked(MouseEvent e)
	{
		fm.getFrame(newFrameStr).setVisible(true);
		oldFrame.setVisible(false);
	}


	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

		//Get new frame from Frame Manager
		JFrame newFrame = fm.getFrame(newFrameStr);
		fm.switchFrame(oldFrame, newFrame);
	}
}
