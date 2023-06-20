package com.example.a07.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.a07.dao.DigitSpanTaskDao;

import com.example.a07.entity.DigitSpanTaskEntity;


@Database(entities= {DigitSpanTaskEntity.class}, version = 1, exportSchema = true)
public abstract class DigitSpanTaskDatabase extends RoomDatabase{

    //exposure DigitSpanTaskDao
    public abstract DigitSpanTaskDao digitSpanTaskDao();
}
