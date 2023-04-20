package com.example.a07;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find buttons by ID and set an OnClickListener to log a message when the button is clicked
        Button button_questionnaire = (Button) findViewById(R.id.button_questionnaire);
        button_questionnaire.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the questionnaire"));

        Button button_tracking = (Button) findViewById(R.id.button_tracking);
        button_tracking.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the tracking"));

        Button button_archive = (Button) findViewById(R.id.button_archive);
        button_archive.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the archive"));

        Button button_settings = (Button) findViewById(R.id.button_settings);
        button_settings.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the settings"));

        // Do the same for navBar buttons
        ImageButton buttonQuestionnaire = (ImageButton) findViewById(R.id.buttonQuestionnaire);
        buttonQuestionnaire.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the questionnaire through the navBar"));

        ImageButton ButtonTracking = (ImageButton) findViewById(R.id.buttonTracking);
        ButtonTracking.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the tracking through the navBar"));

        ImageButton buttonArchive = (ImageButton) findViewById(R.id.buttonArchive);
        buttonArchive.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the archive through the navBar"));

        ImageButton buttonSettings = (ImageButton) findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(v -> Log.d("BUTTONS", "User tried to open the settings through the navBar"));
    }
}