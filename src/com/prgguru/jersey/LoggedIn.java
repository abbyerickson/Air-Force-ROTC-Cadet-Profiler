package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//Path: http://localhost/<appln-folder-name>/loggedin
@Path("/loggedin")
public class LoggedIn {
	// HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/loggedin/dologgedin
    @Path("/dologgedin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON)
    public String doLoggedIn(String uname) {
    	String response = "";
    	CurrentUser user = CurrentUser.loginlist.get(x);
    	if(user != null) {
            response = Utility.constructJSON(user.getPrivilege(), true);
    	}
    	else {
    		 response = Utility.constructJSON("logged in", false, "User not Logged In");
    	}
    	return response;
    }
    int x = 1;
    public boolean doLoggedOut(String uname) {
    	boolean isLoggedOut = false;
    	if(CurrentUser.loginlist.remove() != null) {
    		isLoggedOut = true;
    	}
    	return isLoggedOut;
    }
}
