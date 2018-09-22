package view.forgotpass;

import controller.ForgotPassListener;
import controller.LinkListener;
import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswordPanel extends JPanel
{
	private AbstractFrame parentFrame;
	public ForgotPasswordPanel(ForgotPasswordFrame parentFrame)
	{
		this.parentFrame = parentFrame;

		//Layout stuff
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		//Login title
		JLabel emailLabel = new JLabel("Enter Email");
		emailLabel.setFont(new Font("Myriad Pro",Font.PLAIN,24)); //TODO: Space things out more

		//Description text
		JLabel descLabel = new JLabel("We will send you a password reset link.");

		//Email text field
		JTextField emailText = new JTextField("Email", 12); //TODO: Make this proper prompt text

		//Submit button
		JButton submitButton = new JButton("Submit");

		//Link back to login screen
		JLabel loginLabel = new JLabel("Back to Login");
		loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Change to hand cursor on hover
		loginLabel.setForeground(Color.blue);
		loginLabel.addMouseListener(
				new LinkListener(parentFrame.getFrameManager(), parentFrame, "login"));


		//Add all elements to panel
		this.add(emailLabel, gbc);
		this.add(descLabel, gbc);
		this.add(emailText, gbc);
		this.add(submitButton, gbc);
		this.add(loginLabel, gbc);

		//Action Listener
		submitButton.addActionListener(new ForgotPassListener(emailText));

		//this.setBorder(BorderFactory.createLineBorder(Color.black)); TODO: Figure out border
	}

}
