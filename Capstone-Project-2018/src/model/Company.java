package model;

import java.util.Random;

public class Company {

	private String code;
	private String name;
	private float marketPrice;
	private float profitPerStock;
	private final int totalShares;
	
	private final int MIN_PRICE = 10;
	private final int MAX_PRICE = 100;
	private Random randomGen;

	public Company(String code, String name, int totalShares) {
		
		this.code = code;
		this.name = name;
		this.totalShares = totalShares;
		
		randomGen = new Random();		
		setRandomPrice();
		profitPerStock = 0;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public int getTotalShares() {
		return totalShares;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public void setRandomPrice() {		
		marketPrice = (randomGen.nextInt((int) (MAX_PRICE - MIN_PRICE)) + MIN_PRICE );	
	}

	public float getProfitPerStock() {
		return this.profitPerStock;
	}

	public void setProfitPerStock(float profitPerStock) {
		this.profitPerStock = profitPerStock;
	}
	
	@Override
	public String toString() {
		return "Code: " + code + ", Name: " + name + ", Total Shares: " + totalShares + ", Price Per Share: $" + marketPrice + ", ";
	}

}