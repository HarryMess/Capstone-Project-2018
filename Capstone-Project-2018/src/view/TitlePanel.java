package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TitlePanel extends JPanel
{
	JFrame parentFrame;
	private BufferedImage logo;

	public TitlePanel()
	{
		this.parentFrame = parentFrame;
		//Logo
		try
		{ //TODO: Make Logo scale automatically
			logo = ImageIO.read(new File("img/Placeholder.png"));
		} catch(IOException e) {
			System.out.println("Exception reading image: " + e.toString());
			System.exit(0);
		}

		JLabel picLabel = new JLabel(new ImageIcon(logo));
		JLabel upLabel = new JLabel("Stock Market Simulator");
		upLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		this.add(picLabel);
		this.add(upLabel);
		upLabel.setHorizontalAlignment(JLabel.CENTER);
		upLabel.setVerticalAlignment(JLabel.NORTH);
	}
}
