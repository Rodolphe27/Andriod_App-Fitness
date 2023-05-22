package com.example.a07.dao;

import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.Query;



import com.example.a07.entity.SensorEntity;


import java.util.List;

@Dao
public interface SensorDao {
    @Insert
    void insert(SensorEntity sensor);

    @Query("SELECT * FROM sensorentity")
    List<SensorEntity> queryAll();


    // clear the " table
    @Query("DELETE FROM sensorentity")
    void deleteAll();



}
