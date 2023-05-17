package com.example.a07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a07.adapter.SportArchivAdapter;
import com.example.a07.dao.SportDao;
import com.example.a07.entity.SportEntity;
import com.example.a07.utils.Utils;

import java.util.List;

public class Archive extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    // the sportList
    private List<SportEntity> sportList;

    // TextView
    private TextView sportCount;
    private TextView sportTimeSum;
    private ListView listViewSport;
    private LinearLayout linearLayout_empty;
    private LinearLayout linearLayout_content;

    //adapter
    private SportArchivAdapter sportArchivAdapter;


    // get SportDao
    private SportDao sportDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        listViewSport = findViewById(R.id.listView_sport);
        sportCount = findViewById(R.id.tv_sport_count);
        sportTimeSum = findViewById(R.id.tv_total_sport_time);

        linearLayout_empty = findViewById(R.id.ll_empty);
        linearLayout_content = findViewById(R.id.ll_content);

        // back
        findViewById(R.id.iv_sport_archive_back).setOnClickListener(this);
        // clear sport archiv
        findViewById(R.id.btn_sport_clear).setOnClickListener(this);
        // go to analyze chart
        findViewById(R.id.btn_sport_go_to_analyze).setOnClickListener(this);

        sportDao = MyApplication.getInstance().getSportDatabase().sportDao();

    }


    // show list on resume
    @Override
    protected void onResume() {
        super.onResume();
        showSportList();
    }

    private void showSportList() {

        // get sport List from database
        sportList = sportDao.queryAll();
        if(sportList.size() == 0) {
            return;
        }

        // now you get the datalist, so it's time to get the adapter
        sportArchivAdapter = new SportArchivAdapter(this, sportList);
        // ListView receive the adapter
        listViewSport.setAdapter(sportArchivAdapter);

        // add click long listener,  delete item
        listViewSport.setOnItemLongClickListener(this);

    }

    // long click on a item to delete it
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

        // targert item
        SportEntity item =  sportList.get(position);
        // dialog
        AlertDialog.Builder builder =  new AlertDialog.Builder(Archive.this);
        builder.setMessage(getString(R.string.sport_dialog_delete) + " " + item.getName() + " at " + item.getTimeAndDateStamp() + "?");
        builder.setPositiveButton(R.string.sport_dialog_yes, (dialog, which) -> {
            // delete frrm database
            sportDao.deleteItem(item);

            // delete this info in list
            sportList.remove(position);

            // refresh the adapter
            sportArchivAdapter.notifyDataSetChanged();

            Utils.showToast(this,  R.string.sport_dialog_delete + " " +  item.getName());
        });

        builder.setNegativeButton(R.string.sport_dialog_no, null);
        builder.create().show();
        return true;
    }

    @Override
    public void onClick(View view) {

    }
























    // NAVIGATION

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