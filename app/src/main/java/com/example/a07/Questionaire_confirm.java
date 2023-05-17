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
import android.util.Log;
import android.view.View;

import com.example.a07.dao.QuesDao;
import com.example.a07.entity.QuestionaireEntity;
import com.example.a07.utils.Utils;

import java.util.List;

public class Questionaire_confirm extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private QuesDao quesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_confirm);

        // get preferences
        preferences = getSharedPreferences("ques_data", Context.MODE_PRIVATE);

        findViewById(R.id.btn_drop_ques).setOnClickListener(this);
        findViewById(R.id.btn_save_ques).setOnClickListener(this);
        findViewById(R.id.btn_query_all_ques).setOnClickListener(this);
        findViewById(R.id.btn_clear_table).setOnClickListener(this);

        quesDao = MyApplication.getInstance().getAppDatebase().quesDao();


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
                // create a new QuestionaireEntity instance
                QuestionaireEntity quesEntity = new QuestionaireEntity();

                quesEntity.setTimeAndDateStamp(Utils.getCurrentDateAndTime());

                // get data from SharedPreferences and put into instance
                quesEntity.setQues1(preferences.getInt("ques1", 50));
                quesEntity.setQues2(preferences.getInt("ques2", 50));
                quesEntity.setQues3(preferences.getInt("ques3", 50));
                quesEntity.setQues4(preferences.getInt("ques4", 50));
                quesEntity.setQues5(preferences.getInt("ques5", 50));
                quesEntity.setQues6(preferences.getInt("ques6", 50));

                quesEntity.setQues7(preferences.getInt("ques7", 50));
                quesEntity.setQues8(preferences.getInt("ques8", 50));


                quesEntity.setQues9(preferences.getString("ques9", null));
                quesEntity.setQues10(preferences.getInt("ques10", 50));

                quesEntity.setQues11(preferences.getString("ques11", null));

                quesEntity.setQues12(preferences.getString("ques12", null));

                quesEntity.setQues13(preferences.getInt("ques13", 50));
                quesEntity.setQues14(preferences.getInt("ques14", 50));
                quesEntity.setQues15(preferences.getInt("ques15", 50));
                quesEntity.setQues16(preferences.getInt("ques16", 50));

                quesEntity.setQues17(preferences.getInt("ques17", 50));
                quesEntity.setQues18(preferences.getInt("ques18", 50));

                quesEntity.setQues19(preferences.getInt("ques19", 50));
                quesEntity.setQues20(preferences.getInt("ques20", 50));

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
}