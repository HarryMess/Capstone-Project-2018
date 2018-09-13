package database;


import java.util.*;

import model.User;

import java.io.*;
import java.sql.*;
//import java.util.regex.*;

public class LoginManagerOld
{
    private Scanner input = new Scanner(System.in); // Probably replace with a utilty class
    private ArrayList<User> users = new ArrayList<User>();

    private String password;
    private String email;

    private static String dbURL = "jdbc:derby:Database;create=true;user='s3488361;password=password"; /* This might have to be changed, if you get a "No suitable driver found for ..." Error the classpath is wrong
    private static Connection connec = null; /* Instance */
    private static Statement statem = null;

    public void addUser()
    {
       
       System.out.println("Welcome user to this test instance");
       System.out.println("Please enter the email you would like to be added to the database");
       System.out.println("Restrictions will apply!");
       
       do
       {
       try
       {
           String email = input.nextLine();
       }
           
       catch(Exception exception)
       {
           exception.printStackTrace();
       }
       
           
       }
      
       while (email == null);

       System.out.println("Please enter the password you would like to be added to the database");
          
       do
       {
       try
       {
           String password = input.nextLine();
       }
              
       catch(Exception exception)
       {
           exception.printStackTrace();
       }
       
       }
          
       while(password == null || password.length() > 15);          
          
       /*
       boolean status = false;
       String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
       Pattern p = Pattern.compile(email_Pattern);
       Matcher m = p.m(email);
       if(m.matches())
       {
           status = true;
       }
       else
       {
           System.out.println("Invalid email");
           status = false;
           return status;
       }
       */

       /* Will require do try catch while to prevent users from entering nothing */
       
       // create a new user and hash the password
       User user = new User(email, password);
       user.hashPassword(password);
       
       try
       {
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); // Use org.apache.derby.jdbc.EmbeddedDriver
           connec = DriverManager.getConnection(dbURL);

           /* Old method of mapping driver to URL, refer to
            * https://docs.oracle.com/javase/6/docs/api/java/sql/DriverManager.html
            * Swap to the method outlined in the url when possible
           */
       }

       catch (Exception exception)
       {
           exception.printStackTrace(); /* Calls the toString method of whatever exception was thrown */
       }

       try
       {
    	   users.add(user);       	   
           statem = connec.createStatement();
           statem.execute("insert into Users values (" +
                   ",'" + user.getEmail() + "','" + user.getPassword() + "','" + "')");
           statem.close();
       }

       catch (SQLException sqlExcept)
       {
           sqlExcept.printStackTrace();
       }

        //return status;

    }

    public void autheticate()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); // use org.apache.derby.jdbc.EmbeddedDriver
            connec = DriverManager.getConnection(dbURL);
        }

        catch (Exception exception)
        {
            exception.printStackTrace(); /* Calls the toString method of whatever exception was thrown */
        }

        System.out.println("Please enter your username");
        String email = input.nextLine();

        System.out.println("Please enter your password");
        String password = input.nextLine();

        try
        {
            statem = connec.createStatement();
            ResultSet res = statem.executeQuery("select * from users where email='"+email+"' AND password = '"+password+"'");
            int counter = 0;
            while (res.next())
            {
                counter += 1;
            }

            if (counter == 1)
            {
                System.out.println("Access granted");
            }

            else
            {
                System.out.println("Username and/or password do not match up with our records");
                System.out.println("Access denied");

            }
        }

        catch(Exception exception)
        {
            exception.printStackTrace();
        }


    }

}
