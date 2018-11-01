package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
//import view.moreinfo.PriceLineChart;
//import view.moreinfo.PriceLineChart2;

public class StockInfoListener extends MouseAdapter {

	private String companyCode;
	private Application chart;
	
	public StockInfoListener(String companyCode) {
		this.companyCode = companyCode;
//		chart = new PriceLineChart();
//		PriceLineChart.setCompany(companyCode);
	}
	
	public void mouseClicked(MouseEvent e) {
		try {			
//			chart.launch();
//			chart.start(new Stage());
//			new PriceLineChart2(companyCode);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
}
