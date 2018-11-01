package view;

import javax.swing.*;

import controller.FrameManager;

import java.awt.*;

public class RegisterFrame extends AbstractFrame
{
	FrameManager fm;
	public RegisterFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel(null));

		//Centre panel
		this.add(new RegisterPanel(this));
	}


}
