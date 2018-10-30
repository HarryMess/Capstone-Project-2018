package model;

import java.sql.Timestamp;

/**
 * This class contains all the variables needed for the stock value history.
 * Contains values from the StockHistory table 
 * @author Paul King - s3449513
 *
 */
public class StockTimeStamp {

	// data attributes
	private final Timestamp timestamp;
	private final String companyCode;
	private final float marketPrice;
	private final float profitPerHour;
	
	/**
	 * Constructor 1
	 * The profit per hour is set to zero in this constuctor
	 * @param timestamp
	 * @param companyCode
	 * @param marketPrice
	 */
	public StockTimeStamp(Timestamp timestamp, String companyCode, float marketPrice) {
		this.timestamp = timestamp;
		this.companyCode = companyCode;
		this.marketPrice = marketPrice;
		this.profitPerHour = 0;
	}
	
	/**
	 * Constructor 2
	 * @param timestamp
	 * @param companyCode
	 * @param marketPrice
	 * @param profitPerHour
	 */
	public StockTimeStamp(Timestamp timestamp, String companyCode, float marketPrice, float profitPerHour) {
		this.timestamp = timestamp;
		this.companyCode = companyCode;
		this.marketPrice = marketPrice;
		this.profitPerHour = profitPerHour;
	}
	
	/**
	 * 
	 * @return
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * 
	 * @return
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * 
	 * @return
	 */
	public float getMarketPrice() {
		return marketPrice;
	}
	/**
	 * 
	 * @return
	 */
	public float getProfitPerHour() {
		return profitPerHour;
	}
}