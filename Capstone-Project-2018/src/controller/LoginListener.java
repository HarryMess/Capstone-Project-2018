package controller;

import javax.swing.*;

import java.io.*;
import java.sql.*;
import java.util.*;

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
	
	public void loginMethod (String email, String password)
	{
		
		final String dbURL = "jdbc:derby:Database;create=true;user='s3488361;password=password"; // This might have to be changed, if you get a "No suitable driver found for ..." Error the classpath is wrong
	    Connection connec = null; /* Instance */
	    Statement statem = null;

		{
	        try
	        {
	            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); // use org.apache.derby.jdbc.EmbeddedDriver
	            connec = DriverManager.getConnection(dbURL);
	        }

	        catch (Exception exception)
	        {
	            exception.printStackTrace(); /* Calls the toString method of whatever exception was thrown */
	        }

	        try
	        {
	            statem = connec.createStatement();
	            ResultSet res = statem.executeQuery("select * from users where email='"+email+"' AND password = '"+password+"'");
	            int counter = 0;
	            while (res.next())
	            {
	                counter += 1;
	            }

	            if (counter == 1)
	            {
	                System.out.println("Access granted");
	            }

	            else
	            {
	                System.out.println("Username and/or password do not match up with our records");
	                System.out.println("Access denied");

	            }
	        }

	        catch(Exception exception)
	        {
	            exception.printStackTrace();
	        }
	    }
		
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
			
			
			
			loginMethod(email, password);
			System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
			
			JOptionPane.showMessageDialog(null, "Login successul", "Login Confirmation",
					JOptionPane.INFORMATION_MESSAGE);
			
			// open dashboard screen - replace the code below with actual screen
			parent.setVisible(false);
			ConsoleApplication ca = new ConsoleApplication(user);
			ca.showCompany();
			ca.showMyshare();
			ca.showRecenttrans();
		}
	}
}
