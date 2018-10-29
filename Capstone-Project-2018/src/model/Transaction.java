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
	
//	private final TradingAccount buyer;
//	private final TradingAccount seller;
//	private final Stock stock;

	
	
	public Transaction(Timestamp dateTime, int buyerId, int sellerId,
			String companyCode, float price, int amount) {
		
//		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.companyCode = companyCode;
		this.price = price;
		this.dateTime = dateTime;
		this.amount = amount;
//		buyer = null;
//		seller = null;
//		stock = null;
	}
	
//	public Transaction(Timestamp dateTime, TradingAccount buyer, TradingAccount seller,
//			Stock stock, float price) {
//		
////		this.id = id;
//		this.dateTime = dateTime;
//		this.price = price;
//		this.buyer = buyer;
//		this.seller = seller;
//		this.stock = stock;
//
//		this.amount = stock.getQuantity();
//	}

	public int getTranasctionId() {
		return this.id;
	}
	
	public Timestamp getTimestamp() {
		return dateTime;
	}

//	public int getBuyerId() {
//		return buyer.getId();
//	}
//
//	public int getSellerId() {
//		return seller.getId();
//	}
	
	public int getBuyerId() {
		return buyerId;
	}

	public int getSellerId() {
		return sellerId;
	}
	
//	
//	public String getStockCode() {
//		return stock.getCompanyCode();
//	}
	
	public float getPrice() {
		return price;
	}
	
	public String getCompanyCode() {
		return companyCode;
	}
	
//	@Override
//	public String toString() {
//		return buyer + ", " + seller + ", " + stock + ", " + 
//			   dateTime + ", " + price;
//	}

}