package com.prgguru.jersey;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Stack;

/**
 * Created by dashaunjones on 11/19/15.
 */
public class CheckUserEmail extends ActionBarActivity implements View.OnClickListener{
    Button seeEmail;
    EditText inputEmail;
    static String pwd;

    public static boolean checkEmail(String email) throws Exception {
        boolean emailExists = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user WHERE email = '" + email;
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                pwd = rs.getString("password");
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                emailExists = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return emailExists;
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.password_forgotten);

            seeEmail = (Button) findViewById(R.id.seeEmail);
            inputEmail = (EditText) findViewById(R.id.inputEmail);

            seeEmail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            boolean exists = false;
            String email = inputEmail.getText().toString();

            try {
                exists = checkEmail(email);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(exists) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("*/*");
//        i.putExtra(Intent.EXTRA_EMAIL, new String[] {
//                ANDROID_SUPPORT_EMAIL
//        });
                i.putExtra(Intent.EXTRA_SUBJECT, "Forgotten Password");
                i.putExtra(Intent.EXTRA_TEXT, "Your password is: " + pwd);

                startActivity(createEmailOnlyChooserIntent(i, "Send via email", email));
            }
        }

    public Intent createEmailOnlyChooserIntent(Intent source,
                                               CharSequence chooserTitle, String email) {
        Stack<Intent> intents = new Stack<Intent>();
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                email, null));
        List<ResolveInfo> activities = getPackageManager()
                .queryIntentActivities(i, 0);

        for(ResolveInfo ri : activities) {
            Intent target = new Intent(source);
            target.setPackage(ri.activityInfo.packageName);
            intents.add(target);
        }

        if(!intents.isEmpty()) {
            Intent chooserIntent = Intent.createChooser(intents.remove(0),
                    chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    intents.toArray(new Parcelable[intents.size()]));

            return chooserIntent;
        } else {
            return Intent.createChooser(source, chooserTitle);
        }
    }
}
