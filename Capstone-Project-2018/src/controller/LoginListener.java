package controller;

import view.AbstractFrame;

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
	
	public boolean loginMethod (String email, String password)
	{
		//Database CANNOT be connected to by DTP before running program, else will throw error
		final String dbURL = "jdbc:derby:C:\\Users\\quick\\GitHub\\Capstone-Project-2018\\Capstone-Project-2018\\Capstone-Project-2018\\derby-10.14.2.0\\bin\\Database;create=true"; // This might have to be changed, if you get a "No suitable driver found for ..." Error the classpath is wrong
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
	            
	            //String sql2 = "select email from username.users";
	            String sql = "select password from username.users where password";
	            
	            ResultSet res = statem.executeQuery(sql);
	            int counter = 0;
	            while (res.next())
	            {
	                counter += 1; //counting how many columns are present
	            }

	            if (counter == 1)
	            {
	                System.out.println("Access granted");
	                return true;
	            }

	            else if (counter == 2) //if duplicate records
	            {
	                System.out.println("Username and/or password do not match up with our records");
	                System.out.println("Access denied");
	                return false;

	            }
	        }

	        catch(Exception exception)
	        {
	            exception.printStackTrace();
	        }
	        return false;
	    }
		
	}

	public void actionPerformed(ActionEvent e)
	{
		Model model = Model.getInstance();
		
		String email = userField.getText();
		String password = passField.getText();
		
		User user = model.getUser(email);
		
		//System.out.println("Email is:" + user); 
		
		System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
		
		if(!loginMethod(email, password)) {
			JOptionPane.showMessageDialog(null, "Invalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
		} else  {			
			
			JOptionPane.showMessageDialog(null, "Login successul", "Login Confirmation",
					JOptionPane.INFORMATION_MESSAGE);

			fm.switchFrame(parentFrame, fm.getFrame("dashboard")); //Actually changes the frame
			
			// open dashboard screen - replace the code below with actual screen
//			parent.setVisible(false);
//			ConsoleApplication ca = new ConsoleApplication(user);
//			ca.showCompany();
//			ca.showMyshare();
//			ca.showRecenttrans();
		
		/*if(user == null) {
			System.out.print("No user with that email address was found\n");
			JOptionPane.showMessageDialog(null, "Invalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
		} else if (!user.passwordMatches(password)) {
			System.out.println("The password is invalid");
			JOptionPane.showMessageDialog(null, "Ivalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
		} else  {			

			System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
			
			JOptionPane.showMessageDialog(null, "Login successul", "Login Confirmation",
					JOptionPane.INFORMATION_MESSAGE);
			
			// open dashboard screen - replace the code below with actual screen
			parent.setVisible(false);
			ConsoleApplication ca = new ConsoleApplication(user);
			ca.showCompany();
			ca.showMyshare();
			ca.showRecenttrans();*/
		}

	}
}
