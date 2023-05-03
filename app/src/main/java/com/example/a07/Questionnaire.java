package com.example.a07;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a07.utils.Utils;

public class Questionnaire extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

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
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        // create SharedPreferences
        preferences = getSharedPreferences("ques_data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        // group 1
        ques1_seekBar = findViewById(R.id.ques1_seekbar);
        ques1_seekBar.setOnSeekBarChangeListener(this);

        ques2_seekBar = findViewById(R.id.ques2_seekbar);
        ques2_seekBar.setOnSeekBarChangeListener(this);

        ques3_seekBar = findViewById(R.id.ques3_seekbar);
        ques3_seekBar.setOnSeekBarChangeListener(this);

        ques4_seekBar = findViewById(R.id.ques4_seekbar);
        ques4_seekBar.setOnSeekBarChangeListener(this);

        ques5_seekBar = findViewById(R.id.ques5_seekbar);
        ques5_seekBar.setOnSeekBarChangeListener(this);

        ques6_seekBar = findViewById(R.id.ques6_seekbar);
        ques6_seekBar.setOnSeekBarChangeListener(this);

        // group2
        ques7_seekBar = findViewById(R.id.ques7_seekbar);
        ques7_seekBar.setOnSeekBarChangeListener(this);

        ques8_seekBar = findViewById(R.id.ques8_seekbar);
        ques8_seekBar.setOnSeekBarChangeListener(this);

        //group3
        ques9_radioGroup = findViewById(R.id.ques9_radioGroup);
        ques9_radioGroup.setOnCheckedChangeListener(this);

        ques10_seekBar = findViewById(R.id.ques10_seekbar);
        ques10_seekBar.setOnSeekBarChangeListener(this);

        //group4
        ques11_spinner = findViewById(R.id.ques11_spinner);

        ques11_edittext = findViewById(R.id.ques11_others_edittext);

        ques11_confirm_btn = findViewById(R.id.ques11_others_confirm_btn);
        ques11_confirm_btn.setOnClickListener(this);


        ArrayAdapter<String> startAdapter_ques11 = new ArrayAdapter<>(this, R.layout.ques_spinner_item_layout, Ques11Array);
        ques11_spinner.setAdapter(startAdapter_ques11);
        ques11_spinner.setSelection(0);
        ques11_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // check if user selected "others"
                if(position == Ques11Array.length-1) {
                    findViewById(R.id.ques11_others_layout).setVisibility(View.VISIBLE);
                    Utils.showToast(Questionnaire.this, "please input others");
                } else {
                    editor.putString("ques11", Ques11Array[position]);
                    editor.commit();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //group5
        ques12_spinner = findViewById(R.id.ques12_spinner);

        ques12_edittext = findViewById(R.id.ques12_others_edittext);

        ques12_confirm_btn = findViewById(R.id.ques12_others_confirm_btn);
        ques12_confirm_btn.setOnClickListener(this);


        ArrayAdapter<String> startAdapter_ques12 = new ArrayAdapter<>(this, R.layout.ques_spinner_item_layout, Ques12Array);
        ques12_spinner.setAdapter(startAdapter_ques12);
        ques12_spinner.setSelection(0);
        ques12_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // check if user selected "others"
                if(position == Ques12Array.length-1) {
                    findViewById(R.id.ques12_others_layout).setVisibility(View.VISIBLE);
                    Utils.showToast(Questionnaire.this, "please input others");
                } else {
                    editor.putString("ques12", Ques12Array[position]);
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }












    /* seekbar lisener*/

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch(seekBar.getId()) {
            case R.id.ques1_seekbar:
                int progress1 = seekBar.getProgress();
                editor.putInt("ques1", progress1);
                editor.commit();
                break;
            case R.id.ques2_seekbar:
                int progress2 = seekBar.getProgress();
                editor.putInt("ques2", progress2);
                editor.commit();
                break;
            case R.id.ques3_seekbar:
                int progress3 = seekBar.getProgress();
                editor.putInt("ques3", progress3);
                editor.commit();
                break;
            case R.id.ques4_seekbar:
                int progress4 = seekBar.getProgress();
                editor.putInt("ques4", progress4);
                editor.commit();
                break;
            case R.id.ques5_seekbar:
                int progress5 = seekBar.getProgress();
                editor.putInt("ques5", progress5);
                editor.commit();
                break;
            case R.id.ques6_seekbar:
                int progress6 = seekBar.getProgress();
                editor.putInt("ques6", progress6);
                editor.commit();
                break;
            case R.id.ques7_seekbar:
                int progress7 = seekBar.getProgress();
                editor.putInt("ques7", progress7);
                editor.commit();
                break;
            case R.id.ques8_seekbar:
                int progress8 = seekBar.getProgress();
                editor.putInt("ques8", progress8);
                editor.commit();
                break;
            case R.id.ques10_seekbar:
                int progress10 = seekBar.getProgress();
                editor.putInt("ques10", progress10);
                editor.commit();
                break;
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }



    // ques10  RadioGroup
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.ques9_radioBtn_alone:
                editor.putString("ques9", "alone");
                editor.commit();
                break;
            case R.id.ques9_radioBtn_not_alone:
                editor.putString("ques9", "not_alone");
                editor.commit();
                break;
        }
    }


    // ques11  edittext for others

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ques11_others_confirm_btn:
                    String othersWho_ques11 = ques11_edittext.getText().toString();
                    editor.putString("ques11", othersWho_ques11);
                    editor.commit();
                    Utils.showToast(this, "ques11: " + othersWho_ques11);
                    // hide the EditText and button
                    findViewById(R.id.ques11_others_layout).setVisibility(View.GONE);
                break;
            case R.id.ques12_others_confirm_btn:
                    String othersWho_ques12 = ques12_edittext.getText().toString();
                    editor.putString("ques12", othersWho_ques12);
                    editor.commit();
                    Utils.showToast(this, "ques12: " + othersWho_ques12);
                    // hide the EditText and button
                    findViewById(R.id.ques12_others_layout).setVisibility(View.GONE);
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