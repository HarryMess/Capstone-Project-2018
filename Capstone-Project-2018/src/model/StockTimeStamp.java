package model;

import java.sql.Timestamp;

public class StockTimeStamp {

	// data attributes
	private final Timestamp timestamp;
	private final String companyCode;
	private final float marketPrice;
	private final float profitPerHour;
	
	public StockTimeStamp(Timestamp timestamp, String companyCode, float marketPrice) {
		this.timestamp = timestamp;
		this.companyCode = companyCode;
		this.marketPrice = marketPrice;
		this.profitPerHour = 0;
	}
	
	public StockTimeStamp(Timestamp timestamp, String companyCode, float marketPrice, float profitPerHour) {
		this.timestamp = timestamp;
		this.companyCode = companyCode;
		this.marketPrice = marketPrice;
		this.profitPerHour = profitPerHour;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public float getMarketPrice() {
		return marketPrice;
	}

	public float getProfitPerHour() {
		return profitPerHour;
	}
	
}