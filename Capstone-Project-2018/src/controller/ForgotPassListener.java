package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassListener implements ActionListener
{
	private JTextField emailField;
	public ForgotPassListener(JTextField emailField)
	{
		this.emailField = emailField;
	}

	public void actionPerformed(ActionEvent e)
	{
		String email = emailField.getText();

		//forgotPassMethod(email);
		System.out.println("Test call.\n Email: " + email);
	}
}
