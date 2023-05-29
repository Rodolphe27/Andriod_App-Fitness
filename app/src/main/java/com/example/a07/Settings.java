package com.example.a07;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Locale;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private boolean languageSelected = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner languageSpinner = findViewById(R.id.languageSpinner);
        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        Spinner fontSizeSpinner = findViewById(R.id.fontSizeSpinner);
        Spinner sensorSpinner= findViewById(R.id.sensorSpinner);
        Button toNotification = findViewById(R.id.btn_setNotification);
        SwitchMaterial gpsSwitch = findViewById(R.id.gpsSwitch);


        sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);         // Specify the layout to use when the list of choices appears
        languageSpinner.setAdapter(adapter);                                                    // Apply the adapter to the spinner
        languageSpinner.setSelection(0);
        languageSpinner.setOnItemSelectedListener(this);
        // Set the language spinner to the saved language
        String selectedLanguage = sharedPreferences.getString("language", "English");
        int languagePosition = adapter.getPosition(selectedLanguage);
        languageSpinner.setSelection(languagePosition);

        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> fontSizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.fontsize_array, android.R.layout.simple_spinner_item);
        fontSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSizeSpinner.setAdapter(fontSizeAdapter);
        fontSizeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> sensorAdapter = ArrayAdapter.createFromResource(this,
                R.array.sensor_array, android.R.layout.simple_spinner_item);
        sensorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sensorSpinner.setAdapter(sensorAdapter);
        sensorSpinner.setOnItemSelectedListener(this);

//        // Retrieve the saved language Todo - doesn't work yet
//        String language = sharedPreferences.getString("language", ""); // Retrieve the saved language
//        if (!language.isEmpty()) {                                           // If a language is saved, set the language
//            if (language.equals("German")) {                                 // If the language is German, set the locale to German
//                setLocale(this, "de");
//            } else if (language.equals("English")) {
//                setLocale(this, "en");
//            }
//            else {
//                Toast.makeText(this, "This would set the language to: " + language, Toast.LENGTH_SHORT).show();
//            }
//        }

        // set the gps switch to the saved value
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            gpsSwitch.setChecked(sharedPreferences.getBoolean("gps", false));
        }
        else {
            gpsSwitch.setChecked(false);
            editor.putBoolean("gps", gpsSwitch.isChecked());
            editor.apply();
            Log.d("GPS", "Updated Shared Preferences - GPS Status: " + sharedPreferences.getBoolean("gps", false));
        }

        // Set the gps switch listener
        gpsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Check for ACCESS_FINE_LOCATION permission
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && isChecked) {

                // Permission is not granted
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "We need your location to provide better service", Toast.LENGTH_LONG).show();
                }
                // Request permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            } else {
                // If permission has already been granted save the gps setting in shared preferences
                editor.putBoolean("gps", isChecked);
                editor.apply();
                Log.d("GPS", "Updated Shared Preferences - GPS Status: " + sharedPreferences.getBoolean("gps", false));
            }
        });

        // Set the notification button listener
        toNotification.setOnClickListener(view -> {
            // Only request POST_NOTIFICATIONS permission for SDK 33 and above
            if (android.os.Build.VERSION.SDK_INT >= 33) {
                // Check and request for POST_NOTIFICATIONS permission
                if (ContextCompat.checkSelfPermission(view.getContext(),
                        Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) view.getContext(),
                            Manifest.permission.POST_NOTIFICATIONS)) {
                        Toast.makeText(view.getContext(), "We need your permission to send notifications", Toast.LENGTH_LONG).show();
                    }
                    // Request permission
                    ActivityCompat.requestPermissions((Activity) view.getContext(),
                            new String[]{Manifest.permission.POST_NOTIFICATIONS},
                            MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS);
                } else {
                    // Permission has already been granted
                    openActivity(SettingsNotificationTime.class);
                }
            }
            else {
                // SDK 32 and below don't need POST_NOTIFICATIONS permission - just open the activity
                openActivity(SettingsNotificationTime.class);
            }
        });
    }
  
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SwitchMaterial gpsSwitch = findViewById(R.id.gpsSwitch);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    // permission denied
                    Toast.makeText(this, "Location permission denied. Please enable exact position for the app in the settings.", Toast.LENGTH_SHORT).show();
                    gpsSwitch.setChecked(false);
                }
                editor.putBoolean("gps", gpsSwitch.isChecked());
                editor.apply();
                Log.d("GPS", "Updated Shared Preferences - GPS Status: " + sharedPreferences.getBoolean("gps", false));
                return;
            }
            case MY_PERMISSIONS_REQUEST_POST_NOTIFICATIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show();
                    openActivity(SettingsNotificationTime.class);
                } else {
                    // permission denied
                    Toast.makeText(this, "Notification permission denied. Please allow Notifications to receive a reminder for the questionnaire.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // Method to do something when an item is selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (!languageSelected) {
            // Don't apply the language when the activity is first loaded
            languageSelected = true;
            return;
        }
        String selectedItem = parent.getItemAtPosition(position).toString();

        // Change app language based on selection
        if (parent.getId() == R.id.languageSpinner) {
            if (selectedItem.equals("German")){
                setLocale(this, "de");
                recreate();
            }
            else if (selectedItem.equals("Englisch")){
                setLocale(this, "en");
                recreate();
            }
            // Save the language setting
            editor.putString("language", selectedItem);
            editor.apply();
        } else if (parent.getId() == R.id.sensorSpinner) {
            if (selectedItem.equals("Choose Sensor")) {

            } else if (selectedItem.equals("SensorData")) {
                openActivity(Sensors.class);
            } else if (selectedItem.equals("GpsData")) {
                openActivity(GPS.class);
            } else {
                Toast.makeText(this, "This would open the respective sensor view for: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

        }
    }

    // Implement the onNothingSelected method to do something when no item is selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    // Method to set the language
    public void setLocale (Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
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