package model;

public class Stock {

	private String id;
	private String companyId;
	private int amount;

	/**
	 * 
	 * @param id
	 * @param companyId
	 */
	public Stock(String id, String companyId) {
		// TODO - implement Stock.Stock
		this.id = id;
		this.companyId = companyId;
	}

	public String getId() {
		return this.id;
	}

	public String getCompany() {
		return companyId;
	}

	public int getAmount() {
		return this.amount;
	}

}