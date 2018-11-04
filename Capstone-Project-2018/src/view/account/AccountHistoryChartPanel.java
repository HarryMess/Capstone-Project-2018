package view.account;

import java.sql.SQLException;
import java.util.List;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import model.AccountTimeStamp;
import model.database.AccountHistoryTable;

@SuppressWarnings("serial")
public class AccountHistoryChartPanel extends JFXPanel {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AccountHistoryChartPanel(int accountId) throws SQLException {
    	
        int Id = accountId;
        AccountHistoryTable ahti = AccountHistoryTable.getInstance();
	    
	    List<AccountTimeStamp> ahtlist = ahti.getAccountHistory(Id);
        
	    final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);
        AccountTimeStamp aht0 = ahtlist.get(0);
        
        int FirstId = (int) aht0.getAccountId();
        lineChart.setTitle("User: "+FirstId +" Account Histroy");
        
        xAxis.setLabel("Time");
        yAxis.setLabel("Price");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("User: "+FirstId+" Balance");
        
        //get balance array
        
        for(int i = 0; i< ahtlist.size(); i ++) {
        	AccountTimeStamp aht = ahtlist.get(i);
        	float balance = aht.getBalance();        
        	float ShareValue = aht.getShareValue();
        	float totalbalance = balance + ShareValue;
        	java.sql.Timestamp date = aht.getTimestamp();
        	series1.getData().add(new XYChart.Data<String, Float>(date.toString(), totalbalance));
        }
        
 
        Scene scene  = new Scene(lineChart, 320, 320);       
        lineChart.getData().addAll(series1);
       
        setScene(scene);    	
    } 
}
