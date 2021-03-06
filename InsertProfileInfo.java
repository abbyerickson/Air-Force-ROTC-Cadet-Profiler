public static boolean insertProfileIfo (String first_name, String last_name, 
    		String major, String year, String flight, String email, String phone_number) throws SQLException, Exception {
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
            String query = "INSERT into user_profile(FirstName, LastName, Flight, Year, Major, PhoneNumber, Email) values('"
			+first_name+ "',"+"'"+ last_name + "','"+ flight + "','" + year + "','" + major + "','" + phone_number + "','" + email + "')";
            //System.out.println(query);
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
