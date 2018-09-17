package view;

import controller.FrameManager;

import javax.swing.*;
import java.awt.*;

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
