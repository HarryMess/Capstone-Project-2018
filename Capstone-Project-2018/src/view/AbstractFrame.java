package view;

import controller.FrameManager;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractFrame extends JFrame
{
	private FrameManager fm;
	public AbstractFrame(FrameManager fm, String frameTitle)
	{
		super(frameTitle);
		this.fm = fm;

		//For convenience
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();

		//Frame settings TODO: Fix minimum size and maximising not carrying over
		this.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		this.setMinimumSize(new Dimension(500, 250));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1)); //TODO: Change this
	}

	public FrameManager getFrameManager()
	{
		return fm;
	}
}
