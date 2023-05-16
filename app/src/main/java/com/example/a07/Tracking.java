package com.example.a07;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a07.dao.SportDao;
import com.example.a07.entity.QuestionaireEntity;
import com.example.a07.entity.SportEntity;
import com.example.a07.utils.SharedPreferencesUtil;
import com.example.a07.utils.Utils;

import java.util.Calendar;
import java.util.List;

public class Tracking extends AppCompatActivity implements View.OnClickListener {

    private SportDao sportDao;

    //declare attributes for sportpage
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
        setContentView(R.layout.activity_sport_tracking);
        findViewById(R.id.btn_sport_queryAll).setOnClickListener(this);
        findViewById(R.id.btn_reset_firstopen).setOnClickListener(this);
        // get sportDao
        sportDao = MyApplication.getInstance().getSportDatabase().sportDao();


        // Minhua
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

        //set listener for the sport type spinner
        sportTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String content = parent.getItemAtPosition(position).toString();
                Utils.showToast(Tracking.this, "The selected sport is: " + content);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


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
                stopTime = 0; // reset stop time
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
        });


        /*
         * addSportBtn.setOnClickListener(this);
         * todo: add function to the add button
         */


      

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_sport_queryAll:
                Log.d("divider", "############################");
                try {
                    int recordsNr = 0;
                    List<SportEntity> list = sportDao.queryAll();
                    for (SportEntity item : list) {
                        recordsNr++;
                        Log.d("query_all_tag", item.toString());
                    }
                    Utils.showToast(this, "recordsNr = " + recordsNr);
                }catch (Exception e) {
                    Utils.showToast(this, e.getMessage());
                }
                break;
            case R.id.btn_reset_firstopen:
                SharedPreferencesUtil.getInstance(this).writeBoolean("first", true);
                Utils.showToast(this, "reset first open to true");
                break;
        }
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