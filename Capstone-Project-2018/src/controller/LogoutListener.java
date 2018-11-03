package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener
{
	private FrameManager fm;
	private JFrame oldFrame;
	public LogoutListener(FrameManager fm, JFrame oldFrame)
	{
		this.fm = fm;
		this.oldFrame = oldFrame;
	}

	public void actionPerformed(ActionEvent e)
	{
		JFrame newFrame = fm.getFrame("login");

		fm.switchFrame(oldFrame, newFrame);
		
		//TODO All frames need to be set to null when this happens
	}
}