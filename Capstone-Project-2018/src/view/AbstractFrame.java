package view;

import javax.swing.*;
import controller.FrameManager;
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

		//Frame settings TODO: Fix minimum size and try to draw current bounds from previous frame
		this.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		this.setMinimumSize(new Dimension(500, 250));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));

		//Frame settings TODO: Fix minimum size TODO: fix maximising not carrying over
		setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		setMinimumSize(new Dimension(500, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1)); //TODO: Change this (not all frames have this layout)
	}

	public FrameManager getFrameManager()
	{
		return fm;
	}

	@Override
	public void setVisible(boolean var)
	{
		super.setVisible(var);

		getContentPane().requestFocusInWindow();
		//Makes it so no text fields are in focus when frame is set to visible.
	}

	public void redraw(){}
}
