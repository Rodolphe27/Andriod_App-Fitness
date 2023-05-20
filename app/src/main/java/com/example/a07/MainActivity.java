package com.example.a07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    IntroductoryActivity SA = (IntroductoryActivity) IntroductoryActivity.SplashActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SA.finish();
        setContentView(R.layout.activity_main);
    }

    private long backKeyPressedTime = 0;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast toast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            finish();
        }
    }

    // Method to switch to a different activity based on the tag attribute of the clicked view
    public void switchActivity(View view) {
        String tag = view.getTag().toString();  // get the tag attribute of the clicked view as a string
        try {
            Class<?> activityClass = Class.forName(getPackageName() + "." + tag);       // construct the class name of the activity to be opened
            openActivity(activityClass);        // call openActivity with class of the activity to be opened
        } catch (ClassNotFoundException e) {
            e.printStackTrace();                // handle exceptions
        }
    }

    // Method to open an activity based on its class
    public void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);    // create an intent to open the activity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);                // set the flag to clear the activity stack
        startActivity(intent);                                          // start the activity
    }
}