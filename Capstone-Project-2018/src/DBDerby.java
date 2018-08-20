import java.sql.*;

public class DBDerby
{
    private static String dbURL = "jdbc:derby://localhost:8080/DBDerby;create=true;user=myname;password=guess";
    private static Connection connec = null; /* Instance */ 
    private static Statement statem = null;
    
    public void createConnection() /* Creating the connection */
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connec = DriverManager.getConnection(dbURL); 
            
            /* Old method of mapping driver to URL, refer to
             * https://docs.oracle.com/javase/6/docs/api/java/sql/DriverManager.html
             * Swap to the method outlined in the url when possible
             */
        }
        
        catch (Exception except)
        {
            except.printStackTrace(); /* Calls the toString method of whatever exception was thrown */ 
        }
    }
    
    public void insertUsers(int id, String userName, String password, String email) /* Creation of tables */
    {
        try
        {
            statem = connec.createStatement();
            statem.execute("insert into " + tableName + " values (" +
                    id + ",'" + userName + "','" + password + "','" + email + "','" + "')");
            statem.close();
        }
        
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public void selectUsers() /* Select * from table */
    {
        try
        {
            statem = connec.createStatement();
            ResultSet res = statem.executeQuery("select * from " + tableName);
            ResultSetMetaData resultSet = res.getMetaData();
            int numCol = resultSet.getColumnCount();
            for (int i=1; i <= numCol; i++)
            {
                System.out.print(resultSet.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n++++++++++++++++++++++++++++++++++++++");

            while(res.next())
            {
                int id = res.getInt(1);
                String userName = res.getString(2);
                String password = res.getString(3);
                String email = res.getString(4); 
                System.out.println(id + "\t\t" + userName + "\t\t" + password  + "\t\t" + email);
            }
            
            res.close();
            statem.close();
        }
        
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public void shutdown() /* Close connection */
    {
        try
        {
            if (statem != null)
            {
                statem.close();
            }
            
            if (connec != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                connec.close();
            }           
        }
        
        catch (SQLException sqlExcept)
        {
            System.out.println("Warning!");
        }

    }
}