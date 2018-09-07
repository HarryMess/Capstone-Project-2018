package main;

import view.LoginFrame;
import javax.swing.*;

public class App
{
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() // this is not compatible with the compiler used in the current eclipse project - Paul
			{
				LoginFrame loginFrame = new LoginFrame("Stock Market Simulator");
			}
		});
	}
}