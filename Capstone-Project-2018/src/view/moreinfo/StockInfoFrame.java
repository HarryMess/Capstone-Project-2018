package view.moreinfo;

import controller.FrameManager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import view.AbstractFrame;
import view.BackToDashboardPanel;
import view.dashboard.DashboardFrame;
import view.dashboard.StockTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

public class StockInfoFrame extends AbstractFrame
{
	private JTable table;
	private Object[][] rowData;
	private Object[] columnNames;
	private JTable stockTable;
	
	// added by Paul
	private String companyCode;
	
	public StockInfoFrame(String frameType, FrameManager fm, String title) throws SQLException
	{
		super(fm, title);
		String pageTitle;
		companyCode = "A2M";

		switch(frameType)
		{
			case("transactions"):
				pageTitle = "Transaction Info";
				break;
			default:
				pageTitle = "Company Info";
		}

		DashboardFrame dashboardFrame = (DashboardFrame)fm.getFrame("dashboard");
		table = dashboardFrame.getTable(frameType);

		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentShown(ComponentEvent e)
			{
				System.out.println("TEST");
				updateTable();
			}
		});


		setLayout(new BorderLayout());

		//Create components
		BackToDashboardPanel upperPanel = new BackToDashboardPanel(this, pageTitle);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel tablePanel = new JPanel(new BorderLayout());
		JLabel companyName = new JLabel("COMPANY NAME HERE");

		JButton buyButton = new JButton("Buy");
		JButton sellButton = new JButton("Sell");
		JPanel buttonPanel = new JPanel();
		
		

		//Component settings
		buyButton.setHorizontalAlignment(SwingConstants.LEFT);
		sellButton.setHorizontalAlignment(SwingConstants.LEFT);


		//Table model
//		Object[][] rowData = {{"CBA", "72.19", "1.405%", "$1.00", "72.17", "72.19", "71.45", "72.31", "71.45", "12345"}};

		//Table
		stockTable = new JTable();
		stockTable.setRowSelectionAllowed(false);
		stockTable.getTableHeader().setReorderingAllowed(false);

		//graph
//		JPanel graph = new JPanel();
		JFXPanel graph = new PriceChartPanel(companyCode);
		JLabel temp = new JLabel("THIS WILL BE A GRAPH OF PRICES OVER TIME");
		
		
		// Added by Paul
//		Platform.runLater(new Runnable() {
//			
//            @Override
//            public void run() {
//            	companyCode = "A2M";
//                try {                	
//					new PriceChartPanel(companyCode);					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//       });

		//Add components
		if(!frameType.equals("transactions"))
		{
			buttonPanel.add(buyButton);
			buttonPanel.add(sellButton);
			upperPanel.add(companyName, BorderLayout.SOUTH);
			mainPanel.add(graph, BorderLayout.SOUTH);
		}

		graph.add(temp);
		tablePanel.add(stockTable.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(stockTable, BorderLayout.CENTER);
		tablePanel.add(buttonPanel, BorderLayout.SOUTH);

		mainPanel.add(tablePanel, BorderLayout.NORTH);


		add(upperPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}

	public void updateTable()
	{
		columnNames = new Object[]{"Code", "Last Price", "Change %", "Change $", "Bid", "Offer", "Open", "High", "Low", "Volume"};
		System.out.println("test2");
		int selectedIndex = table.getSelectedRow();
		int numColumns = table.getColumnCount();
		rowData = new Object[1][numColumns];

		for(int i=0;i<numColumns;i++)
		{
			rowData[0][i] = table.getValueAt(selectedIndex, i);
			System.out.println(table.getValueAt(selectedIndex, i));
		}

		stockTable.setModel(new StockTableModel(rowData, columnNames));
	}

}