package JFx;

import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.StockTimeStamp;
import model.database.StockHistoryTable;

import model.StockTimeStamp;
 
public class PriceLineChart extends Application {
 

	@Override 
    public void start(Stage stage) throws SQLException {
		ShowChart(stage, "");
    }
    
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void ShowChart(Stage stage, String companyCode) throws SQLException {
        String CompanyCode = companyCode;
	    StockHistoryTable shti = StockHistoryTable.getInstance();
	    
	    List<StockTimeStamp> stslist = shti.getStockHistory(CompanyCode);
        
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
        
        //get price array
        
        for(int i = 0; i< stslist.size(); i ++) {
        	StockTimeStamp sts = stslist.get(i);
        	float price = sts.getMarketPrice();            	
        	java.sql.Timestamp date = sts.getTimestamp();
        	series1.getData().add(new XYChart.Data<java.sql.Timestamp, Float>(date, price));
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