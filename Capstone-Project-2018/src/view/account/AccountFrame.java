package view.account;

import controller.FrameManager;
import view.AbstractFrame;
import view.BackToDashboardPanel;

import javax.swing.*;

public class AccountFrame extends AbstractFrame
{
	public AccountFrame(FrameManager fm, String title)
	{
		super(fm, title);

		add(new BackToDashboardPanel(this, "My Account"));
	}
}
