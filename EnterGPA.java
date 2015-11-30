package com.prgguru.jersey;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


//Path: http://localhost/<appln-folder-name>/enterGPA
@Path("/enterGPA")
public class EnterGPA {
	// HTTP Get Method
    @GET
 // Path: http://localhost/<appln-folder-name>/enterGPA/doenterGPA
    @Path("/doenterGPA")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    private int enterGPA(String name, String GPA){
        System.out.println("Inside EnterGPA");
        int result = 3;
        if(Utility.isNotNull(name) && Utility.isNotNull(GPA)){
            try {
                if(DBConnection.insertGPAInfo(name, GPA)){
                    result = 0;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Inside enterGPA catch"+e);
                result = 1;
            }
        }
        return result;
    }
 // Query parameters are parameters: http://localhost/<appln-folder-name>/updateGPA/enterGPA
    public String doGPA(@QueryParam("name") String name, @QueryParam("GPA") String GPA){
        String response = "";
        //System.out.println("Inside doLogin "+uname+"  "+pwd);
        int retCode = enterGPA(name, GPA);
        if(retCode == 0){
            response = Utility.constructJSON("name", "GPA", true);
        }else if(retCode == 1){
            response = Utility.constructJSON("name","GPA", false, "Error");
        }
        return response;
    }
    
}
