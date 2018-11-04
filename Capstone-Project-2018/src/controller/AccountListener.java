package controller;

import view.AbstractFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountListener implements ActionListener
{ 
	//TODO: Combine with logout listener??
	private FrameManager fm;
	private AbstractFrame oldFrame;
	public AccountListener(FrameManager fm, AbstractFrame oldFrame)
	{
		this.fm = fm;
		this.oldFrame = oldFrame;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Account button clicked.");
		AbstractFrame newFrame = fm.getFrame("account");

		fm.switchFrame(oldFrame, newFrame);
	}
}
