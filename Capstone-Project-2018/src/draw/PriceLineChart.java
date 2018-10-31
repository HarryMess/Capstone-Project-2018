package draw;

import java.util.List;

import com.sun.jmx.snmp.Timestamp;

/** This example can be found from the following web page:
 *  https://docs.oracle.com/javafx/2/charts/line-chart.htm
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.database.StockHistoryTable;
import model.database.StockTimeStamp;
 
public class PriceLineChart extends Application {
 

	@Override 
	    public void start(Stage stage) {
    	ShowChart(stage, "");
    }
    
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
        public void ShowChart(Stage stage, String companyCode) {
 
    	    StockHistoryTable shti = StockHistoryTable.getInstance();
    	    StockTimeStamp stsi = StockTimeStamp.getInstance();
    	    
    	    List<StockTimeStamp> stslist = shti.getStockHistory(companyCode);
            
    	    final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
            StockTimeStamp sts0 = stslist[0];
            
            String comcode = sts0.getCompanyCode();
            stage.setTitle(comcode);
            lineChart.setTitle("Stock Monitoring, 2018  ");
            
            xAxis.setLabel("Time");
            yAxis.setLabel("Price");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName(comcode);
            
            //get price array
            int[] price = getPrice();
            
            for(int i = 0; i< stslist.length ; i ++) {
            	StockTimeStamp sts = stslist[i];
            	int price1 = sts.getPrice();
            	String date = sts.getTimestamp();
            	series1.getData().add(new XYChart.Data<String, Integer>(date, price1));
            }
     
            Scene scene  = new Scene(lineChart,800,600);       
            lineChart.getData().addAll(series1);
           
            stage.setScene(scene);
            stage.show(); 	
        	
        }

	
    
 
    public static void main(String[] args) {
        launch(args);
    }
    
}