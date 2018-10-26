package draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import model.Company;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PriceLineGui {

	private JFrame frame;
	private JButton btnGraph;
	private JButton btnReturn;
	private JTextArea PriceLine;
	private JTextArea CompanyInfo;
	private JTextField CompanyName;
	private JTextField SharePrice;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PriceLineGui window = new PriceLineGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PriceLineGui() {
		initialize();
		event();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 432, 26);
		frame.getContentPane().add(menuBar);
		
	    btnGraph = new JButton("Graph");
		menuBar.add(btnGraph);
		
	    btnReturn = new JButton("Return");
		menuBar.add(btnReturn);

		
		PriceLine = new JTextArea("",10,10);
		PriceLine.setForeground(Color.BLACK);
		PriceLine.setEditable(false);
		PriceLine.setBounds(10, 127, 408, 173);
		frame.getContentPane().add(PriceLine);
		
		JLabel lblCompanyName = new JLabel("Company Name:");
		lblCompanyName.setToolTipText("");
		lblCompanyName.setBounds(30, 41, 120, 18);
		frame.getContentPane().add(lblCompanyName);
		
		JLabel lblSharePrice = new JLabel("Share Price:");
		lblSharePrice.setBounds(30, 62, 108, 18);
		frame.getContentPane().add(lblSharePrice);
		
		CompanyName = new JTextField();
		CompanyName.setBounds(137, 42, 108, 20);
		CompanyName.setEditable(false);
		frame.getContentPane().add(CompanyName);
		CompanyName.setColumns(10);
		
		SharePrice = new JTextField();
		SharePrice.setBounds(30, 85, 370, 24);
		SharePrice.setEditable(false);
		frame.getContentPane().add(SharePrice);
		SharePrice.setColumns(10);
		
		CompanyInfo = new JTextArea();
		CompanyInfo.setBounds(10, 39, 408, 75);
		CompanyInfo.setEditable(false);
		frame.getContentPane().add(CompanyInfo);
		
		
	}
	
	// 
	private void event(){
		//sample company object
		Company company = new Company("Samplebank code", "Samplebank", 0);
		
		// return button
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		// show graph button
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PriceLine line = new PriceLine();
			    
				// set company info
				  // set company name 
				CompanyName.setText(line.companyInfo(company));
				  // set company price
				SharePrice.setText(line.companyPrice(company));
				
				//  draw the coordinate in the area
				String b = line.drawborder();
			    PriceLine.setText(b);
			   
			    // draw the price line by using given price array
			    int[] price = line.drawLine(company);
				for(int i = 0; i < price.length; i++) {
					int value = price[i];
					int ypos = 11+(Math.abs(value-6))*104;
					int xpos = ypos+i*5;
				    PriceLine.replaceRange("*", xpos, xpos+2);
				}

		  	}
		});
	
	}
}
