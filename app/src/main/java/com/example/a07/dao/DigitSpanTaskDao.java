package com.example.a07.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.a07.entity.DigitSpanTaskEntity;

@Dao
public interface DigitSpanTaskDao {

    @Insert
    void insert(DigitSpanTaskEntity digitSpanTask);
}
