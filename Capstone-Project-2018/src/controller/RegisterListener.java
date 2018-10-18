package controller;

import javax.swing.*;

import database.Users;
import model.User;

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
		// get the model		
		String email = emailField.getText();
		String password = passField.getText();
		String confirmPassword = confirmPassField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		
		
//		if(!validEmailAddress(email)) {
		
		if (password.equals(confirmPassword)) {
		
//		} else if (!nameAlreadyTaken(email) ) {
		
//		} else {
			
			if(Users.register(email, password, firstName + " " + lastName)) // calls method to talk to database
			
//				System.out.println("Test call.\nEmail: " + email + "\nName: " + firstName + " " + lastName + "\nPassword: " + password
//				+ " and confirmed: " + confirmPassword);
				
				JOptionPane.showMessageDialog(null, "The user has been registered", "Registration Confirmation", 
						JOptionPane.INFORMATION_MESSAGE);
			
			else
				JOptionPane.showMessageDialog(null, "There was an error registering the user",
						"Unable To Register Account", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "The 'password' and 'confirm password' fields do not match",
					"Passwords don't match", JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
