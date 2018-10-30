package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Description This class contains all the variables relevant to a user object.
 * Matches the Users Table
 * @author Paul King -s3449513
 * @version 1.0
 * @since 30/10/2018
 */
public class User
{
	private int id;
	private final String email;
	private String password;
	private boolean isAdmin;
	
	/**
	 * This is the constructor
	 * @param email The email address of the current user. This is also the username.
	 * @param password The user password. This will be in SHA1 format.
	 */
	public User(String email, String password)
	{
		this.email = email;
		this.password = password;
	}
	
	/**
	 * 
	 * @return returns the user id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return retriences the user email address. Also the username.
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * 
	 * @return retrieves the password of the user
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Used to update the password
	 * @param password takes the new password as an argument
	 * @deprecated due to database implementation
	 */
	public void setPassword(String password)
	{
		this.password = password; 
	}
	
	/**
	 * Hashes the password during registration using SHA1 algorithm
	 * @param password takes the new password as an argument
	 * @deprecated due to equivalent method in UserTable class
	 */
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
	
	/**
	 * Checks if the password entered by the user matches the current one.
	 * @param passwordEntered this is the password entered by the user during login
	 * @return returns true if the password matches, false if it does not.
	 */
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