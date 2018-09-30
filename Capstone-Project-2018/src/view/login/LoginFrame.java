package view.login;

import controller.FrameManager;
import view.AbstractFrame;
import view.TitlePanel;

public class LoginFrame extends AbstractFrame
{
	public LoginFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel(this));

		//Centre panel
		this.add(new LoginPanel(this));

		this.setVisible(true);
	}
}