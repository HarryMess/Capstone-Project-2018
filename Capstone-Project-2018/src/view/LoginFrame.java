package view;

import controller.FrameManager;

public class LoginFrame extends AbstractFrame
{
	FrameManager fm;
	public LoginFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel());

		//Centre panel
		this.add(new LoginPanel(this));

		this.setVisible(true);
	}
}