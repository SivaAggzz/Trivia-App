package com.techneapps.triviaapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.techneapps.triviaapp.database.models.QuizHistory;

import java.util.List;

@Dao
public interface QuizHistoryDao {
    /*
    * DAO INTERFACE FOR ADDING,DELETING,UPDATING QUIZ HISTORY FROM ANDROID ROOM
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addQuizHistory(QuizHistory quizHistory);

    @Delete
    void deleteQuizHistory(QuizHistory quizHistory);

    @Update
    void updateQuizHistory(QuizHistory quizHistory);


    @Query("select * from quiz_history")
    List<QuizHistory> getQuizHistory();

    @Query("select * from quiz_history WHERE quiz_id == :id")
    QuizHistory getQuizHistoryById(long id);


// --Commented out by Inspection START (14/03/20, 1:11 AM):
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertMultipleQuizHistory(List<QuizHistory> quizHistories);
// --Commented out by Inspection STOP (14/03/20, 1:11 AM)


}
