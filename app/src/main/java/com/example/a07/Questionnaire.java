package com.example.a07;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Questionnaire extends AppCompatActivity {

    // group1
    private SeekBar ques1_seekBar;
    private SeekBar ques2_seekBar;
    private SeekBar ques3_seekBar;
    private SeekBar ques4_seekBar;
    private SeekBar ques5_seekBar;
    private SeekBar ques6_seekBar;

    // group2
    private SeekBar ques7_seekBar;
    private SeekBar ques8_seekBar;

    // group3
    private RadioGroup ques9_radioGroup;
    private SeekBar ques10_seekBar;

    // group4
    private Spinner ques11_spinner;
    private final static String[] Ques11Array = {"Partner", "Family", "Friends", "Coworkers", "Strangers", "Others"};
    private EditText ques11_edittext;
    private Button ques11_confirm_btn;

    // group5
    private Spinner ques12_spinner;
    private final static String[] Ques12Array = {"At home", "School/University", "Work", "Sport", "other recreational Activity",
                                                "Shopping", "Visiting", "Others"};
    private EditText ques12_edittext;
    private Button ques12_confirm_btn;

    // group6
    private SeekBar ques13_seekBar;
    private SeekBar ques14_seekBar;
    private SeekBar ques15_seekBar;
    private SeekBar ques16_seekBar;

    // group7

    private SeekBar ques17_seekBar;
    private SeekBar ques18_seekBar;

    // group8
    private SeekBar ques19_seekBar;
    private SeekBar ques20_seekBar;


    // cache the questionaire
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        // group 1
        findViewById(R.id.ques1_seekbar);





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