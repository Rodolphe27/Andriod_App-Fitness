package com.example.a07;

import android.app.Application;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;

import com.example.a07.dao.QuesDao;
import com.example.a07.dao.SportDao;
import com.example.a07.database.AppDatabase;
import com.example.a07.database.DigitSpanTaskDatabase;
import com.example.a07.database.GpsDatabase;
import com.example.a07.database.SensorDatabase;
import com.example.a07.database.SportDatabase;
import com.example.a07.entity.SportEntity;
import com.example.a07.utils.SharedPreferencesUtil;
import com.example.a07.utils.Utils;

import java.util.List;

public class MyApplication extends Application {

    public int sportCount;


    private static MyApplication mApp;

    // create AppDatabase immediately after starting the APP
    private AppDatabase appDatabase;
    private SportDatabase sportDatabase;


    private SensorDatabase sensorDatabase;
    private GpsDatabase gpsDatabase;
    private DigitSpanTaskDatabase digitSpanTaskDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        // the name of database is ""
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "appdb")
                .addMigrations()
                .allowMainThreadQueries()
                .build();

        sportDatabase = Room.databaseBuilder(this, SportDatabase.class, "sportdb")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        sensorDatabase = Room.databaseBuilder(this, SensorDatabase.class, "sensordb")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        gpsDatabase = Room.databaseBuilder(this, GpsDatabase.class, "gpsdb")
                .addMigrations()
                .allowMainThreadQueries()
                .build();
        digitSpanTaskDatabase = Room.databaseBuilder(this ,DigitSpanTaskDatabase.class, "digitspantaskdb")
                .addMigrations()
                .allowMainThreadQueries()
                .build();


        // insert some mock data;
        insertMockSportData();

    }

    public static MyApplication getInstance() {
        return mApp;
    }

    public AppDatabase getAppDatebase() {
        return appDatabase;
    }

    public SportDatabase getSportDatabase() {
        return sportDatabase;
    }

    public SensorDatabase getSensorDatabase() {
        return sensorDatabase;
    }

    public GpsDatabase getGpsDatabase() {
        return gpsDatabase;
    }
    public DigitSpanTaskDatabase getDigitSpanTaskDatabase() {
        return digitSpanTaskDatabase;
    }

    private void insertMockSportData() {
        // if this is the firsttime open the phone, insert the mock data into database
        boolean isFirst = SharedPreferencesUtil.getInstance(this).readBoolean("first", true);
        if(isFirst) {
            Utils.showToast(this, "Welcome to first use this App");
            // first get SportDao:
            SportDao mySportDao = sportDatabase.sportDao();
            // write 2 mock data into database
            List<SportEntity> list = SportEntity.getDefaultList();
            for(SportEntity item : list) {
                mySportDao.insert(item);
            }

            // set first open app to false
            SharedPreferencesUtil.getInstance(this).writeBoolean("first", false);
        }
    }
}
