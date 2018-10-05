package model;

public class Stock {

	private String code;
	private int ownerId;
	private int quantity;
	
	public Stock(String code, int ownerId, int quantity) {
	
		this.code = code;
		this.ownerId = ownerId;	
		this.quantity = quantity;
	}

	public int getOwner() {
		return ownerId;
	}

	public String getCompany() {
		return code;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void addShares(int amount) {
		quantity = amount;
	}
	
	public void removeShares(int amount) {
		quantity -= amount;
	}
	
	@Override
	public String toString() {

		return "Code: " + code + ", Owner: " + ownerId + ", Quantity: " + quantity;
	}

}