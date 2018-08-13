/* Consider this as an parent class for the class "Admin" which the "Admin" class will inherit */ 

public class User
{
	private String userName;
	private String email;
	private String password; 
	
	public User(String userName, String email, String password)
	{
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public String getUserName()
	{
		return username;
	}
	public void setUserName(String username)
	{
		this.userName = userName;
	}
	
	public String getEmail()
	{
		return email;
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
	
	public String getString()
	{
		return "Username: " + userName + "\n" + "Email: " + email + "\n" + "Password: " + password + "\n";
	}
}
