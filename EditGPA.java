package com.prgguru.jersey;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.register.jersey.Utility;

//Path: http://localhost/<appln-folder-name>/editGPA
@Path("/editGPA")
public class EditGPA {
	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/editprofile/doedit
    @Path("/doeditGPA")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&year=abc&major=xyz
    public String doEditGPA(@QueryParam("name") String name, @QueryParam("GPA")String GPA){
        String response = "";
        int retCode = updateInfo(name, GPA);
        if(retCode == 0){
            response = Utility.constructJSON(name, GPA, true);
        }else if(retCode == 1){
            response = Utility.constructJSON("edit", false, "Error Occured");
        }
        return response;
 
    }
    private int updateInfo(String name, String GPA){
        System.out.println("Inside editGPA");
        int result = 1;
            try {
                if(DBConnection.insertGPA(name, GPA)){
                    System.out.println("EditGPA if");
                    result = 0;
                }
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside checkCredentials catch e ");
                result = 1;
            }
        return result;
    }
}
