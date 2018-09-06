import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class User
{
  
 private final TradingAccount account; /* Class instance */
 
 private String userName; /* Generic variables */ 
 private String email;
 private String password;
 
 
 public User(String userName, String email, String password)
 {
  this.userName = userName;
  this.email = email;
  this.password = password;
  account = new TradingAccount();
 }
 
 public String getUserName()
 {
  return userName;
 }
 public void setUserName(String userName)
 {
  this.userName = userName;
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
 
 @Override
 public String toString()
 {
  return "Username: " + userName + "\n" + "Email: " + email + "\n" + "Password: " + password + "\n";
 }
}