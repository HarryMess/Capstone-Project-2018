package controller;

import view.AbstractFrame;

import javax.swing.*;

import database.DerbyDB;

import java.sql.*;

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
	    Connection connec = null; /* Instance */
	    Statement statem = null;

		{
	        try
	        {
	            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); // use org.apache.derby.jdbc.EmbeddedDriver
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
		Model model = Model.getInstance();
		
		String email = userField.getText();
		String password = passField.getText();
		
		User user = model.getUser(email);
		
		if(!loginMethod(email, password)) {
			JOptionPane.showMessageDialog(null, "Invalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);
			
			
		} else  {			
			
			
			JOptionPane.showMessageDialog(null, "Login successful", "Login Confirmation",
				JOptionPane.INFORMATION_MESSAGE);

			fm.switchFrame(parentFrame, fm.getFrame("dashboard")); //Actually changes the frame
			
//			open dashboard screen - replace the code below with actual screen
//			parent.setVisible(false);
//			ConsoleApplication ca = new ConsoleApplication(user);
//			ca.showCompany();
//			ca.showMyshare();
//			ca.showRecenttrans();
			
		}

	}
}
