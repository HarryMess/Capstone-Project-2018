package model;

public class Stock {

	private String code;
	private String ownerId;
	private int quantity;
	
	public Stock(String code, String ownerId, int quantity) {
	
		this.code = code;
		this.ownerId = ownerId;	
		this.quantity = quantity;
	}

	public String getOwner() {
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