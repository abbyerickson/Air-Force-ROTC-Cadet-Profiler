package edu.catysonpurdue.rotccadetprofiler;

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

/* Edit Profile frontend class */

public class EditProfileActivity extends Activity{
    // Progress Dialog Object
    ProgressDialog prgDialog;
    // Error Msg TextView Object
    TextView errorMsg;
    // Name Edit View Object
    EditText nameET;
    // Email Edit View Object
    EditText yearET;
    // Passwprd Edit View Object
    EditText majorET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //make .xml		setContentView(R.layout.editprofile);
        // Find Error Msg Text View control by ID
        errorMsg = (TextView)findViewById(R.id.editprofile_error);
        // Find Name Edit View control by ID
        nameET = (EditText)findViewById(R.id.editprofileName);
        nameET.setText("Google is your friend.", TextView.BufferType.EDITABLE);
        // Find Email Edit View control by ID
        yearET = (EditText)findViewById(R.id.editprofileYear);
        // Find Password Edit View control by ID
        majorET = (EditText)findViewById(R.id.editprofileMajor);
        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }
    /**
     * Method gets triggered when an edit button is clicked
     *
     * @param view
     */
    public void editProfile(View view){
        // Get Name ET control value
        String name = nameET.getText().toString();
        // Get Year ET control value
        String year = year.getText().toString();
        // Get Major ET control value
        String major = major.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // Put Http parameter name with value of Name Edit View control
        params.put("name", name);
        // Put Http parameter username with value of Email Edit View control
        params.put("year", year);
        // Put Http parameter password with value of Password Edit View control
        params.put("major", major);
        // Invoke RESTful Web Service with Http parameters
        invokeWS(params);
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
        client.get("http://10.184.136.163:8080/useraccount/editprofile/doedit",params ,new AsyncHttpResponseHandler() {
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
}
