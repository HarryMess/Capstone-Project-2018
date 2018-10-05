package model;

public class Stock {

	private String code;
	private Company company;
	private String owner;
	private int quantity;

	public Stock(String code, String owner, int quantity) {
	
		this.code = code;
		this.owner = owner;	
		this.quantity = quantity;
	}
	
	public Stock(Company company, int quantity)	{
		this.company = company;
		this.quantity = quantity;
	}

	public String getOwner() {
		return owner;
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
	
	public void removeShares(int amount) {
		quantity -= amount;
	}
	
	@Override
	public String toString() {
		return "Code: " + code + ", Owner: " + owner + ", Quantity: " + quantity;
	}

}