package draw;

import java.sql.SQLException;
import java.util.List;

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
import model.StockTimeStamp;
 
public class PriceLineChart extends Application {
 

	@Override 
	    public void start(Stage stage) {
    	try {
			ShowChart(stage, "A2M");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void ShowChart(Stage stage, String companyCode) throws SQLException {
 
        StockHistoryTable shti = StockHistoryTable.getInstance();
//      StockTimeStamp stsi = StockTimeStamp.getInstance();
	    
	    List<StockTimeStamp> stslist = shti.getStockHistory(companyCode);
        
	    final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        StockTimeStamp sts0 = stslist.get(0);
        
        String comcode = sts0.getCompanyCode();
        stage.setTitle(comcode);
        lineChart.setTitle("Stock Monitoring, 2018  ");
        
        xAxis.setLabel("Time");
        yAxis.setLabel("Price");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(comcode);
        
        for(int i = 0; i< stslist.size() ; i ++) {
        	StockTimeStamp sts = stslist.get(i);
        	double price1 = sts.getMarketPrice();
        	String date = sts.getTimestamp().toString();
        	series1.getData().add(new XYChart.Data<String, Integer>(date, (int) price1));
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