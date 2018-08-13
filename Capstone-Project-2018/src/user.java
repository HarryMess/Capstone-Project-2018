/* Consider this as an parent class for the class "Admin" which the "Admin" class will inherit */ 

public class User
{
	private string userName;
	private string email;
	private string password; 
	
	public User(string userName, string email, string password)
	{
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	public string getUserName()
	{
		return username;
	}
	public void setUserName(string username)
	{
		this.userName = userName;
	}
	
	public string getEmail()
	{
		return email;
	}
	public void setEmail(string email)
	{
		this.email = email;
	}
	
	public string getPassword()
	{
		return password;
	}
	public void setPassword(string password)
	{
		this.password = password; 
	}
	
	public string getString()
	{
		return "Username: " + userName + "\n" + "Email: " + email + "\n" + "Password: " + password + "\n";
	}
}
