package com.example.a07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.example.a07.dao.SensorDao;
import com.example.a07.entity.SensorEntity;

import com.example.a07.utils.Utils;

public class Sensors extends AppCompatActivity implements View.OnClickListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isCounterSensorPresent;
    private Integer stepCount = 0;
    private double threshold = 1.0; // Adjust the threshold as needed
    private double previousAcceleration = 0.0;
    private boolean isStepDetected = false;
    private SensorEventListener stepDetector;
    TextView textViewStepCounter;

    SensorDao sensorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get references to UI elements
        setContentView(R.layout.activity_sensor);
        Button backToSetting = findViewById(R.id.btn_Tosetting);
        findViewById(R.id.btn_tosave).setOnClickListener(this);



        textViewStepCounter = findViewById(R.id.txt_stepCounter);
        //Intialize Sensor manager and sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Initialize the DAO

        sensorDao = MyApplication.getInstance().getSensorDatabase().sensorDoa();

        //Initialise step Detector
        stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    //Retrieve acceleration values
                    float xAcceleration = sensorEvent.values[0];
                    float yAcceleration = sensorEvent.values[1];
                    float zAcceleration = sensorEvent.values[2];

                    //Calculate magnitude and magnitude delta
                    double magnitude = Math.sqrt(xAcceleration * xAcceleration + yAcceleration * yAcceleration + zAcceleration * zAcceleration);
                    double magnitudeDelta = magnitude - previousAcceleration;
                    previousAcceleration = magnitude;

                    // Detect step based on magnitude delta and threshold
                    if (magnitudeDelta > threshold && !isStepDetected) {
                        stepCount++;
                        isStepDetected = true;
                    } else if (magnitudeDelta < threshold) {
                        isStepDetected = false;
                    }

                    //Update step counter text view
                    textViewStepCounter.setText(stepCount.toString());
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //Empty implementation
            }
        };

        //// Set onClickListener for backToSetting button
        backToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to Settings activity
                Intent intent = new Intent();
                intent.setClass(Sensors.this, Settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Register step detector listener
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Resume step detector listener
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister step detector listener
        sensorManager.unregisterListener(stepDetector);
    }
    private void sensorDataToDB() {
        // create SensorEntity
        SensorEntity sensorEntity= new SensorEntity();
        sensorEntity.setTimeAndDateStamp(Utils.getCurrentDateAndTime());
        sensorEntity.setStepCount(stepCount);

        // Insert SensorEntity into the database
        sensorDao.insert(sensorEntity);
        Utils.showToast(Sensors.this, "Data Saved");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_tosave) {
            // Call method to save sensor data to database
            sensorDataToDB();
        }

    }
}
