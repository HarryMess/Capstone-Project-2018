import java.util.*;
import java.io.*;

public class loginManager()
{
  
  Scanner input = new Scanner(System.in);
  
  private DBDerby db = new DBDerby; /* Class instances */
  
  Arraylist<User> users; /* Lists */
  
  private String userName; /* Generic variables */ 
  private String password;
  private String email;
  private boolean valid; 
  
  public loginManager()
  {
    users = new Arraylist<User>();
  }
  
  public addUser()
  {
    /* Will probably need a try catch */
    
    System.out.println("Welcome, username & password must be between 1 and 12 characters");
    System.out.println("Please enter your account name");
    String userName = input.nextLine(); 
    
    while (userName == null) /* Checks if user entered inputed a username, numbers can still be entered */
    {
      System.out.println("Please input your username!");
    }
    
    System.out.println("Field cannot be left empty, please input your password!"); 
    String password = input.nextLine();
    
    while (password == null) /* Same deal */
    {
      System.out.println("Field cannot be left empty, please input your password!");
    }
    
    System.out.println("Please enter your email!"); 
    String email = input.nextLine();
    
    while (email == null) /* Same deal */
    {
      System.out.println("Field cannot be left empty, please input your email!");
    }
     
    if (userName.length() !null & userName.length() <= 12) /* Checks size of String, this will obviously cause an exception if an number is present */
    {
      System.out.println("Sucessful entry!"); /* Also careful of 'magical number' */
      User u = new User(userName, password, email); /* Declares an instance of the Array of the class User and adds user input into the Array */ 
      users.add(u); /* Not sure how to do this part, should I use the main method to do this kind of stuff using the DBDERBY.java class ? */
    
    
      db.createConnection();
      db.insertUsers(userName, password, email); /* Might be an error through the ID, I removed the ID variable which could cause insertUsers to reject due to amount of arguments */ 
      db.shutdown();
    }
  }
  
  public authenticateUser()
  {
    db.createConnection();
    
    try 
    {
      
    }
    
    catch
    {
      
    }
       
  }
}
