package view.dashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BuyStockPanel extends JPanel
{
	public BuyStockPanel()
	{
		add(new JLabel("Buy New Stocks:"));
		setBorder(new LineBorder(Color.BLACK));
	}
}


//	public void updateResults()
//	{
//		Collection<Player> players = gameEngine.getAllPlayers();
//
//		JLabel title = new JLabel("Results:"); //Start with results heading
//		title.setFont(title.getFont().deriveFont(24.0f));
//		title.setHorizontalAlignment(SwingConstants.CENTER);
//
//		this.add(title);
//
//		for(Player player : players)
//		{ //For each player, display their current information
//			String output = String.format("%s now has %d points.", player.getPlayerName(), player.getPoints());
//			JLabel playerLabel = new JLabel(output);
//			this.add(playerLabel);
//		}
//	}
