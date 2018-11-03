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
		add(new TitlePanel(this));

		//Centre panel
		add(new LoginPanel(this));

		setVisible(true);

	}
}