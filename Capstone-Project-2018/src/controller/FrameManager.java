package controller;

import view.AbstractFrame;
import view.ForgotPasswordFrame;
import view.LoginFrame;
import view.RegisterFrame;

import javax.swing.*;

public class FrameManager
{
	private String frameTitle;
	private AbstractFrame loginFrame, registerFrame, forgotPassFrame;
	public FrameManager(String frameTitle)
	{
		loginFrame = new LoginFrame(this, frameTitle);
		this.frameTitle = frameTitle;
	}

	public JFrame getFrame(String frame)
	{
		switch(frame)
		{
			case("login"):
				return loginFrame;

			case("register"):
				if(registerFrame == null)
				{
					registerFrame = new RegisterFrame(this, frameTitle);
				}
				return registerFrame;

			case("forgotpass"):
				if(forgotPassFrame == null)
				{
					forgotPassFrame = new ForgotPasswordFrame(this, frameTitle);
				}
				return forgotPassFrame;
			default:
				return null;
		}
	}
}
