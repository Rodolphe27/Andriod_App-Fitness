package com.example.a07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Tracking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_tracking);
    }

    // Methods to switch activity
    public void switchActivity(View view) {
        String tag = view.getTag().toString();
        try {
            Class<?> activityClass = Class.forName(getPackageName() + "." + tag);
            openActivity(activityClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}