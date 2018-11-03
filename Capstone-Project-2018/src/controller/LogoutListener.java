package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener
{
	private FrameManager fm;
	private AbstractFrame oldFrame;
	public LogoutListener(FrameManager fm, AbstractFrame oldFrame)
	{
		this.fm = fm;
		this.oldFrame = oldFrame;
	}

	public void actionPerformed(ActionEvent e)
	{
		AbstractFrame newFrame = fm.getFrame("login");

		fm.switchFrame(oldFrame, newFrame);
		
		//TODO All frames need to be set to null when this happens
	}
}