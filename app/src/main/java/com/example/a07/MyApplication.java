package com.example.a07;

import android.app.Application;

import androidx.room.Room;

import com.example.a07.database.AppDatabase;
import com.example.a07.database.GpsDatabase;
import com.example.a07.database.SensorDatabase;

public class MyApplication extends Application {


    private static MyApplication mApp;

    // create AppDatabase immediately after starting the APP
    private AppDatabase appDatabase;
    private SensorDatabase sensorDatabase;
    private GpsDatabase gpsDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        // the name of database is ""
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "appdb")
                .addMigrations()
                .allowMainThreadQueries()
                .build();

    }

    public static MyApplication getInstance() {
        return mApp;
    }

    public AppDatabase getAppDatebase() {
        return appDatabase;
    }
}
