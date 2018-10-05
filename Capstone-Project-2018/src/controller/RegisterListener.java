package controller;

import javax.swing.*;

import model.Model;
import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener
{	
	private JTextField emailField, firstNameField, lastNameField, passField, confirmPassField;
<<<<<<< HEAD
=======
	
	
>>>>>>> 903a25f49cb3d84d5753baf4d59c3c87fba5093d
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
		Model model = Model.getInstance();
		
		String email = emailField.getText();
		String password = passField.getText();
		String confirmPassword = confirmPassField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		
		
//		if(!validEmailAddress(email)) {
		
//		} else if (passwordMatch(password, confirmPassword)) {
		
//		} else if (!nameAlreadyTaken(email) ) {
		
//		} else {

			//registerMethod(email, password, confirmPassword, firstName, lastName);
			model.addUser(new User(email, password, firstName+" "+lastName));
			
//			System.out.println("Test call.\nEmail: " + email + "\nName: " + firstName + " " + lastName + "\nPassword: " + password
//			+ " and confirmed: " + confirmPassword);
			
			JOptionPane.showMessageDialog(null, "The user has been registered", "Registration Confirmation", 
					JOptionPane.INFORMATION_MESSAGE);
		
//		}
	}
}
