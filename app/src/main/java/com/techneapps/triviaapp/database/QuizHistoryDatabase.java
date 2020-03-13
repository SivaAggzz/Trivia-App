package com.techneapps.triviaapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.techneapps.triviaapp.database.dao.QuizHistoryDao;
import com.techneapps.triviaapp.database.models.QuizHistory;


@Database(entities = {QuizHistory.class}, version = 1)
public abstract class QuizHistoryDatabase extends RoomDatabase {
    /*
     * abstract class to get QuizHistoryDao
     */
    public abstract QuizHistoryDao getQuizHistoryDao();
}
