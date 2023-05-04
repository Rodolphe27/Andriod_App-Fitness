package com.example.a07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SettingsNotificationTime extends AppCompatActivity implements View.OnClickListener {
    private TimePicker timePicker;
    private TextView timePicker_result1;
    private TextView timePicker_result2;
    private TextView timePicker_result3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notification_time);

        timePicker = findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        timePicker_result1 = findViewById(R.id.text_timePicker_result1);
        timePicker_result2 = findViewById(R.id.text_timePicker_result2);
        timePicker_result3 = findViewById(R.id.text_timePicker_result3);

        Button returnSettings = findViewById(R.id.btn_returnSettings);

        //set onClickListeners for buttons
        findViewById(R.id.btn_timeOK1).setOnClickListener(this);
        findViewById(R.id.btn_timeOk2).setOnClickListener(this);
        findViewById(R.id.btn_timeOK3).setOnClickListener(this);

        findViewById(R.id.btn_returnSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SettingsNotificationTime.this, Settings.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id  == R.id.btn_timeOK1){
            String desc = String.format("%d:%d",timePicker.getHour(), timePicker.getMinute());
            timePicker_result1.setText(desc);

        }
        if (id  == R.id.btn_timeOk2){
            String desc = String.format("%d:%d",timePicker.getHour(), timePicker.getMinute());
            timePicker_result2.setText(desc);

        }
        if (id  == R.id.btn_timeOK3){
            String desc = String.format("%d:%d",timePicker.getHour(), timePicker.getMinute());
            timePicker_result3.setText(desc);

        }
    }
}