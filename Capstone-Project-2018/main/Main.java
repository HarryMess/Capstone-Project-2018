package main;
import view.LoginFrame;
import javax.swing.*;

import controller.FrameManager;

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
				DataGenerator dg = new DataGenerator();
				dg.addUsers();
				dg.createCompanies();
				dg.addStockToCompanies();
				dg.addstockowned();

			}
		});
	}
}