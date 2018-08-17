/* Consider this as an parent class for the class "Admin" which the "Admin" class will inherit */ 
import java.util.*;

public class User
{
	private String userName;
	private String email;
	private String password; 
	/* private List<StockAccount> list = new ArrayList<StockAccount>(); StockAccount class needs to be created first */ 
	
	public User(String userName, String email, String password)
	{
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public String getUserName()
	{
		return userName;
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