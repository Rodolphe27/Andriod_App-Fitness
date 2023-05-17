package com.example.a07;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sensors extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isCounterSensorPresent;
    private Integer stepCount = 0;
    private double threshold = 1.0; // Adjust the threshold as needed
    private double previousAcceleration = 0.0;
    private boolean isStepDetected = false;
    private SensorEventListener stepDetector;
    TextView textViewStepCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textViewStepCounter = findViewById(R.id.StepCounter);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    float xAcceleration = sensorEvent.values[0];
                    float yAcceleration = sensorEvent.values[1];
                    float zAcceleration = sensorEvent.values[2];

                    double magnitude = Math.sqrt(xAcceleration * xAcceleration + yAcceleration * yAcceleration + zAcceleration * zAcceleration);
                    double magnitudeDelta = magnitude - previousAcceleration;
                    previousAcceleration = magnitude;

                    if (magnitudeDelta > threshold && !isStepDetected) {
                        stepCount++;
                        isStepDetected = true;
                    } else if (magnitudeDelta < threshold) {
                        isStepDetected = false;
                    }
                    textViewStepCounter.setText(stepCount.toString());
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(stepDetector);
    }
}
