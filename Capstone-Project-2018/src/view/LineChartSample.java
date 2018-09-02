package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class LineChartSample extends Application {
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
       
        lineChart.setTitle("Stock Monitoring, 2010");
                          
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");
        
        series1.getData().add(new XYChart.Data<String, Integer>("Jan", 23));
        series1.getData().add(new XYChart.Data<String, Integer>("Feb", 14));
        series1.getData().add(new XYChart.Data<String, Integer>("Mar", 15));
        series1.getData().add(new XYChart.Data<String, Integer>("Apr", 24));
        series1.getData().add(new XYChart.Data<String, Integer>("May", 34));
        series1.getData().add(new XYChart.Data<String, Integer>("Jun", 36));
        series1.getData().add(new XYChart.Data<String, Integer>("Jul", 22));
        series1.getData().add(new XYChart.Data<String, Integer>("Aug", 45));
        series1.getData().add(new XYChart.Data<String, Integer>("Sep", 43));
        series1.getData().add(new XYChart.Data<String, Integer>("Oct", 17));
        series1.getData().add(new XYChart.Data<String, Integer>("Nov", 29));
        series1.getData().add(new XYChart.Data<String, Integer>("Dec", 25));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Portfolio 2");
        series2.getData().add(new XYChart.Data<String, Integer>("Jan", 33));
        series2.getData().add(new XYChart.Data<String, Integer>("Feb", 34));
        series2.getData().add(new XYChart.Data<String, Integer>("Mar", 25));
        series2.getData().add(new XYChart.Data<String, Integer>("Apr", 44));
        series2.getData().add(new XYChart.Data<String, Integer>("May", 39));
        series2.getData().add(new XYChart.Data<String, Integer>("Jun", 16));
        series2.getData().add(new XYChart.Data<String, Integer>("Jul", 55));
        series2.getData().add(new XYChart.Data<String, Integer>("Aug", 54));
        series2.getData().add(new XYChart.Data<String, Integer>("Sep", 48));
        series2.getData().add(new XYChart.Data<String, Integer>("Oct", 27));
        series2.getData().add(new XYChart.Data<String, Integer>("Nov", 37));
        series2.getData().add(new XYChart.Data<String, Integer>("Dec", 29));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Portfolio 3");
        series3.getData().add(new XYChart.Data<String, Integer>("Jan", 44));
        series3.getData().add(new XYChart.Data<String, Integer>("Feb", 35));
        series3.getData().add(new XYChart.Data<String, Integer>("Mar", 36));
        series3.getData().add(new XYChart.Data<String, Integer>("Apr", 33));
        series3.getData().add(new XYChart.Data<String, Integer>("May", 31));
        series3.getData().add(new XYChart.Data<String, Integer>("Jun", 26));
        series3.getData().add(new XYChart.Data<String, Integer>("Jul", 22));
        series3.getData().add(new XYChart.Data<String, Integer>("Aug", 25));
        series3.getData().add(new XYChart.Data<String, Integer>("Sep", 43));
        series3.getData().add(new XYChart.Data<String, Integer>("Oct", 44));
        series3.getData().add(new XYChart.Data<String, Integer>("Nov", 45));
        series3.getData().add(new XYChart.Data<String, Integer>("Dec", 44));
        
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().addAll(series1, series2, series3);
       
        stage.setScene(scene);
        stage.show();
    }
 
 
    public static void main(String[] args) {
        launch(args);
    }
    
}