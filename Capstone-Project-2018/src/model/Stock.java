package model;

public class Stock {

	// columns from stocks table
	private String code;
	private String companyName;
	private int ownerId;
	private int quantity;
	private float marketPrice;
	private float profitPerHour;
	
	public Stock(String code, String companyName, int ownerId, float marketPrice) {
		
		this.code = code;
		this.companyName = companyName;
		this.quantity = 1;
		this.ownerId = ownerId;
		this.marketPrice = marketPrice;
		this.profitPerHour = 0;
	}

	public Stock(String code, String companyName, int ownerId, float marketPrice, 
			int quantity, float profitPerHour) {
		
		this.code = code;
		this.companyName = companyName;
		this.quantity = quantity;
		this.ownerId = ownerId;
		this.marketPrice = marketPrice;
		this.profitPerHour = profitPerHour;
	}
	
	public String getCode() {
		return code;
	}

	public String getCompanyName() {
		return companyName;
	}

	public int getOwner() {
		return ownerId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public float getMarketPrice() {
		return marketPrice;
	}
	
	public float getProfitPerHour() {
		return profitPerHour;
	}
	
	public void addShares(int amount) {
		quantity = amount;
	}
	
	public void setOwner(int ownerId) {
		this.ownerId = ownerId;
	}
	
	public void removeShares(int amount) {
		quantity -= amount;
	}
	
	@Override
	public String toString() {
		return "Code: " + code + ", Name: " + companyName + ", Owner: " + ownerId + ", Price: " + marketPrice + " Quantity: " + quantity;
	}

}