package main;

import javax.swing.*;

import view.LoginFrame;
import controller.FrameManager;

public class Main
{
	public static void main(String[] args)
	{
//		DataGenerator dg = new DataGenerator();
//		dg.addUsers();
//		dg.createCompanies();
//		dg.addStockToCompanies();
//		dg.addstockowned();

		SwingUtilities.invokeLater(new Runnable() //ONLY USE FOR GUI
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