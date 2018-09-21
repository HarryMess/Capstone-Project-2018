package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.sql.Date;

public class Transaction {

	private final int id;
	
	// used in database
	private String buyerId;
	private String sellerId;
	private String stockId;
	
	private final TradingAccount buyer;
	private final TradingAccount seller;
	private final Stock stock;
	private final LocalDateTime dateTime;
	
	// not used anymore
	private Date date;
	private Time time;
	
	private final float price;
	
	public Transaction(int id, String buyerId, String sellerId, String stockId,
			Date date, Time time, float price) {
		
		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.stockId = stockId;
		this.date = date;
		this.time = time;
		this.price = price;
		buyer = null;
		seller = null;
		stock = null;
		dateTime = null;
	}
	
	public Transaction(int id, TradingAccount buyer, TradingAccount seller,
			Stock stock, LocalDateTime dateTime, float price) {
		
		this.id = id;
		this.price = price;
		this.buyer = buyer;
		this.seller = seller;
		this.stock = stock;
		this.dateTime = dateTime;
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

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}
	
	public float getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return id +", " + buyer + ", " + seller + ", " + stock + ", " + 
			   dateTime + ", " + price;
	}

}