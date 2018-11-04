package view;

import controller.FrameManager;

@SuppressWarnings("serial")
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
