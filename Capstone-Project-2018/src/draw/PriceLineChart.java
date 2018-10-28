package draw;

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
import model.Company;
 
public class PriceLineChart extends Application {
 

	@Override 
	    public void start(Stage stage) {
		Company company = new Company("Samplebank code", "Samplebank", 0);
    	ShowChart(stage,company);
    }
    
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
        public void ShowChart(Stage stage, Company company) {

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
            
            stage.setTitle(company.getName());
            lineChart.setTitle("Stock Monitoring, 2010  "+company.getName());
            
            xAxis.setLabel("Time");
            yAxis.setLabel("Price");

            XYChart.Series series1 = new XYChart.Series();
            series1.setName(company.getName());
            
            //get price array
            int[] price = getPrice(company);
            
            for(int i = 0; i< price.length ; i ++) {
            	series1.getData().add(new XYChart.Data<String, Integer>(i+"", price[i]));
            }
     
            Scene scene  = new Scene(lineChart,800,600);       
            lineChart.getData().addAll(series1);
           
            stage.setScene(scene);
            stage.show(); 	
        	
        }
	
    public int[] getPrice(Company company) {

		// get price array need change code here 
    	float[] testprice = new float[] {(float) 1.4,(float) 2.4,(float) 3.4,(float) 4.5,(float) 4.6,(float) 3.6,(float) 2.6,(float) 1.6};
		
		// change the price array to int array
		int[] price = new int[testprice.length];	
		for(int i=0;i< testprice.length; i++) {
			float a = testprice[i];
			int b = (int)a;
		    price[i]=b;
		}
		 return price;
	}
    
 
    public static void main(String[] args) {
        launch(args);
    }
    
}