package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//Path: http://localhost/<appln-folder-name>/editprofile
@Path("/editprofile")
public class EditProfile {
	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/editprofile/doedit
    @Path("/doedit")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&year=abc&major=xyz
    public String doEdit(@QueryParam("name") String name, @QueryParam("rank") String rank, @QueryParam("flight") String flight, @QueryParam("year") String year, @QueryParam("major") String major
    			, @QueryParam("phone") String phone){
        String response = "";
        int retCode = updateInfo(name, rank, flight, year, major, phone);
        if(retCode == 0){
            response = Utility.constructJSON(name, rank, flight, year, major, phone, true);
        }else if(retCode == 1){
            response = Utility.constructJSON("edit", false, "Error Occured");
        }
        return response;
 
    }
    private int updateInfo(String name, String rank, String flight, String year, String major, String phone){
        System.out.println("Inside editProfile");
        int result = 1;
            try {
                if(DBConnection.insertProfInfo(name, rank, flight, year, major, phone)){
                    System.out.println("EditProfile if");
                    result = 0;
                }
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside checkCredentials catch e ");
                result = 1;
            }
        /*else{
            System.out.println("Inside checkCredentials else");
            result = 1;
        }*/
        return result;
    }
}
