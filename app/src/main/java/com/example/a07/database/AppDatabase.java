package com.example.a07.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.a07.dao.QuesDao;
import com.example.a07.entity.QuestionaireEntity;


@Database(entities = {QuestionaireEntity.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    // exposure QuesDao
    public abstract QuesDao quesDao();


}
