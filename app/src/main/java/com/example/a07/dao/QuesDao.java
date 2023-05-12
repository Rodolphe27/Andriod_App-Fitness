package com.example.a07.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.a07.entity.QuestionaireEntity;

import java.util.List;

@Dao
public interface QuesDao {

    @Insert
    void insert(QuestionaireEntity... quesEntity);


    // query All Questionaires(Default:   table name = entity name)
    @Query("SELECT * FROM QuestionaireEntity")
    List<QuestionaireEntity> queryAll();


    // clear the "QuestionaireEntity" table
    @Query("DELETE FROM QuestionaireEntity")
    void deleteAll();
}
