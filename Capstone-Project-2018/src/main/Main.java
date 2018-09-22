package main;

import controller.FrameManager;

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
				DataGenerator dg = new DataGenerator();
				dg.addUsers();
				dg.createCompanies();
				dg.addStockToCompanies();
				dg.addstockowned();

			}
		});
	}
}