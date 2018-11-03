package view.dashboard;

import controller.LinkListener;
import controller.StockTableListener;
import controller.TableSelectionListener;
import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class StockTable extends JPanel
{
	private JTable table;
	private JLabel moreInfoLabel;
	private AbstractTablePanel parentPanel;
	public StockTable(String linkedFrame, AbstractTablePanel parentPanel, StockTableModel model)
	{
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		AbstractFrame parentFrame = parentPanel.getParentFrame();

		// Create components
		table = new JTable(model); //Actual table
		moreInfoLabel = new JLabel("More Info on Selected Item");
		JScrollPane scrollPane = new JScrollPane(table);

		// Table settings
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		// Component settings

		moreInfoLabel.setHorizontalAlignment(JLabel.CENTER);

		// Action listeners	
		moreInfoLabel.addMouseListener(new StockTableListener(parentFrame.getFrameManager(), parentFrame, linkedFrame, table));
		table.getSelectionModel().addListSelectionListener(new TableSelectionListener((DashboardFrame)parentFrame));

		//Add table header, then table itself
		//add(table.getTableHeader(), BorderLayout.NORTH);
		//add(table, BorderLayout.CENTER);
		add(scrollPane);
		add(moreInfoLabel, BorderLayout.SOUTH);
	}

	public JTable getTable()
	{
		return table;
	}

	public JLabel getMoreInfo()
	{
		return moreInfoLabel;
	}
}

