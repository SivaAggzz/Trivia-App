package com.techneapps.triviaapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.techneapps.triviaapp.database.QuizHistoryDatabase;
import com.techneapps.triviaapp.database.dao.QuizHistoryDao;
import com.techneapps.triviaapp.database.models.QuizHistory;
import com.techneapps.triviaapp.repo.QuizHistoryRepository;
import com.techneapps.triviaapp.utils.OnOperationDoneCallback;

import java.util.ArrayList;
import java.util.List;

public class QuizHistoryViewModel extends ViewModel {
    //Data to provide to View
    private MutableLiveData<List<QuizHistory>> quizHistoriesMutableLiveData;
    //Our Repo
    private QuizHistoryRepository quizHistoryRepository;

    public QuizHistoryViewModel(QuizHistoryDatabase quizHistoryDatabase) {
        quizHistoryRepository = new QuizHistoryRepository(quizHistoryDatabase);
        quizHistoriesMutableLiveData = quizHistoryRepository.getQuizHistories();
    }


    //method to get all saved QuizHistories from Room database using RXJava
    public MutableLiveData<List<QuizHistory>> getAllQuizHistories() {
        return quizHistoryRepository.getQuizHistories();
    }
    //method to get a saved QuizHistories by id from Room database using RXJava
    public MutableLiveData<QuizHistory> getQuizHistoryByID(long quizID) {
        return quizHistoryRepository.getQuizHistoryByID(quizID);
    }
    //method to delete all saved QuizHistories from Room database using RXJava
    public void clearQuizHistory() {
        quizHistoryRepository.clearQuizHistories();
    }

    //method to delete a saved QuizHistory from Room database using RXJava
    private void deleteQuizHistory(QuizHistory quizHistory) {
        quizHistoryRepository.deleteQuizHistory(quizHistory);
    }

    //method to update QuizHistory to Room database using RXJava
    public void updateQuizHistory(QuizHistory quizHistory) {
        quizHistoryRepository.updateQuizHistory(quizHistory);
    }

    //method to add QuizHistory to Room database using RXJava
    public void addQuizHistory(QuizHistory quizHistory, OnOperationDoneCallback onOperationDoneCallback) {
        quizHistoryRepository.addQuizHistory(quizHistory,onOperationDoneCallback);
    }
}
