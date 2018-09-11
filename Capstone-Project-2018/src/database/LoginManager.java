package database;

import java.util.*;
import java.io.*;
import java.sql.*;
//import java.util.regex.*;

public class LoginManager
{
    private Scanner input = new Scanner(System.in); // Probably replace with a utilty class
    //private ArrayList<User> userArray1 = new ArrayList<User>();

    private String password;
    private String email;

    private static String dbURL = "jdbc:derby:Database;create=true;user='s3488361;password=password"; /* This needs to be changed */
    private static Connection connec = null; /* Instance */
    private static Statement statem = null;

    public void addUser()
    {
       System.out.println("Welcome user to this test instance");
       System.out.println("Please enter the email you would like to be added to the database");
       System.out.println("Restrictions will apply!");
       String email = input.nextLine();

       System.out.println("Please enter the password you would like to be added to the database");
       String password = input.nextLine();

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

       try
       {
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
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
           statem = connec.createStatement();
           /*statem.execute("insert into " + Users + " values (" +
                   ",'" + email + "','" + password + "','" + "')");*/
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
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
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
