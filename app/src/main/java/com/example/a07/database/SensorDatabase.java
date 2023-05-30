package com.example.a07.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.a07.dao.SensorDao;
import com.example.a07.entity.SensorEntity;

@Database(entities = {SensorEntity.class}, version = 1, exportSchema = true)
public abstract class SensorDatabase extends RoomDatabase {

    // exposure SensorDatabase
    public abstract SensorDao sensorDoa();


}
