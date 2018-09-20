package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyStockPanel extends JPanel
{
	public MyStockPanel()
	{
		add(new JLabel("My Stocks"));
		setBorder(new LineBorder(Color.BLACK));
	}
}
