/* Consider this as an parent class for the class "Admin" which the "Admin" class will inherit */ 
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class User
{
	private String email;
	private String password;
	private final TradingAccount account;
	private boolean isAdmid;
	
	public User(String email, String password)
	{
		this.email = email;
		this.password = password;
		account = new TradingAccount();
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
		
		String hashedPassword;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			
			// create a string format of hash
			byte[] digest = md.digest();			
			hashedPassword = DatatypeConverter.printHexBinary(digest);
			
			System.out.println("Password: " + password);
			System.out.println("Hashed password: " + hashedPassword);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//register
	public void register()
	{
		
	}
	
	// function log in
	public void logIn() 
	{
		
	}
    
	// function log out 
	public void logOut()
	{
		
	}
	
	// view friend list 
	public void viewFriendlist()
	{
	   System.out.println("1. Add friend \n"
			   	    + "2. Accept friend request \n"
			   		+ "3. Send money to friend \n"
			   		+ "4. Send massage \n"
			   		+ "5. View info \n");
	   int i = 0;
	   
	}
	
	// add friend
	public void addFriend()
	{
		
	}
	
	// accept friend request
	public void acceptFriendrequest()
	{
		
	}
	
	// send money to friend
	public void sendMoney()
	{
		
	}
	
	// send massage 
	public void sendmassage()
	{
		
	}
	
	//view info
	public void viewinfo() 
	{
		
	}
	
	
	@Override
	public String toString()
	{
		return "Email: " + email + "\n" + "Password: " + password + "\n";
	}
}