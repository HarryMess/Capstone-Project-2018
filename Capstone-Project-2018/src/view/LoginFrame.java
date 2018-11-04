package view;

import controller.FrameManager;

@SuppressWarnings("serial")
public class LoginFrame extends AbstractFrame
{
	FrameManager fm;
	public LoginFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel(null));

		//Centre panel
		this.add(new LoginPanel(this));

		this.setVisible(true);
	}
}