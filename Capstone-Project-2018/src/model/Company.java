package model;

public class Company {

	private String code;
	private String name;
	private float marketPrice;
	private float profitPerStock;

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Company(String code, String name) {
		
		this.code = code;
		this.name = name;
	}

	public String getId() {
		return code;
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public float getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 
	 * @param marketPrice
	 */
	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public float getProfitPerStock() {
		return this.profitPerStock;
	}

	/**
	 * 
	 * @param profitPerStock
	 */
	public void setProfitPerStock(float profitPerStock) {
		this.profitPerStock = profitPerStock;
	}

}