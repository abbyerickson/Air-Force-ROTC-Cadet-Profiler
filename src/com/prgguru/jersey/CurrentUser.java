package com.prgguru.jersey;
import java.util.LinkedList;
import java.util.Random;

public class CurrentUser {
	String username;
	int id;
	String privilege;
	public CurrentUser(String username, int id, String privilege) {
		this.username = username;
		this.id = id;
		this.privilege = privilege;
	}
    public static LinkedList<CurrentUser> loginlist = new LinkedList<CurrentUser>();
    public static Random random = new Random();
    
    public String getUsername() {
    	return username;
    }
    
    public int getId() {
    	return id;
    }
    
    public String getPrivilege() {
    	return privilege;
    }
}	
