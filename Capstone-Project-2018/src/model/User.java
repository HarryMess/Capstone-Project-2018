
/* Consider this as an parent class for the class "Admin" which the "Admin" class will inherit */ 
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class User
{
	private int id;
	private final String email;
	private String password;
	
	public User(String email, String password)
	{
		this.email = email;;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public String getEmail()
	{
		return email;
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
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(password.getBytes());
			
			// create a string format of hash
			byte[] digest = md.digest();			
			hashedPassword = DatatypeConverter.printHexBinary(digest);
			
			// update the password
			this.password = hashedPassword;
			
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