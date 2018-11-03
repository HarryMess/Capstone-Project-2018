package main;

import javax.swing.*;

import controller.FrameManager;

public class Main
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() //ONLY USE FOR GUI
		{
			@Override
			public void run()
			{
				new FrameManager("Stock Market Simulator");				
			}
		});
	}
}