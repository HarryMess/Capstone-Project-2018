package model;

/**
 * This class contains all the variables needed to stock and company information
 * Contains value from Stocks table
 * @author Paul King
 * @version 1.0
 * @since 30/10/2018
 */
public class Stock {

	// columns from stocks table
	private String code;
	private String companyName;
	private int ownerId;
	private int quantity;
	private double marketPrice;
	private float profitPerHour;
	
	private final int INCREASE_TRESHOLD = 9;
	private final int DECREASE_TRESHOLD = 4;
	
	/**
	 * The quantity is set to 1 by default and the profitPerHour to 0 in this constructor
	 * @param code
	 * @param companyName
	 * @param ownerId
	 * @param marketPrice
	 */
	public Stock(String code, String companyName, int ownerId, float marketPrice) {
		
		this.code = code;
		this.companyName = companyName;
		this.quantity = 1;
		this.ownerId = ownerId;
		this.marketPrice = marketPrice;
		this.profitPerHour = 0;
	}

	/**
	 * Constructor 2
	 * @param code
	 * @param companyName
	 * @param ownerId
	 * @param marketPrice
	 * @param quantity
	 * @param profitPerHour
	 */
	public Stock(String code, String companyName, int ownerId, float marketPrice, 
			int quantity, float profitPerHour) {
		
		this.code = code;
		this.companyName = companyName;
		this.quantity = quantity;
		this.ownerId = ownerId;
		this.marketPrice = marketPrice;
		this.profitPerHour = profitPerHour;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 
	 * @return
	 */
	public int getOwner() {
		return ownerId;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getMarketPrice() {
		return marketPrice;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getProfitPerHour() {
		return profitPerHour;
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void addShares(int amount) {
		quantity = amount;
	}
	
	/**
	 * 
	 * @param ownerId
	 */
	public void setOwner(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 
	 * @param amount
	 */
	public void removeShares(int amount) {
		quantity -= amount;
	}
	
	/**
	 * Updates the price of the stock by a small amount at random using an algorithm
	 * This method is to be called on every stock once an hour
	 */
	public void updatePrice() {
		double randomNumber = Math.random();
//		System.out.println("Random number: " + randomNumber);
		
		double adjustment;
		
		if(randomNumber > 0.5) {
			adjustment =  1 + (randomNumber / INCREASE_TRESHOLD);
//			System.out.println("Price Adjustment: " + adjustment);
			marketPrice *= adjustment;
		}
		else {
			adjustment = 1 - (randomNumber / DECREASE_TRESHOLD);
//			System.out.println("Price Adjustment: " + adjustment);
			marketPrice *= adjustment;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Code: " + code + ", Name: " + companyName + ", Owner: " + ownerId + ", Price: " + marketPrice + " Quantity: " + quantity;
	}

}