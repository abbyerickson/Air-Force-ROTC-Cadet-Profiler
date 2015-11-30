package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/updateGPA")
public class UpdateGPA {
	// HTTP Get Method
    @GET
 // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doupdateGPA")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
	 /* Method to check whether the entered credential is valid
	     * 
	     * @param uname
	     * @param pwd
	     * @return
	     */
	    private String findGPA(){
	        System.out.println("Inside updateGPA");
	        boolean result = false;
	        String response = "";
	        String [] GPAInfo = new String[2]; 
            try {
            	GPAInfo = DBConnection.retrieveGPAInfo();
            } catch (SQLException e) {
    			e.printStackTrace();
    		} catch (Exception e) {
    			e.printStackTrace();
    		} 
            response = Utility.constructJSON(GPAInfo[0], GPAInfo[1], true);
            return response;
        }
}
