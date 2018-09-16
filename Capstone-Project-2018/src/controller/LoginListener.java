package controller;

import javax.swing.*;

import main.ConsoleApplication;
import model.Model;
import model.User;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener
{
	private JFrame parent;
	private JTextField userField, passField;
	public LoginListener(JFrame parent, JTextField userField, JTextField passField)
	{
		this.parent = parent;
		this.userField = userField;
		this.passField = passField;
	}

	public void actionPerformed(ActionEvent e)
	{
		Model model = Model.getInstance();
		
		String email = userField.getText();
		String password = passField.getText();
		
		User user = model.getUser(email);
		
		if(user == null) {
			System.out.print("No user with that email address was found\n");
			JOptionPane.showMessageDialog(null, "Ivalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
		} else if (!user.passwordMatches(password)) {
			System.out.println("The password is invalid");
			JOptionPane.showMessageDialog(null, "Ivalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
		} else  {			
			//loginMethod(email, password);
			System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
			
			JOptionPane.showMessageDialog(null, "Login successul", "Login Confirmation",
					JOptionPane.INFORMATION_MESSAGE);
			
			// open dashboard screen - replace the code below with actual screen
			parent.setVisible(false);
			new ConsoleApplication(user);
			
		}
	}
}
