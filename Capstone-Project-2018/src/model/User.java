/* Consider this as an parent class for the class "Admin" which the "Admin" class will inherit */ 
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import javax.xml.bind.DatatypeConverter;

public class User
{
	private String email;
	private String password;
	private final TradingAccount account;
	
	public User(String email, String password, TradingAccount account)
	{
		this.email = email;;
		this.password = password;
		this.account = account;
	}
	
	public User(String email, String password, String name)
	{
		this.email = email;
		this.password = password;
		hashPassword(password);		
		
		account = new TradingAccount(name);
	}
	
	
	public String getEmail()
	{
		return email;
	}
	
	public TradingAccount getTradingAccount() {
		return account;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password; 
	}
	
	public void hashPassword(String password) {
		
//		String hashedPassword;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			
			// create a string format of hash
//			byte[] digest = md.digest();			
//			hashedPassword = DatatypeConverter.printHexBinary(digest);
			
//			System.out.println("Password: " + password);
//			System.out.println("Hashed password: " + hashedPassword);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean passwordMatches(String passwordEntered) {
		
		hashPassword(passwordEntered);
		
		if(password.equals(passwordEntered))
			return true;
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Email: " + email + "\n" + "Password: " + password + "\n";
	}

}