package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/loadprofile")
public class LoadProfile {
	// HTTP Get Method
    @GET
    // Path: http://localhost/useraccount/loadprofile/doloadprof
    @Path("/doloadprof")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    // Query parameters are parameters: http://localhost/useraccount/loadprofile/doloadprof
    public String doLoadProf(){
        String response = "";
        String [] profInfo = new String[6];
        try {
			profInfo = DBConnection.retrieveProfInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        response = Utility.constructJSON(profInfo[0], profInfo[1], profInfo[2], profInfo[3],
        		profInfo[4], profInfo[5], true);
        return response;
    }
}
