package controller;

import javax.swing.*;

import model.database.UsersTable;

import view.AbstractFrame;
import view.dashboard.DashboardFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener
{
	private JTextField userField, passField;
	private FrameManager fm;
	private AbstractFrame parentFrame;
	
	// table references
	private UsersTable users;
	
	public LoginListener(AbstractFrame parentFrame, JTextField userField, JTextField passField)
	{
		this.parentFrame = parentFrame;
		this.userField = userField;
		this.passField = passField;

		fm = parentFrame.getFrameManager();
		users = UsersTable.getInstance();
	}	

	public void actionPerformed(ActionEvent e)
	{		
		String email = userField.getText();
		String password = passField.getText();

		if(users.login(email, password)) {
			fm.updateUser();
			((DashboardFrame)fm.getFrame("dashboard")).setUserEmail(email);
			fm.switchFrame(parentFrame, fm.getFrame("dashboard"));
		} else  {			
			JOptionPane.showMessageDialog(null, "Invalid username or password",
					"Authentication failed", JOptionPane.ERROR_MESSAGE);						
		}

	}
}