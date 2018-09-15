package view;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame
{
	public LoginFrame(String title)
	{
		super(title);

		//For convenience
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();

		//Frame settings
		this.setBounds(screenWidth/4, screenHeight/4, screenWidth/2, screenHeight/2);
		this.setMinimumSize(new Dimension(500, 250));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));
		//imglabelapprove.setHorizontalAlignment(JLabel.CENTER);
		//Upper panel
		//JPanel upPane = new JPanel();
		JLabel upLabel = new JLabel("Stock Market Simulator");
		upLabel.setFont(new Font("Century Gothic", Font.PLAIN, 32));
		//upPane.add(upLabel);
		upLabel.setHorizontalAlignment(JLabel.CENTER);
		upLabel.setVerticalAlignment(JLabel.NORTH);
		this.add(upLabel);

		//Middle panel
//		JPanel midPane = new JPanel();
		JLabel midLabel = new JLabel("Middle section");
		midLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(midLabel);

		//Lower panel
//		JPanel lowPane = new JPanel();
		JLabel lowLabel = new JLabel("Lower section");
		lowLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(lowLabel);

		this.setVisible(true);
	}
}