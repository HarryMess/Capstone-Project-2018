package model;

import java.sql.Time;
import java.sql.Date;

public class Transaction {

	private final String id;
	private final String buyerId;
	private final String sellerId;
	private final String stockId;
	private final Date date;
	private final Time time;
	private final float price;
	
	public Transaction(String id, String buyerId, String sellerId, String stockId,
			Date date, Time time, float price) {
		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.stockId = stockId;
		this.date = date;
		this.time = time;
		this.price = price;
	}

	public String getTranasctionId() {
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

}