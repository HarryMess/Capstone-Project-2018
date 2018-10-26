package draw;

import model.Company;

public class PriceLine {
	
	private int[] price;
    private static final String sep = System.getProperty("line.separator"); 
	
    // to draw the coordinate for price
	public String drawborder() {
		String x = "Time";
		String y = "Price";
		String b = "|"+"                                                  "+"                                                  "+ sep;
		String bl = "----------------------------------------------------------------------------------------->" + x;
		String bor = y+sep+sep
				     +"6"+b+"5"+b+"4"+b+"3"+b+"2"+b+"1"+b					 
				     +"0|"+bl;
		return bor;
	}

	
	// get the price array  
	public int[] drawLine(Company company) {

		// get price array    need change code here 
		double[] testprice = new double[] {1.4,2.4,3.4,4.5,4.6,3.6,2.6,1.6};
		
		// change the price array to int array
		int[] price = new int[testprice.length];	
		for(int i=0;i< testprice.length; i++) {
			double a = testprice[i];
			int b = (int)a;
		    price[i]=b;
		}
		 return price;
	}
	
	// get company name
	public String companyInfo(Company company) {
		
		String companyName = company.getName();
		return companyName;
	}
	
	// get company price
	public String companyPrice(Company company) {
		float p = company.getMarketPrice();
		String price = p+"";
		return price;
	}
	
}