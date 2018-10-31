package main;

import model.Stock;

public class PriceChangeTest {

	public static void main(String[] args) {
		Stock stock = new Stock("A2M", "The A2 Milk Company Limited", 1, 50000);
		
		System.out.println("Initial Price: " + stock.getMarketPrice() + "\n");
		
		for (int i=1; i<=20; i++) {
			stock.updatePrice();
			System.out.printf("New price %d: $%.2f\n", i, (float) stock.getMarketPrice());
		}
		
	}

}
