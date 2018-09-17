package view;

import controller.FrameManager;

import javax.swing.*;

public class ForgotPasswordFrame extends AbstractFrame
{

	public ForgotPasswordFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel(this));

		//Centre panel
		this.add(new ForgotPasswordPanel(this));

		this.setVisible(true);
	}

}
