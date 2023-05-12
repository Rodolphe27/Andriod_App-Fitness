package com.example.a07;

import android.app.Application;

import androidx.room.Room;

import com.example.a07.database.AppDatabase;

public class MyApplication extends Application {


    private static MyApplication mApp;

    // create AppDatabase immediately after starting the APP
    private AppDatabase appDatabase;

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
