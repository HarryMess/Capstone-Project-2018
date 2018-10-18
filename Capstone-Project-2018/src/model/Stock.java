package model;

public class Stock {

	private String code;
	private Company company;
	private int ownerId;
	private int quantity;

	public Stock(String code, int ownerId, int quantity) {
	
		this.code = code;
		this.ownerId = ownerId;	
		this.quantity = quantity;
	}
	
	public Stock(Company company, int quantity)	{
		this.company = company;
		this.quantity = quantity;
	}

	public int getOwner() {
		return ownerId;
	}
	
	
	public Company getCompany() {
		return company;
	}
	
	public String getCompanyByCode() {
		return code;
	}

	public int getQuantity() {
		return quantity;
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
		return "Code: " + code + ", Owner: " + ownerId + ", Quantity: " + quantity;
	}

}