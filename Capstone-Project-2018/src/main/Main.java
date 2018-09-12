package main;
import controller.FrameManager;
import view.LoginFrame;
import javax.swing.*;

public class Main
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				FrameManager fm = new FrameManager("Stock Market Simulator");
			}
		});
	}
}