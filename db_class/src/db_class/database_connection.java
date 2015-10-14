package db_class;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class database_connection {
	 /*
    * Create a connection to the Database
    */
   @SuppressWarnings("finally")
   public static Connection createConnection() throws Exception {
       Connection connection = null;
       try {
           Class.forName(DBInfo.path);
           connection = DriverManager.getConnection(DBInfo.url, DBInfo.username, DBInfo.password);
       } catch (Exception e) {
           throw e;
       } finally {
           return connection;
       }
   }
   /*
    * Verify if username and passsword are valid
    */
   public static boolean checkCredentials(String username, String password) throws Exception {
       Connection connection = null;
       boolean isValid = false;
       try {
           try {
               connection = database_connection.createConnection();
           } catch (Exception e) {
               e.printStackTrace();
           }
           Statement statement = connection.createStatement();
           String query = "SELECT * FROM user WHERE username = '" + username
                   + "' AND password=" + "'" + password + "'";
           ResultSet result = statement.executeQuery(query);
           while (result.next()) {
               isValid = true;
           }
       } catch (SQLException sqlexcp) {
           throw sqlexcp;
       } catch (Exception e) {
           if (connection != null) {
               connection.close();
           }
           throw e;
       } finally {
           if (connection != null) {
               connection.close();
           }
       }
       return isValid;
   }
   /*
    * Insert username and password when registering
    */
   public static boolean insertUser(String name, String username, String password) throws SQLException, Exception {
       Connection connection = null;
   	boolean isInserted = false;
       try {
           try {
               connection = database_connection.createConnection();
           } catch (Exception e) {
               e.printStackTrace();
           }
           Statement statement = connection.createStatement();
           String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
                   + username + "','" + password + "')";
           int update = statement.executeUpdate(query);
           if (update > 0) {
               isInserted = true;
           }
       } catch (SQLException sqlexcp) {
           throw sqlexcp;
       } catch (Exception e) {
           if (connection != null) {
               connection.close();
           }
           throw e;
       } finally {
           if (connection != null) {
               connection.close();
           }
       }
       return isInserted;
   }
}
