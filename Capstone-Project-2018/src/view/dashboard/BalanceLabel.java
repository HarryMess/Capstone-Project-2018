package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BalanceLabel extends JLabel
{
	private int balance = 1000000;
	public BalanceLabel()
	{
		super("Current Balance: 1000000", LEFT);
		setBorder(new LineBorder(Color.BLACK)); //TODO: Make border look nicer

	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int newBalance) //TODO: Draw from User/StockAccount class
	{ //TODO: Better validation??
		if(newBalance >= 0)
		{
			balance = newBalance;
			this.setText("Current Balance: " + balance);
		}
	}
}
