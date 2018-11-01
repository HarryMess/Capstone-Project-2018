package model;

import java.sql.Timestamp;

/**
 * This class contains all the variables needed for transaction after a purchase ot sale has been made
 * Contains values from Transactions Table
 * @author Paul King
 * @version 1.0
 * @since 30/10/2018
 */
public class Transaction {

	// class member variables
	private int id;
	private final int buyerId;
	private final int sellerId;
	private final String companyCode;
	private final Timestamp dateTime;	
	private final float price;
	private final int amount;
	
	/**
	 * Constructor
	 * @param dateTime
	 * @param buyerId
	 * @param sellerId
	 * @param companyCode
	 * @param price
	 * @param amount
	 */
	public Transaction(Timestamp dateTime, int buyerId, int sellerId,
			String companyCode, float price, int amount) {
		
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.companyCode = companyCode;
		this.price = price;
		this.dateTime = dateTime;
		this.amount = amount;
	}

	/**
	 * 
	 * @return
	 */
	public int getTranasctionId() {
		return this.id;
	}	
	/**
	 * 
	 * @return
	 */
	public Timestamp getTimestamp() {
		return dateTime;
	}	
	public int getBuyerId() {
		return buyerId;
	}
	/**
	 * 
	 * @return
	 */
	public int getSellerId() {
		return sellerId;
	}
	/**
	 * 
	 * @return
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * 
	 * @return
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * 
	 * @return
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return buyerId + ", " + sellerId + ", " + companyCode + ", " + 
			   dateTime + ", " + price;
	}

}