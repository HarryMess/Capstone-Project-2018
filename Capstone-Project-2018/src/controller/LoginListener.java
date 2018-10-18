package controller;

import javax.swing.*;

import java.sql.*;
import view.AbstractFrame;
import view.dashboard.DashboardFrame;


import database.DerbyDB;
import database.Users;

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
		
		//System.out.println("Email is:" + user); 
		
		System.out.println("Test call.\n Email: " + email + " Password: " + password);
		
		if(Users.login(email, password)) {
			JOptionPane.showMessageDialog(null, "Login successul", "Login Confirmation",
					JOptionPane.INFORMATION_MESSAGE);
			
			// open dashboard screen - replace the code below with actual screen
			new DashboardFrame(fm ,"Dashboard");
			parentFrame.setVisible(false);			
			
		} else  {			
			JOptionPane.showMessageDialog(null, "Invalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);						
		}

	}
}