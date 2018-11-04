package view;

import controller.FrameManager;

@SuppressWarnings("serial")
public class ForgotPasswordFrame extends AbstractFrame
{

	public ForgotPasswordFrame(FrameManager fm, String title)
	{
		super(fm, title);

		//Upper panel
		this.add(new TitlePanel(null));

		//Centre panel
		this.add(new ForgotPasswordPanel(this));

		this.setVisible(true);
	}

}
