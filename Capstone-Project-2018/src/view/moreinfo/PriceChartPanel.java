package view.moreinfo;

import java.sql.SQLException;
import java.util.List;


/** This example can be found from the following web page:
 *  https://docs.oracle.com/javafx/2/charts/line-chart.htm
 */

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.StockTimeStamp;
import model.database.StockHistoryTable;

 
public class PriceChartPanel extends JFXPanel {
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PriceChartPanel(String companyCode) throws SQLException {
        String CompanyCode = companyCode;
	    StockHistoryTable shti = StockHistoryTable.getInstance();
	    
	    List<StockTimeStamp> stslist = shti.getStockHistory(CompanyCode);
        
	    final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        StockTimeStamp sts0 = stslist.get(0);
        
        String comcode = sts0.getCompanyCode();
        lineChart.setTitle("Stock Monitoring, 2018  ");
        
        xAxis.setLabel("Time");
        yAxis.setLabel("Price");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(comcode);
        
        //get price array
        
        for(int i = 0; i< stslist.size(); i ++) {
        	StockTimeStamp sts = stslist.get(i);
        	float price = sts.getMarketPrice();            	
        	String date = sts.getTimestamp().toString();
        	series1.getData().add(new XYChart.Data<String, Float>(date, price));
        }
 
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().addAll(series1);
       
        setScene(scene);
    }
    
}