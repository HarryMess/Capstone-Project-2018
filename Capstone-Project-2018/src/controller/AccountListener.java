package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountListener implements ActionListener
{ 
	//TODO: Combine with logout listener??
	private FrameManager fm;
	private JFrame oldFrame;
	public AccountListener(FrameManager fm, JFrame oldFrame)
	{
		this.fm = fm;
		this.oldFrame = oldFrame;
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Account button clicked.");
		JFrame newFrame = fm.getFrame("account");

		fm.switchFrame(oldFrame, newFrame);

	}
}
