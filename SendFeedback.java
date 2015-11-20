package edu.catysonpurdue.rotccadetprofiler;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.Stack;
import android.net.Uri;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by codytyson on 11/19/15.
 */
public class SendFeedback extends ActionBarActivity {
    public Intent createEmailOnlyChooserIntent(Intent source,
                                               CharSequence chooserTitle) {
        Stack<Intent> intents = new Stack<Intent>();
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                "info@domain.com", null));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bEmail = (Button) findViewById(R.id.bLogin);

        bEmail.setOnClickListener(this);
    }

//  @Override
    public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("*/*");
//        i.putExtra(Intent.EXTRA_EMAIL, new String[] {
//                ANDROID_SUPPORT_EMAIL
//        });
        i.putExtra(Intent.EXTRA_SUBJECT, "Crash report");
        i.putExtra(Intent.EXTRA_TEXT, "Some crash report details");

        startActivity(createEmailOnlyChooserIntent(i, "Send via email"));
    }
}
