package com.prgguru.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
	/**
     * Method to create DB Connection
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } catch (Exception e) {
            throw e;
        } finally {
            return con;
        }
    }
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public static boolean checkLogin(String uname, String pwd) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }
    /**
     * Method to find user's account privilege
     * @return String
     * @throws SQLException
     * @throws Exception
     */
    public static String retrievePrivilege(String email) throws SQLException, Exception {
    	String privilege = new String();
    	Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM privilege WHERE email = '" +email+ "'";
	        ResultSet rs = stmt.executeQuery(query);
	        if (rs.next() == true) {
	            privilege = rs.getString("privilege");
	        }
        } catch (SQLException sqle) {
	        throw sqle;
	    } catch (Exception e) {
	        //e.printStackTrace();
	        // TODO Auto-generated catch block
	        if (dbConn != null) {
	            dbConn.close();
	        }
	        throw e;
	    } finally {
	        if (dbConn != null) {
	            dbConn.close();
	        }
	    }
        return privilege;
    }
    
    /**
     * Method to insert uname and pwd in DB
     * 
     * @param name
     * @param uname
     * @param pwd
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
    	boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
            	e.printStackTrace();
            }
            String privilege = retrievePrivilege(uname);
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into user(name, username, password, privilege, rank, flight, year, major, phone) values('"+name+ "',"+"'"
                    + uname + "','" + pwd + "','" + privilege + "','','','','','')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            System.out.print(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }

	/**
	 * Method to insert Profile Info into DB
	 * 
	 * @param name
	 * @param year
	 * @param major
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertProfInfo(String name, String rank, String flight, String year, String major, String phone) throws SQLException, Exception {
	    boolean insertStatus = false;
	    Connection dbConn = null;
	    try {
	        try {
	            dbConn = DBConnection.createConnection();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        Statement stmt = dbConn.createStatement();
	        String query = "UPDATE user SET name = '" +name+ "', rank = '" +rank+ "', flight = '" +flight+ "', year = '" +year+ "', major = '"
	        		+major+ "', phone = '" +phone+ "' WHERE username = '" +Constants.currUser+ "'";
	        System.out.println(query);
	        int records = stmt.executeUpdate(query);
	        //System.out.println(records);
	        //When record is successfully inserted
	        if (records > 0) {
	            insertStatus = true;
	        }
	    } catch (SQLException sqle) {
	        //sqle.printStackTrace();
	        throw sqle;
	    } catch (Exception e) {
	        //e.printStackTrace();
	        // TODO Auto-generated catch block
	        if (dbConn != null) {
	            dbConn.close();
	        }
	        throw e;
	    } finally {
	        if (dbConn != null) {
	            dbConn.close();
	        }
	    }
	    return insertStatus;
	}
	
	/**
	 * Method to retrieve Profile Info from DB
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static String[] retrieveProfInfo() throws SQLException, Exception {
	    Connection dbConn = null;
	    String [] profInfo = new String[6]; 
	    try {
	        try {
	            dbConn = DBConnection.createConnection();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        Statement stmt = dbConn.createStatement();
	        String query = "SELECT * FROM user WHERE username = '" +Constants.currUser+ "'";
	        //System.out.println(query);
	        ResultSet rs = stmt.executeQuery(query);
	        //When row is successfully extracted
	        int i = 0;
	        if (rs.next() == true) {
	            String s = rs.getString("name");
	            profInfo[i++] = s;
	            s = rs.getString("rank");
	            profInfo[i++] = s;
	            s = rs.getString("flight");
	            profInfo[i++] = s;
	            s = rs.getString("year");
	            profInfo[i++] = s;
	            s = rs.getString("major");
	            profInfo[i++] = s;
	            s = rs.getString("phone");
	            profInfo[i++] = s;
	        }
	    } catch (SQLException sqle) {
	        throw sqle;
	    } catch (Exception e) {
	        //e.printStackTrace();
	        // TODO Auto-generated catch block
	        if (dbConn != null) {
	            dbConn.close();
	        }
	        throw e;
	    } finally {
	        if (dbConn != null) {
	            dbConn.close();
	        }
	    }
	    return profInfo;
	}
}