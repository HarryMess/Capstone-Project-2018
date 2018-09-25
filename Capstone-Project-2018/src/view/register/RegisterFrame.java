package view.register;

import javax.swing.*;

import controller.FrameManager;

import java.awt.*;

import view.AbstractFrame;
import view.TitlePanel;

public class RegisterFrame extends AbstractFrame
{
	FrameManager fm;
	public RegisterFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel(this));

		//Centre panel
		this.add(new RegisterPanel(this));
	}


}
