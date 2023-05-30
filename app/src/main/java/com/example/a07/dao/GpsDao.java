package com.example.a07.dao;

import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.Query;


import com.example.a07.entity.GpsEntity;


import java.util.List;

@Dao
public interface GpsDao {
    @Insert
    void insert(GpsEntity gps);

    @Query("SELECT * FROM gps_table")
    List<GpsEntity> queryAll();


    // clear the " table
    @Query("DELETE FROM gps_table")
    void deleteAll();



}
