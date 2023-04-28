package com.example.a07;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set darkmode according to the saved setting
        SharedPreferences sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false);
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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