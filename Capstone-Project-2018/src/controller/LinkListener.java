package controller;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LinkListener implements MouseListener
{
	JFrame oldFrame;
	String newFrame;
	FrameManager fm;
	public LinkListener(FrameManager fm, JFrame oldFrame, String newFrame)
	{
		this.oldFrame = oldFrame;
		this.newFrame = newFrame;
		this.fm = fm;
	}

	public void mouseClicked(MouseEvent e)
	{
		fm.getFrame(newFrame).setVisible(true);
		oldFrame.setVisible(false);
	}


	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

	}
}
