package com.example.a07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class sportTest02 extends AppCompatActivity /*implements View.OnClickListener*/ {
    //declare attributes
    protected Chronometer chronometer;
    private Spinner sportTypeSpinner;
    private TextView currentDate;
    private Calendar calendar;
    private long stopTime;
    protected long stopTimeStored;

    private Button startBtn, stopBtn, pauseBtn, addSportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_test02);

        //connect attributes with layout elements
        sportTypeSpinner = findViewById(R.id.spinnerSportType);
        currentDate = findViewById(R.id.tvShowCurrentDate);
        chronometer = findViewById(R.id.chronometerSport);
        stopTime = 0;
        stopTimeStored = 0;

        //set current date
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        currentDate.setText(day + "." + month + "." + year);

        //buttons
        startBtn = findViewById(R.id.btnSportStart);
        stopBtn = findViewById(R.id.btnSportStop);
        pauseBtn = findViewById(R.id.btnSportPause);
        addSportBtn = findViewById(R.id.btnAddSport);

        /*//set listener for buttons
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        addSportBtn.setOnClickListener(this);*/

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
                chronometer.start();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                stopTime =  chronometer.getBase() - SystemClock.elapsedRealtime();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                stopTimeStored = stopTime;      //store stopTime in stopTimeStored (for later use)
                stopTime = 0;
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
        });


        /*
         * addSportBtn.setOnClickListener(this);
         * todo: add function to the add button
         */

        //set listener for the sport type spinner
        sportTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String content = parent.getItemAtPosition(position).toString();
                Toast.makeText(sportTest02.this, "The selected sport is: " + content,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /*//onclick listener for buttons
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSportStart:
                chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
                chronometer.start();

            case R.id.btnSportPause:
                stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();


            case R.id.btnSportStop:
                chronometer.setBase(SystemClock.elapsedRealtime());
                stopTimeStored = stopTime;      //store stopTime in stopTimeStored (for later use)
                stopTime = 0;

            case R.id.btnAddSport:
                // TODO: add recorded sport into database
        }
    }*/
}
