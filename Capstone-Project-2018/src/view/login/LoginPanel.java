package view.login;

import controller.LinkListener;
import controller.LoginListener;
import view.AbstractFrame;
import view.CustomPasswordField;
import view.CustomTextField;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel
{
	public LoginPanel(AbstractFrame parentFrame)
	{

		//Layout stuff
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		//Login title
		JLabel loginLabel = new JLabel("Login");

		//Link to register
		JLabel regLabel = new JLabel("(or Register)");
		regLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Change to hand cursor on hover
		regLabel.setForeground(Color.blue); //TODO: Make only register clickable etc
		regLabel.addMouseListener(
				new LinkListener(parentFrame.getFrameManager(), parentFrame, "register"));

		//Login text fields
		// add default values for quicker login
		JTextField emailText = new CustomTextField("admin@asx.com.au");
		JTextField passText = new CustomPasswordField("password");

		//Login button
		JButton loginButton = new JButton("Login");

		//Link to reset password
		JLabel forgotPassLabel = new JLabel("Forgot Password?");
		forgotPassLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Change to hand cursor on hover
		forgotPassLabel.setForeground(Color.blue);
		forgotPassLabel.addMouseListener(
				new LinkListener(parentFrame.getFrameManager(), parentFrame, "forgotpass"));
		
		//Add all elements to panel
		this.add(loginLabel, gbc);
		this.add(regLabel, gbc);
		this.add(emailText, gbc);
		this.add(passText, gbc);
		this.add(loginButton, gbc);
		this.add(forgotPassLabel, gbc);

		//Action Listener
		loginButton.addActionListener(new LoginListener(parentFrame, emailText, passText));

		//this.setBorder(BorderFactory.createLineBorder(Color.black)); TODO: Figure out border
	}
}