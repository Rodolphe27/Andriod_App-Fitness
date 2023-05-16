package com.example.a07;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a07.dao.SportDao;
import com.example.a07.entity.SportEntity;
import com.example.a07.utils.SharedPreferencesUtil;
import com.example.a07.utils.Utils;

import java.util.Calendar;
import java.util.List;

public class Tracking extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SportDao sportDao;

    //declare attributes for sportpage
    protected Chronometer chronometer;
    private Spinner sportTypeSpinner;
    private TextView currentDate;
    private Calendar calendar;
    private long stopTime = 0;

    private long startTime = 0;
    private long pauseTime = 0;

    private long timeBuf = 0;

    private Button startBtn, stopBtn, pauseBtn, addSportBtn;

    private String sportName;
    private int myMoodScore; // moodscore after sport



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
                sportName = content;
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

                startTime = System.currentTimeMillis();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                stopTime =  chronometer.getBase() - SystemClock.elapsedRealtime();


                pauseTime = System.currentTimeMillis();
                timeBuf += pauseTime - startTime;

            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                stopTime = 0;
                timeBuf = System.currentTimeMillis() - pauseTime;
                timeBuf = timeBuf/1000/60;
                Utils.showToast(Tracking.this, Long.toString(timeBuf));
                chronometer.setBase(SystemClock.elapsedRealtime());
                showDialogSeekBar();
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


    private void showDialogSeekBar() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_seekbar, null);
        // mySeekBar
        SeekBar mySeekBar = mView.findViewById(R.id.myMoodScoreSeekBar);
        mySeekBar.setOnSeekBarChangeListener(this);
        mBuilder.setTitle(R.string.after_sport_seekbar);
        //put the seekbar into the dialog
        mBuilder.setView(mView);

        mBuilder.setPositiveButton(R.string.btn_after_sport_dialog_positive, (dialog, which) -> {
            // save to datebase;
            saveSportDataToDB();
            // todo? start questionaire?

        });

        mBuilder.setNegativeButton(R.string.btn_after_sport_dialog_negative, (dialog, which) -> {
           // back ot main page
            Intent intent = new Intent(Tracking.this, MainActivity.class);
            startActivity(intent);
        });


        //show the AlertDialog using show() method
        AlertDialog alertDialog2 = mBuilder.create();
        alertDialog2.show();
    }


    /*here is for mySeekBar.setOnSeekBarChangeListener(this)*/

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        myMoodScore = seekBar.getProgress();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    private void saveSportDataToDB() {
        // create SportEntity
        SportEntity sportEntity = new SportEntity();
        sportEntity.setTimeAndDateStamp(Utils.getCurrentDateAndTime());
        sportEntity.setName(sportName);
        sportEntity.setDuration(Long.toString(timeBuf));
        sportEntity.setMoodScore(myMoodScore);

        sportDao.insert(sportEntity);
        Utils.showToast(Tracking.this, "Sport Saved");
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