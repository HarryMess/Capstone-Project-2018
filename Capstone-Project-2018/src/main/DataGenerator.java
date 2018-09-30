package main;

import java.util.List;

import model.*;

public class DataGenerator {

	private static Model model = Model.getInstance();
	
	public DataGenerator( ) {
		model = Model.getInstance();
	}
	
//	public void main(String[] args) {
//
//		addUsers();
//		createCompanies();
//		addStockToCompanies();
//		showData();
//	}

	public static void main(String[] args) {

		addUsers();
		createCompanies();
		addStockToCompanies();
		
		List<User> users = model.getUsers();
		List<Company> companies = model.getCompanies();
		List<Stock> stocks = model.getStocks();
		
		System.out.println("Users");
		System.out.println("-----");
		for(User user : users) {
			System.out.print(user.toString());
			System.out.println("Balance: $" + user.getTradingAccount().getBalance());
			System.out.println();		
		}
		
		System.out.println("Company");
		System.out.println("-------");
		for(Company company : companies)
			System.out.println(company.toString());
		
		System.out.println();
		System.out.println("Stocks");
		System.out.println("-----");
		for(Stock stock : stocks)
			System.out.println(stock.toString());
	}
	
	public static void addUsers() {
		
		model.addUser(new Admin("admin@asx.com.au", "password", "Stock Market"));
		model.addUser(new User("s3449513@student.rmit.edu.au", "password", "Paul King"));
		model.addUser(new User("Username", "Password", "admin"));
	}
	
	public static void createCompanies() {
		
		model.addCompany(new Company("A2M", "THE A2 MILK COMPANY LIMITED", 1000000));
		model.addCompany(new Company("AA", "AUSTRALIAN AGRICULTURAL COMPANY LIMITED.", 1000000));
		model.addCompany(new Company("ARB", "ARB CORPORATION LIMITED.", 1000000));
		model.addCompany(new Company("ASB", "AUSTAL LIMITED", 1000000));
		model.addCompany(new Company("ANZ", "AUSTRALIA AND NEW ZEALAND BANKING GROUP LIMITED", 1000000));
		model.addCompany(new Company("BAL", "BELLAMY'S AUSTRALIA LIMITED", 1000000));
//		model.addCompany(new Company("BAP", "BAPCOR LIMITED", 1000000));
//		model.addCompany(new Company("BBN", "BABY BUNTING GROUP LIMITED", 1000000));
//		model.addCompany(new Company("BEAR", "BETASHARES AUSTRALIAN EQUITIES BEAR HEDGE FUND", 1000000));
//		model.addCompany(new Company("CAB", "CABCHARGE AUSTRALIA LIMITED", 1000000));
//		model.addCompany(new Company("CAR", "CARSALES.COM LIMITED.", 1000000));
//		model.addCompany(new Company("CBA", "COMMONWEALTH BANK OF AUSTRALIA.", 1000000));
//		model.addCompany(new Company("CIM", "CIMIC GROUP LIMITED", 1000000));
//		model.addCompany(new Company("CWY", "CLEANAWAY WASTE MANAGEMENT LIMITED", 1000000));
//		model.addCompany(new Company("DHG", "DOMAIN HOLDINGS AUSTRALIA LIMITED.", 1000000));
//		model.addCompany(new Company("DMP", "DOMINO'S PIZZA ENTERPRISES LIMITED", 1000000));
//		model.addCompany(new Company("ECX", "ECLIPX GROUP LIMITED", 1000000));
//		model.addCompany(new Company("EHE", "ESTIA HEALTH LIMITED", 1000000));
//		model.addCompany(new Company("FBU", "FLETCHER BUILDING LIMITED", 1000000));
//		model.addCompany(new Company("FLT", "FLIGHT CENTRE TRAVEL GROUP LIMITED", 1000000));
//		model.addCompany(new Company("GEM", "G8 EDUCATION LIMITED", 1000000));
//		model.addCompany(new Company("GMA", "GENWORTH MORTGAGE INSURANCE AUSTRALIA LIMITED", 1000000));
//		model.addCompany(new Company("GUD", "G.U.D. HOLDINGS LIMITED", 1000000));
//		model.addCompany(new Company("HSN", "HANSEN TECHNOLOGIES LIMITED", 1000000));
//		model.addCompany(new Company("HSO", "HEALTHSCOPE LIMITED.", 1000000));
//		model.addCompany(new Company("IAG", "INSURANCE AUSTRALIA GROUP LIMITED", 1000000));
//		model.addCompany(new Company("IEM", "ISHARES MSCI EMERGING MARKETS ETF", 1000000));
//		model.addCompany(new Company("JBH", "JB HI-FI LIMITED", 1000000));
//		model.addCompany(new Company("JHC", "JAPARA HEALTHCARE LIMITED", 1000000));
//		model.addCompany(new Company("KAR", "KAROON GAS AUSTRALIA LIMITED", 1000000));
//		model.addCompany(new Company("KGN", "KOGAN.COM LTD", 1000000));
//		model.addCompany(new Company("LLC", "LENDLEASE GROUP", 1000000));
//		model.addCompany(new Company("LNK", "LINK ADMINISTRATION HOLDINGS LIMITED", 1000000));
//		model.addCompany(new Company("MFG", "MAGELLAN FINANCIAL GROUP LIMITED", 1000000));
//		model.addCompany(new Company("MGR", "MIRVAC GROUP", 1000000));
//		model.addCompany(new Company("NAB", "NATIONAL AUSTRALIA BANK LIMITED", 1000000));
//		model.addCompany(new Company("NAN", "NANOSONICS LIMITED", 1000000));
//		model.addCompany(new Company("NCM", "NEWCREST MINING LIMITED", 1000000));
//		model.addCompany(new Company("OFX", "OFX GROUP LIMITED", 1000000));
//		model.addCompany(new Company("OGC", "OCEANAGOLD CORPORATION", 1000000));
//		model.addCompany(new Company("PGH", "PACT GROUP HOLDINGS LTD", 1000000));
//		model.addCompany(new Company("PME", "PRO MEDICUS LIMITED", 1000000));
//		model.addCompany(new Company("PMV", "PREMIER INVESTMENTS LIMITED", 1000000));
//		model.addCompany(new Company("QAN", "QANTAS AIRWAYS LIMITED", 1000000));
//		model.addCompany(new Company("QBE", "QBE INSURANCE GROUP LIMITED", 1000000));
//		model.addCompany(new Company("REA", "REA GROUP LTD", 1000000));
//		model.addCompany(new Company("REG", "REGIS HEALTHCARE LIMITED", 1000000));
//		model.addCompany(new Company("S32", "SOUTH32 LIMITED", 1000000));
//		model.addCompany(new Company("SAR", "SARACEN MINERAL HOLDINGS LIMITED", 1000000));
//		model.addCompany(new Company("TAH", "TABCORP HOLDINGS LIMITED", 1000000));
//		model.addCompany(new Company("TCL", "TRANSURBAN GROUP", 1000000));
//		model.addCompany(new Company("TGR", "TASSAL GROUP LIMITED", 1000000));
//		model.addCompany(new Company("URW", "UNIBAIL-RODAMCO-WESTFIELD", 1000000));
//		model.addCompany(new Company("VCX", "CENTRES", 1000000));
//		model.addCompany(new Company("VOC", "VOCUS GROUP LIMITED", 1000000));
//		model.addCompany(new Company("WBC", "WESTPAC BANKING CORPORATION", 1000000));
//		model.addCompany(new Company("WEB", "WEBJET LIMITED", 1000000));
//		model.addCompany(new Company("XRO", "XERO LIMITED", 1000000));
				
	}
	
	public static void addStockToCompanies() {
		
		List<Company> companies = model.getCompanies();		
		List<Stock> stocks = model.getStocks();
		
		for(Company company : companies) {
			stocks.add(new Stock(company.getCode(), "admin@asx.com.au", company.getTotalShares()));
		}
		
	}
	
	public void addstockowned() {
		String admin = "Username";
		Stock stock = new Stock("XRO", "admin@asx.com.au", 10);	
		Stock stock2 = new Stock("WEB", "admin@asx.com.au", 10);	
		model.getTradingAccount(admin).BuyStock(stock);
		model.getTradingAccount(admin).BuyStock(stock2);	
	}
	
	public void showData() {
		List<User> users = model.getUsers();
		List<Company> companies = model.getCompanies();
		List<Stock> stocks = model.getStocks();
		
		System.out.println("Users");
		System.out.println("-----");
		for(User user : users) {
			System.out.print(user.toString());
			System.out.println("Balance: $" + user.getTradingAccount().getBalance());
			System.out.println();		
		}
		
		System.out.println("Company");
		System.out.println("-------");
		for(Company company : companies)
			System.out.println(company.toString());
		
		System.out.println();
		System.out.println("Stocks");
		System.out.println("-----");
		for(Stock stock : stocks)
			System.out.println(stock.toString());
	}
	
}
