package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel
{
	private BufferedImage logo;

	public TitlePanel(AbstractFrame parentFrame)
	{
		//Logo
		try
		{ //TODO: Make Logo scale automatically
			logo = ImageIO.read(new File("img/logo.jpg"));
		} catch(IOException e) {
			System.out.println("Exception reading image: " + e.toString());
			System.exit(0);
		}
		logo = resize(logo, 100, 100);
		JLabel picLabel = new JLabel(new ImageIcon(logo));
		JLabel upLabel = new JLabel("Stock Market Simulator");
		upLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		this.add(picLabel);
		this.add(upLabel);
		upLabel.setHorizontalAlignment(JLabel.CENTER);
		upLabel.setVerticalAlignment(JLabel.NORTH);
	}

	private static BufferedImage resize(BufferedImage logo, int x, int y)
	{
		BufferedImage newImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		Image tempImage = logo.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		Graphics2D g2d = newImage.createGraphics();
		g2d.drawImage(tempImage, 0, 0, null);
		g2d.dispose();
		return newImage;
	}
}
