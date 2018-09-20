package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TransactionsPanel extends JPanel
{
	public TransactionsPanel()
	{
		add(new JLabel("Recent Transactions"));
		setBorder(new LineBorder(Color.BLACK));
	}
}