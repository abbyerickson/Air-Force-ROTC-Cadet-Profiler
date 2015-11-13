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

/* View client's user profile */

public class ViewProfileActivity extends Activity{
	// Progress Dialog Object
	ProgressDialog prgDialog;
	// Error Msg TextView Object
	TextView errorMsg;
	// Name Edit View Object
	TextView nameET;
	// Rank Edit View Object
	TextView rankET;
	// Flight Edit View Object
	TextView flightET;
	// Email Edit View Object
	TextView yearET;
	// Password Edit View Object
	TextView majorET;
	// Phone Number Edit View Objecct
	TextView phoneET;
	//GPA Edit View Object
	TextView GPAET; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.profile);
		// Find Name Edit View control by ID
		nameET = (TextView)findViewById(R.id.etName);
		// Find Rank Edit View control by ID
		rankET = (TextView)findViewById(R.id.etRank);
		// Find Flight Edit View control by ID
		flightET = (TextView)findViewById(R.id.etFlight);
		// Find Year Edit View control by ID
		yearET = (TextView)findViewById(R.id.etYear);
		// Find Password Edit View control by ID
		majorET = (TextView)findViewById(R.id.etMajor);
		// Find Phone Number Edit View control by ID
		phoneET = (TextView)findViewById(R.id.etPhone);
		//Fine GPA Edit View Control by ID
		GPAET = (TextView)findViewById(R.id.etGPA);
		// Instantiate Progress Dialog object
		prgDialog = new ProgressDialog(this);
		// Set Progress Dialog Text
	    prgDialog.setMessage("Please wait...");
	    // Set Cancelable as False
	    prgDialog.setCancelable(false);
	    loadEditProfile();
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
	                org.codehaus.jettison.json.JSONObject obj = new JSONObject(response);
	                // When the JSON response has status boolean value assigned with true
	                if(obj.getBoolean("status")){
	                	// Set Updated Values for Edit View controls
	                	nameET.setText(obj.getString("name"), TextView.BufferType.NORMAL);
	                	rankET.setText(obj.getString("rank"), TextView.BufferType.NORMAL);
	                	flightET.setText(obj.getString("flight"), TextView.BufferType.NORMAL);
	                	yearET.setText(obj.getString("year"), TextView.BufferType.NORMAL);
	                	majorET.setText(obj.getString("major"), TextView.BufferType.NORMAL);
	                	phoneET.setText(obj.getString("phone"), TextView.BufferType.NORMAL);
	                	GPAET.setText(obj.getString("GPA"), TextView.BufferType.NORMAL);
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
	 * Method which navigates from View Profile Activity to Home Activity
	 */
	public void navigatebacktoHomeActivity(View view){
		Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(homeIntent);
	}
}
