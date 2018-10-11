package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import database.StockMarket;

import java.sql.Date;

public class Transaction {

	private int id;
	
	// used in database
	private String buyerId;
	private String sellerId;
	private String stockId;
	
	private final TradingAccount buyer;
	private final TradingAccount seller;
	private final Stock stock;
	private final Timestamp dateTime;
	
	private final float price;
	private final int amount;
	
	public Transaction(Timestamp dateTime, String buyerId, String sellerId,
			String stockId, float price, int amount) {
		
//		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.stockId = stockId;
		this.price = price;
		this.dateTime = dateTime;
		this.amount = amount;
		buyer = null;
		seller = null;
		stock = null;
	}
	
	public Transaction(TradingAccount buyer, TradingAccount seller,
			Stock stock, Timestamp dateTime, float price) {
		
//		this.id = id;
		this.price = price;
		this.buyer = buyer;
		this.seller = seller;
		this.stock = stock;
		this.dateTime = dateTime;
		this.amount = stock.getQuantity();
	}
	
	public void makeTransaction() {
		
		// get the Stock Market object
		@SuppressWarnings("unused")
		Model model = Model.getInstance();		
		// transfer the funds
		StockMarket market = StockMarket.getInstance();		
		
		market.transferFunds(buyer, seller, price);		
	}

	public int getTranasctionId() {
		return this.id;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public String getSellerId() {
		return sellerId;
	}
	
	public String getStockId() {
		return stockId;
	}
	
	public float getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return buyer + ", " + seller + ", " + stock + ", " + 
			   dateTime + ", " + price;
	}

}