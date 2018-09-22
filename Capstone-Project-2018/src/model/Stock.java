package model;

public class Stock {

	private String code;
	private String owner;
	private int quantity;

	public Stock(String code, String owner, int quantity) {
	
		this.code = code;
		this.owner = owner;	
		this.quantity = quantity;
	}

	public String getOwner() {
		return owner;
	}

	public String getCompany() {
		return code;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void removeShares(int amount) {
		quantity -= amount;
	}
	
	@Override
	public String toString() {
		return "Code: " + code + ", Owner: " + owner + ", Quantity: " + quantity;
	}

}