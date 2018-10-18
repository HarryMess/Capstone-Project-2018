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
	public boolean loginMethod (String email, String password)
	{
		//Database CANNOT be connected to by DTP before running program, else will throw error
		Connection connec = null; /* Instance */
	    Statement statem = null;

		{
	        try
	        {
	            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); // use org.apache.derby.jdbc.EmbeddedDriver
	            connec = DerbyDB.getConnection();
	        }

	        catch (Exception exception)
	        {
	            exception.printStackTrace(); /* Calls the toString method of whatever exception was thrown */
	        }	        
	        
	        try
	        {
	            statem = connec.createStatement();	
	            String sql2 = "select email from username.users where email='"+email+"'";
	            
	            ResultSet res = statem.executeQuery(sql2);
	            int counter = 0;
	            while (res.next())
	            {
	                counter += 1; //counting how many columns are present
	            }

	            if (counter == 1)
	            {
	            	if (password.contentEquals("password")) // if password correct
    	            {
    	                return true;
    	            }

    	            else if (password != "password") //if password incorrect
    	            {	    	                
    	                return false;

    	            }
	            }

	            else if (counter == 2) //if username not found
	            {
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
		String email = userField.getText();
		String password = passField.getText();
		
		//System.out.println("Email is:" + user); 
		
		System.out.println("Test call.\n Email: " + email + "\nPassword: " + password);
		
		if(Users.login(email, password)) {
			JOptionPane.showMessageDialog(null, "Invalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
		} else  {			
			
			JOptionPane.showMessageDialog(null, "Login successul", "Login Confirmation",
					JOptionPane.INFORMATION_MESSAGE);
			
			// open dashboard screen - replace the code below with actual screen
			new DashboardFrame(fm ,"Dashboard");
			parentFrame.setVisible(false);			
		}

	}
}