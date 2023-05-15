package com.example.a07;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a07.dao.SportDao;
import com.example.a07.entity.QuestionaireEntity;
import com.example.a07.entity.SportEntity;
import com.example.a07.utils.SharedPreferencesUtil;
import com.example.a07.utils.Utils;

import java.util.List;

public class Tracking extends AppCompatActivity implements View.OnClickListener {

    private SportDao sportDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_tracking);

        findViewById(R.id.btn_sport_queryAll).setOnClickListener(this);
        findViewById(R.id.btn_reset_firstopen).setOnClickListener(this);

        // get sportDao
        sportDao = MyApplication.getInstance().getSportDatabase().sportDao();
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