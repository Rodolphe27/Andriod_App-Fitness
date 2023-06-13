package com.example.a07;

/*
* in this page, the date should be saved into database
*
* */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.a07.dao.QuesDao;
import com.example.a07.entity.QuestionaireEntity;
import com.example.a07.utils.Utils;

import java.util.HashMap;
import java.util.List;

public class Questionaire_confirm extends AppCompatActivity implements View.OnClickListener {

    // clickMap which contains the click information of each questionaire item
    private HashMap<Integer, Boolean> myClickMap = new HashMap<>();

    // ques 21
    private EditText ques21_editText;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private QuesDao quesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_confirm);

        // ques21
        ques21_editText = findViewById(R.id.ques21_editText);
        ques21_editText.addTextChangedListener(new MyTextWatcher(ques21_editText));

        // get preferences and create editor
        preferences = getSharedPreferences("ques_data", Context.MODE_PRIVATE);
        editor = preferences.edit();

        findViewById(R.id.btn_drop_ques).setOnClickListener(this);
        findViewById(R.id.btn_save_ques).setOnClickListener(this);
        findViewById(R.id.btn_query_all_ques).setOnClickListener(this);
        findViewById(R.id.btn_clear_table).setOnClickListener(this);

        quesDao = MyApplication.getInstance().getAppDatebase().quesDao();

    }

    @Override
    protected void onResume() {
        super.onResume();
        myClickMap = MyApplication.getInstance().clickMap;
    }

    private Integer checkAndSaveInt(SharedPreferences preferences, Integer quesNr, int defaultvalue) {
        // if the question item is clicked
        if(myClickMap.get(quesNr)) {
            return preferences.getInt("ques"+quesNr, defaultvalue);
        } else {
            return defaultvalue;
        }

    }

    private String checkAndSaveString(SharedPreferences preferences, Integer quesNr, String defaultvalue) {
        // if the question item is clicked
        if(myClickMap.get(quesNr)) {
            return preferences.getString("ques"+quesNr, defaultvalue);
        } else {
            return defaultvalue;
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_drop_ques:
                // back to Questionaire page so that user can modify the Ques again
                Intent intent_back_to_ques = new Intent(this, Questionnaire.class);
                intent_back_to_ques.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_back_to_ques);
                break;
            case R.id.btn_save_ques:
//                System.out.println(myClickMap);
                // create a new QuestionaireEntity instance
                QuestionaireEntity quesEntity = new QuestionaireEntity();

                quesEntity.setTimeAndDateStamp(Utils.getCurrentDateAndTime());

                // get data from SharedPreferences and put into instance
                // if you does not scroll the seekbar, the the default value will be saved into the database;
                quesEntity.setQues1(checkAndSaveInt(preferences, 1, 0));
                quesEntity.setQues2(checkAndSaveInt(preferences, 2, 0));
                quesEntity.setQues3(checkAndSaveInt(preferences, 3, 0));
                quesEntity.setQues4(checkAndSaveInt(preferences, 4, 0));
                quesEntity.setQues5(checkAndSaveInt(preferences, 5, 0));
                quesEntity.setQues6(checkAndSaveInt(preferences, 6, 0));

                quesEntity.setQues7(checkAndSaveInt(preferences, 7, 50));
                quesEntity.setQues8(checkAndSaveInt(preferences, 8, 50));


                quesEntity.setQues9(checkAndSaveString(preferences, 9, "alone"));
                quesEntity.setQues10(checkAndSaveInt(preferences, 10, 50));

                quesEntity.setQues11(checkAndSaveString(preferences, 11, "Partner"));

                quesEntity.setQues12(checkAndSaveString(preferences, 12, "At home"));

                quesEntity.setQues13(checkAndSaveInt(preferences, 13, 50));
                quesEntity.setQues14(checkAndSaveInt(preferences, 14, 50));
                quesEntity.setQues15(checkAndSaveInt(preferences, 15, 50));
                quesEntity.setQues16(checkAndSaveInt(preferences, 16, 50));

                quesEntity.setQues17(checkAndSaveInt(preferences, 17, 4));
                quesEntity.setQues18(checkAndSaveInt(preferences, 18, 4));

                quesEntity.setQues19(checkAndSaveInt(preferences, 19, 3));
                quesEntity.setQues20(checkAndSaveInt(preferences, 20, 3));
                quesEntity.setQues21(checkAndSaveString(preferences, 21, "the user does not have comment"));


//                Log.d("test_tag", quesEntity.toString());

                // save into database
                try {
                    quesDao.insert(quesEntity);
                    Utils.showToast(this, "insert with success");
                }catch (Exception e) {
                    Utils.showToast(this, e.getMessage());
                }
                break;
            case R.id.btn_query_all_ques:
                Log.d("divider", "############################");
                try {
                    int recordsNr = 0;
                    List<QuestionaireEntity> list = quesDao.queryAll();
                    for (QuestionaireEntity ques : list) {
                        recordsNr++;
                        Log.d("query_all_tag", ques.toString());
                    }
                    Utils.showToast(this, "recordsNr = " + recordsNr);
                }catch (Exception e) {
                    Utils.showToast(this, e.getMessage());
                }
                break;
            case R.id.btn_clear_table:
                try {
                    quesDao.deleteAll();
                    Utils.showToast(this, "table cleared");
                }catch (Exception e) {
                    Utils.showToast(this, e.getMessage());
                }
                break;
        }

    }

    private class MyTextWatcher implements TextWatcher {

        // a private EditText Object
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // update the clickMap for the 21th item in questionaire
            myClickMap.put(21, true);
            MyApplication.getInstance().clickMap.put(21, true);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // get the text inputed
            String str = editable.toString();
            // put the string into sharedpreferences
            editor.putString("ques21", str);
            editor.commit();
        }
    }

}