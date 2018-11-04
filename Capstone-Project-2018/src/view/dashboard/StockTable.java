package view.dashboard;

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
		this.setParentPanel(parentPanel);
		setLayout(new BorderLayout());
		AbstractFrame parentFrame = parentPanel.getParentFrame();



		// Create components
		table = new JTable(model); //Actual table

		JScrollPane scrollPane = new JScrollPane(table);

		// Table settings
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		table.getSelectionModel().addListSelectionListener(new TableSelectionListener(parentFrame));

		add(scrollPane);
		if(!linkedFrame.equals("transactions"))
		{
			moreInfoLabel = new JLabel("More Info on Selected Item");
			moreInfoLabel.setHorizontalAlignment(JLabel.CENTER);
			moreInfoLabel.addMouseListener(new StockTableListener(parentFrame.getFrameManager(), parentFrame, linkedFrame, table));
			add(moreInfoLabel, BorderLayout.SOUTH);
		}
	}

	public JTable getTable()
	{
		return table;
	}

	public JLabel getMoreInfo()
	{
		return moreInfoLabel;
	}

	public AbstractTablePanel getParentPanel() {
		return parentPanel;
	}

	public void setParentPanel(AbstractTablePanel parentPanel) {
		this.parentPanel = parentPanel;
	}
}

