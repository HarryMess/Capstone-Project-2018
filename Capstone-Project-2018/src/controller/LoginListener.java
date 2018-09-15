package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener
{
	private JTextField userField, passField;
	public LoginListener(JTextField userField, JTextField passField)
	{
		this.userField = userField;
		this.passField = passField;
	}

	public void actionPerformed(ActionEvent e)
	{
		String email = userField.getText();
		String password = passField.getText();
		
		loginMethod(email, password);
		
		System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
	}
	
	public void loginMethod(String email, String password)
	{
		private static String dbURL = ""
		
		
	}
}
