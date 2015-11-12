package com.register.jersey;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//import org.codehaus.jettison.json;

public class Utility {
              /**
     * Null check Method
     *
     * @param txt
     * @return
     */
    public static boolean isNotNull(String txt) {
        // System.out.println("Inside isNotNull");
        return txt != null && txt.trim().length() >= 0 ? true : false;
    }
    /**
     * Method to construct JSON
     *
     * @param tag
     * @param status
     * @return
     */
    public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
    /**
     * Method to construct JSON with Error Msg
     *
     * @param tag
     * @param status
     * @param err_msg
     * @return
     */
    public static String constructJSON(String tag, boolean status,String err_msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
    
	public static String constructJSON(String name, String rank, String flight, String year, String major,
			String phone, String GPA, boolean status) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject(); 
		try { 
			obj.put("name", name);
			obj.put("rank", rank);
			obj.put("flight", flight);
			obj.put("year", year);
			obj.put("major", major);
			obj.put("phone", phone);
			obj.put("GPA", GPA);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) { 
			
		}
		return obj.toString();
	}
	public static String constructJSON(String name, String GPA, boolean status) {
	// TODO Auto-generated method stub
		JSONObject obj = new JSONObject(); 
		try { 
			obj.put("name", name);
			obj.put("GPA", GPA);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) { 
			
		}
		return obj.toString();
	}
}
