package com.example.a07.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a07.entity.DigitSpanTaskEntity;

@Dao
public interface DigitSpanTaskDao {

    @Insert
    void insert(DigitSpanTaskEntity digitSpanTask);
    @Query("DELETE FROM digitSpanTable")
    void deletAll();
}
