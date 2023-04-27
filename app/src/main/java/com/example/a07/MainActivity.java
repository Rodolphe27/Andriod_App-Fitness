package com.example.a07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMainActivity(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_home_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void openQuestionnaire(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_questionnaire_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, Questionnaire.class); //intent created to open a new page (activity)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void openSettings(View view){
        Toast toast = Toast.makeText(this, R.string.toast_settings_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, Settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void openTracking(View view){
        Toast toast = Toast.makeText(this, R.string.toast_tracking_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, SportTracking.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void openArchive(View view){
        Toast toast = Toast.makeText(this, R.string.toast_archive_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, Archive.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}