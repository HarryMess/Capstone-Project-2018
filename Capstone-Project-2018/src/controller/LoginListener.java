package controller;

import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener
{
	private JTextField userField, passField;
	private FrameManager fm;
	private AbstractFrame parentFrame;
	public LoginListener(AbstractFrame parentFrame, JTextField userField, JTextField passField)
	{
		this.parentFrame = parentFrame;
		this.userField = userField;
		this.passField = passField;

		fm = parentFrame.getFrameManager();
	}

	public void actionPerformed(ActionEvent e)
	{
		String email = userField.getText();
		String password = passField.getText();

		//loginMethod(email, password);
		System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
		fm.switchFrame(parentFrame, fm.getFrame("dashboard")); //Actually changes the frame
	}
}
