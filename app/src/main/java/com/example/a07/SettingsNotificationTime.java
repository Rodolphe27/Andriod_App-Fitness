package com.example.a07;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsNotificationTime extends AppCompatActivity implements View.OnClickListener {
    private TimePicker timePicker;
    private TextView timePicker_result1;
    private TextView timePicker_result2;
    private TextView timePicker_result3;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notification_time);

        sharedPreferences = getSharedPreferences("my_app_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        timePicker = findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        timePicker_result1 = findViewById(R.id.text_timePicker_result1);
        timePicker_result1.setText(sharedPreferences.getString("time1", ""));
        timePicker_result2 = findViewById(R.id.text_timePicker_result2);
        timePicker_result2.setText(sharedPreferences.getString("time2", ""));
        timePicker_result3 = findViewById(R.id.text_timePicker_result3);
        timePicker_result3.setText(sharedPreferences.getString("time3", ""));

        Button returnSettings = findViewById(R.id.btn_returnSettings);

        //set onClickListeners for buttons
        findViewById(R.id.btn_timeOK1).setOnClickListener(this);
        findViewById(R.id.btn_timeOk2).setOnClickListener(this);
        findViewById(R.id.btn_timeOK3).setOnClickListener(this);

        returnSettings.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(SettingsNotificationTime.this, Settings.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id  == R.id.btn_timeOK1){
            String desc = String.format(Locale.getDefault(), "%d:%d",timePicker.getHour(), timePicker.getMinute());
            timePicker_result1.setText(desc);
            saveTime(desc, 1);
        }
        if (id  == R.id.btn_timeOk2){
            String desc = String.format(Locale.getDefault(), "%d:%d",timePicker.getHour(), timePicker.getMinute());
            timePicker_result2.setText(desc);
            saveTime(desc, 2);
        }
        if (id  == R.id.btn_timeOK3){
            String desc = String.format(Locale.getDefault(), "%d:%d",timePicker.getHour(), timePicker.getMinute());
            timePicker_result3.setText(desc);
            saveTime(desc, 3);
        }
    }

    // save time to shared preferences
    public void saveTime(String time, int id) {
        editor.putString("time"+id, time);
        editor.apply();
    }
}