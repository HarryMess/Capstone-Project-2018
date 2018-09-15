package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener
{
	private JTextField emailField, firstNameField, lastNameField, passField, confirmPassField;
	public RegisterListener(JTextField userField, JPasswordField passField, JPasswordField confirmPassField,
							JTextField firstNameField, JTextField lastNameField)
	{
		this.emailField = userField;
		this.passField = passField;
		this.confirmPassField = confirmPassField;
		this.firstNameField = firstNameField;
		this.lastNameField = lastNameField;
	}

	public void actionPerformed(ActionEvent e)
	{
		String email = emailField.getText();
		String password = passField.getText();
		String confirmPassword = confirmPassField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();

		//registerMethod(email, password, confirmPassword, firstName, lastName);
		System.out.println("Test call.\nEmail: " + email + "\nName: " + firstName + " " + lastName + "\nPassword: " + password
		+ " and confirmed: " + confirmPassword);
	}
}
