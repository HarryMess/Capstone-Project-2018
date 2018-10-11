package controller;

import view.AbstractFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackToDashboardListener implements ActionListener
{
	private FrameManager fm;
	private AbstractFrame parentFrame;
	public BackToDashboardListener(FrameManager fm, AbstractFrame parentFrame)
	{
		this.fm = fm;
		this.parentFrame = parentFrame;
	}

	public void actionPerformed(ActionEvent e)
	{
		fm.switchFrame(parentFrame, fm.getFrame("dashboard")); //Actually changes the frame
	}
}
