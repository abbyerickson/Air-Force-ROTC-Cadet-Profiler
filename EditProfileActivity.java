package com.prgguru.example;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/* Save profile changes made by client */

public class EditProfileActivity extends Activity{
	// Progress Dialog Object
	ProgressDialog prgDialog;
	// Error Msg TextView Object
	TextView errorMsg;
	// Name Edit View Object
	EditText nameET;
	// Rank Edit View Object
	EditText rankET;
	// Flight Edit View Object
	EditText flightET;
	// Email Edit View Object
	EditText yearET;
	// Password Edit View Object
	EditText majorET;
	// Phone Number Edit View Objecct
	EditText phoneET;
	//GPA Edit View Object
	EditText GPAET; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.edit);
		// Find Name Edit View control by ID
		nameET = (EditText)findViewById(R.id.etName);
		// Find Rank Edit View control by ID
		rankET = (EditText)findViewById(R.id.etRank);
		// Find Flight Edit View control by ID
		flightET = (EditText)findViewById(R.id.etFlight);
		// Find Year Edit View control by ID
		yearET = (EditText)findViewById(R.id.etYear);
		// Find Password Edit View control by ID
		majorET = (EditText)findViewById(R.id.etMajor);
		// Find Phone Number Edit View control by ID
		phoneET = (EditText)findViewById(R.id.etPhone);
		// Find Phone Number Edit View control by ID
		GPAET = (EditText)findViewById(R.id.etGPA);
		// Instantiate Progress Dialog object
		prgDialog = new ProgressDialog(this);
		// Set Progress Dialog Text
	    prgDialog.setMessage("Please wait...");
	    // Set Cancelable as False
	    prgDialog.setCancelable(false);
	    loadEditProfile();
	}
	/**
	 * Method gets triggered when an save changes button is clicked
	 * 
	 * @param view
	*/
	public void editProfile(View view){
		// Get Name ET control value
		String name = nameET.getText().toString();
		// Get Rank ET control value
		String rank = rankET.getText().toString();
		// Get Flight ET control value
		String flight = flightET.getText().toString();
		// Get Year ET control value
		String year = yearET.getText().toString();
		// Get Major ET control value
		String major = majorET.getText().toString();
		// Get Phone ET control value
		String phone = phoneET.getText().toString();
		// Get GPA ET control value
		String GPA = GPAET.getText().toString();
		// Instantiate Http Request Param Object
		RequestParams params = new RequestParams();
		// Put Http parameter name with value of Name Edit View control
		params.put("name", name);
		// Put Http parameter rank with value of Rank Edit View control
		params.put("rank", rank);
		// Put Http parameter flight with value of Flight Edit View control
		params.put("flight", flight);
		// Put Http parameter username with value of Email Edit View control
		params.put("year", year);
		// Put Http parameter password with value of Password Edit View control
		params.put("major", major);
		// Put Http parameter phone with value of Phone Edit View control
		params.put("phone", phone);
		// Put Http parameter phone with value of GPA Edit View control
		params.put("GPA", GPA);
		// Invoke RESTful Web Service with Http parameters
		invokeWS(params);	
	}
	
	/**
	* Method that performs RESTful webservice invocations
	* loads profile values into edit boxes
    */
	public void loadEditProfile(){
		// Show Progress Dialog 
		prgDialog.show();
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
	    client.get("http://10.184.138.125:8080/useraccount/loadprofile/doloadprof",new AsyncHttpResponseHandler() {
	    // When the response returned by REST has Http response code '200'
	    @Override
	    	public void onSuccess(String response) {
	        // Hide Progress Dialog
	        	System.out.println("After success");
	    		prgDialog.hide();
	            try {
	            	// JSON Object
	                JSONObject obj = new JSONObject(response);
	                // When the JSON response has status boolean value assigned with true
	                if(obj.getBoolean("status")){
	                	// Set Updated Values for Edit View controls
	                	nameET.setText(obj.getString("name"), TextView.BufferType.EDITABLE);
	                	rankET.setText(obj.getString("rank"), TextView.BufferType.EDITABLE);
	                	flightET.setText(obj.getString("flight"), TextView.BufferType.EDITABLE);
	                	yearET.setText(obj.getString("year"), TextView.BufferType.EDITABLE);
	                	majorET.setText(obj.getString("major"), TextView.BufferType.EDITABLE);
	                	phoneET.setText(obj.getString("phone"), TextView.BufferType.EDITABLE);
	                	GPAET.setText(obj.getString("GPA"), TextView.BufferType.EDITABLE);
	                } 
	                // Else display error message
	                else{
	                	errorMsg.setText(obj.getString("error_msg"));
	                    Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
	                }
	            } catch (JSONException e) {
	            	// TODO Auto-generated catch block
	            	Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
	            	e.printStackTrace();     
	              }
	        }
	        // When the response returned by REST has Http response code other than '200'
	        @Override
	        public void onFailure(int statusCode, Throwable error,
	            String content) {
	            // Hide Progress Dialog
	            prgDialog.hide();
	            // When Http response code is '404'
	            if(statusCode == 404){
	            	Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
	            } 
	            // When Http response code is '500'
	            else if(statusCode == 500){
	            	Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
	            } 
	            // When Http response code other than 404, 500
	            else{
	            	Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
	            }
	        }
	    });
	}

	/**
	* Method that performs RESTful webservice invocations
	* 
	* @param params
    */
	public void invokeWS(RequestParams params){
		// Show Progress Dialog 
		prgDialog.show();
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
	    client.get("http://10.184.138.125:8080/useraccount/editprofile/doedit",params ,new AsyncHttpResponseHandler() {
	    // When the response returned by REST has Http response code '200'
	    @Override
	    	public void onSuccess(String response) {
	        // Hide Progress Dialog
	        	prgDialog.hide();
	            try {
	            	// JSON Object
	                JSONObject obj = new JSONObject(response);
	                // When the JSON response has status boolean value assigned with true
	                if(obj.getBoolean("status")){
	                	// Set Updated Values for Edit View controls
	                	nameET.setText(obj.getString("name"), TextView.BufferType.EDITABLE);
	                	rankET.setText(obj.getString("rank"), TextView.BufferType.EDITABLE);
	                	flightET.setText(obj.getString("flight"), TextView.BufferType.EDITABLE);
	                	yearET.setText(obj.getString("year"), TextView.BufferType.EDITABLE);
	                	majorET.setText(obj.getString("major"), TextView.BufferType.EDITABLE);
	                	phoneET.setText(obj.getString("phone"), TextView.BufferType.EDITABLE);
	                	GPAET.setText(obj.getString("GPA"), TextView.BufferType.EDITABLE);
	                    //Use doprofile method
	                    // Display successfully registered message using Toast
	                    Toast.makeText(getApplicationContext(), "Your profile is sucessefully upadaed!", Toast.LENGTH_LONG).show();
	                } 
	                // Else display error message
	                else{
	                	errorMsg.setText(obj.getString("error_msg"));
	                    Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
	                }
	            } catch (JSONException e) {
	            	// TODO Auto-generated catch block
	            	Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
	            	e.printStackTrace();     
	              }
	        }
	        // When the response returned by REST has Http response code other than '200'
	        @Override
	        public void onFailure(int statusCode, Throwable error,
	            String content) {
	            // Hide Progress Dialog
	            prgDialog.hide();
	            // When Http response code is '404'
	            if(statusCode == 404){
	            	Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
	            } 
	            // When Http response code is '500'
	            else if(statusCode == 500){
	            	Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
	            } 
	            // When Http response code other than 404, 500
	            else{
	            	Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
	            }
	        }
	    });
	}
	
	/**
	 * Method which navigates from Edit Profile Activity to Home Activity
	 */
	public void navigatebacktoHomeActivity(View view){
		Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(homeIntent);
	}
}
