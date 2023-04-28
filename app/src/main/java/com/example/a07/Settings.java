package com.example.a07;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Locale;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private boolean languageSelected = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner languageSpinner = findViewById(R.id.languageSpinner);
        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        Spinner fontsizeSpinner = findViewById(R.id.fontsizeSpinner);
        SwitchMaterial darkmodeSwitch = findViewById(R.id.darkmodeSwitch);

        sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);         // Specify the layout to use when the list of choices appears
        languageSpinner.setAdapter(adapter);                                                    // Apply the adapter to the spinner
        languageSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);
        colorSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> fontsizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.fontsize_array, android.R.layout.simple_spinner_item);
        fontsizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontsizeSpinner.setAdapter(fontsizeAdapter);
        fontsizeSpinner.setOnItemSelectedListener(this);

        // Retrieve the saved dark mode setting
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false);
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            darkmodeSwitch.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            darkmodeSwitch.setChecked(false);
        }

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

        // Set the dark mode switch listener
        darkmodeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Enable dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                // Disable dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            getDelegate().applyDayNight();

            // Save the dark mode setting
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();
        });
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
            else {
                Toast.makeText(this, "This would set the language to: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            // Save the dark mode setting
            editor.putString("language", selectedItem);
            editor.apply();
        }
    }

    // Implement the onNothingSelected method to do something when no item is selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show();
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

    // Method to open the main activity
    public void openMainActivity(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_home_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //intent created to open a new page (activity)
        startActivity(intent);
    }

    // Method to open the settings activity
    public void openSettings(View view){
        Toast toast = Toast.makeText(this, R.string.toast_settings_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, Settings.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Method to open the tracking activity
    public void openTracking(View view){
        Toast toast = Toast.makeText(this, R.string.toast_tracking_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, SportTracking.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // Method to open the archive activity
    public void openArchive(View view){
        Toast toast = Toast.makeText(this, R.string.toast_archive_main, Toast.LENGTH_SHORT);    //toast a text when open
        toast.show();
        Intent intent = new Intent(this, Archive.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}