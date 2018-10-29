package model;

import java.sql.Timestamp;

public class Transaction {

	private int id;
	
	// used in database
	private final int buyerId;
	private final int sellerId;
	private final String companyCode;
	private final Timestamp dateTime;	
	private final float price;
	private final int amount;
	
	public Transaction(Timestamp dateTime, int buyerId, int sellerId,
			String companyCode, float price, int amount) {
		
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.companyCode = companyCode;
		this.price = price;
		this.dateTime = dateTime;
		this.amount = amount;
	}

	public int getTranasctionId() {
		return this.id;
	}
	
	public Timestamp getTimestamp() {
		return dateTime;
	}
	
	public int getBuyerId() {
		return buyerId;
	}

	public int getSellerId() {
		return sellerId;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getCompanyCode() {
		return companyCode;
	}
	
	@Override
	public String toString() {
		return buyerId + ", " + sellerId + ", " + companyCode + ", " + 
			   dateTime + ", " + price;
	}

}