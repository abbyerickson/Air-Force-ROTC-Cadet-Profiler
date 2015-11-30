/**
	 * Method to retrieve Profile Info from DB
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static String[] retrieveGPAInfo() throws SQLException, Exception {
	    Connection dbConn = null;
	    String [] GPAInfo = new String[2]; 
	    try {
	        try {
	            dbConn = DBConnection.createConnection();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        Statement stmt = dbConn.createStatement();
	        String query = "SELECT * FROM GPA WHERE username = '" +Constants.currUser+ "'";
	        //System.out.println(query);
	        ResultSet rs = stmt.executeQuery(query);
	        //When row is successfully extracted
	        int i = 0;
	        if (rs.next() == true) {
	            String s = rs.getString("name");
	            GPAInfo[i++] = s;
	            s = rs.getString("GPA");
	            GPAInfo[i++] = s;
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
	    return GPAInfo;
	}
	/**
	 * Method to insert GPA into DB
	 * 
	 * @param name
	 * @param year
	 * @param major
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertGPAInfo(String name, String GPA) throws SQLException, Exception {
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
	        String query = "UPDATE user SET name = '" +name+ "', rank = '" +GPA+ 
	        		"' WHERE username = '" +Constants.currUser+ "'";
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
