package view.moreinfo;

import controller.BuyListener;
import controller.FrameManager;
import controller.SellListener;
import javafx.embed.swing.JFXPanel;

import model.Stock;
import model.database.StocksTable;
import view.AbstractFrame;
import view.BackToDashboardPanel;
import view.dashboard.DashboardFrame;
import view.dashboard.StockTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class StockInfoFrame extends AbstractFrame
{
	private JTable table;
	private Object[][] rowData;
	private Object[] columnNames;
	private JTable stockTable;
	private JFXPanel graph;
	private String companyCode;
	private JPanel mainPanel;
	private BuyListener buyListener;
	private SellListener sellListener;

	public StockInfoFrame(String frameType, FrameManager fm, String title) throws SQLException
	{
		super(fm, title);

		DashboardFrame dashboardFrame = (DashboardFrame)fm.getFrame("dashboard");
		table = dashboardFrame.getTable(frameType);

		int selectedIndex = table.getSelectedRow();
		companyCode = (String)table.getValueAt(selectedIndex, 0);
		String pageTitle = "Company Info";

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
		mainPanel = new JPanel(new BorderLayout());
		JPanel tablePanel = new JPanel(new BorderLayout());
		JLabel companyName = new JLabel("COMPANY NAME HERE");

		JButton buyButton = new JButton("Buy");
		JButton sellButton = new JButton("Sell");
		JPanel buttonPanel = new JPanel();

		//Component settings
		buyButton.setHorizontalAlignment(SwingConstants.LEFT);
		sellButton.setHorizontalAlignment(SwingConstants.LEFT);

		// add listeners to buttons
		buyListener = new BuyListener(this, companyCode);
		sellListener = new SellListener(this, companyCode);

		buyButton.addActionListener(buyListener);
		sellButton.addActionListener(sellListener);

		//Table model
//		Object[][] rowData = {{"CBA", "72.19", "1.405%", "$1.00", "72.17", "72.19", "71.45", "72.31", "71.45", "12345"}};

		//Table
		stockTable = new JTable();
		stockTable.setRowSelectionAllowed(false);
		stockTable.getTableHeader().setReorderingAllowed(false);

		//Add components


		//graph
		graph = new StockHistoryChartPanel(companyCode);
		JLabel temp = new JLabel("THIS WILL BE A GRAPH OF PRICES OVER TIME");


		buttonPanel.add(buyButton);
		buttonPanel.add(sellButton);
		upperPanel.add(companyName, BorderLayout.SOUTH);
		mainPanel.add(graph, BorderLayout.SOUTH);
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
		int selectedIndex = table.getSelectedRow();
		StocksTable stocksInst = StocksTable.getInstance();
		Stock desiredStock = null;
		try
		{
			desiredStock = stocksInst.getStock(companyCode);
		} catch(SQLException e)
		{
			System.out.println(e.toString());
		}

		columnNames = new Object[]{"Code", "Company Name", "Market Price"};
		int numColumns = table.getColumnCount();
		rowData = new Object[1][numColumns];
		rowData[0][0] = desiredStock.getCode();
		rowData[0][1] = desiredStock.getCompanyName();
		rowData[0][2] = desiredStock.getMarketPrice();

		for(int i=0;i<numColumns;i++)
		{
			rowData[0][i] = table.getValueAt(selectedIndex, i);
			System.out.println(table.getValueAt(selectedIndex, i));
		}

		stockTable.setModel(new StockTableModel(rowData, columnNames));
	}

	public void redraw()
	{
		updateTable();
		int selectedIndex = table.getSelectedRow();
		companyCode = (String)table.getValueAt(selectedIndex, 0);


		mainPanel.remove(graph);
		try {
			graph = new StockHistoryChartPanel(companyCode);
		} catch(SQLException e) {
			System.out.println(e.toString());
		}
		mainPanel.add(graph, BorderLayout.SOUTH);

		buyListener.updateInfo(companyCode);
		sellListener.updateInfo(companyCode);

	}

}