package view.register;

import controller.LinkListener;
import controller.RegisterListener;
import view.CustomPasswordField;
import view.CustomTextField;
//import controller.ForgotPassLinkListener;
//import controller.LoginListener;
//import controller.RegisterLinkListener;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel
{


	public RegisterPanel(RegisterFrame parentFrame)
	{
		CustomTextField emailText, firstNameText, lastNameText;
		CustomPasswordField passText, confirmPassText;

		//Layout stuff
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		//Register title
		JLabel regLabel = new JLabel("Register");

		//Link to login
		JLabel loginLabel = new JLabel("(or Log In)");
		loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Change to hand cursor on hover
		loginLabel.setForeground(Color.blue); //TODO: Make only login clickable etc
		loginLabel.addMouseListener(new LinkListener(parentFrame.getFrameManager(), parentFrame, "login"));

		//Register text fields
		emailText = new CustomTextField("Email");
		passText = new CustomPasswordField("Password");
		confirmPassText = new CustomPasswordField("Confirm Password");
		firstNameText = new CustomTextField("First Name");
		lastNameText = new CustomTextField("Last Name");


		//Register button
		JButton regButton = new JButton("Register");

		//Add all elements to panel
		this.add(regLabel, gbc);
		this.add(loginLabel, gbc);
		this.add(emailText, gbc);
		this.add(firstNameText);
		this.add(lastNameText, gbc);
		this.add(passText);
		this.add(confirmPassText, gbc);
		this.add(regButton, gbc);

		//Action Listener
		regButton.addActionListener(new RegisterListener(emailText, passText, confirmPassText, firstNameText, lastNameText));
	}
}
