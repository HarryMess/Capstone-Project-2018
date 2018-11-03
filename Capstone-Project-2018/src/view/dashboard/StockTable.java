package view.dashboard;

import controller.LinkListener;
import view.AbstractFrame;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class StockTable extends JPanel
{
	private JTable table;
	public StockTable(String linkedFrame, AbstractTablePanel parentPanel, StockTableModel model)
	{ //TODO: Actually pull stock data from DataBase
		setLayout(new BorderLayout());
		AbstractFrame parentFrame = parentPanel.getParentFrame();

		//Create components
		table = new JTable(model); //Actual table
		JLabel moreInfoLabel = new JLabel("More Info on Selected Item");
		JScrollPane scrollPane = new JScrollPane(table);

		//Table settings
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		//Component settings
		moreInfoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Change to hand cursor on hover
		moreInfoLabel.setForeground(Color.blue);
		moreInfoLabel.setHorizontalAlignment(JLabel.CENTER);

		//Action listeners
		
		//TODO replace last argument with a variable containing the selected company code
		moreInfoLabel.addMouseListener(new LinkListener(parentFrame.getFrameManager(), parentFrame, linkedFrame, "A2M"));

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
}

