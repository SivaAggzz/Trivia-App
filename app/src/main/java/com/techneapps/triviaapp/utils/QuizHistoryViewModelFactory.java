package com.techneapps.triviaapp.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.techneapps.triviaapp.database.QuizHistoryDatabase;
import com.techneapps.triviaapp.viewmodel.QuizHistoryViewModel;

public class QuizHistoryViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    /*
     * custom ViewModelFactory for our QuizHistoryViewModel with custom parameters
     * such as QuizHistoryDatabase
     */
    private final QuizHistoryDatabase quizHistoryDatabase;

    public QuizHistoryViewModelFactory(QuizHistoryDatabase quizHistoryDatabase) {
        this.quizHistoryDatabase = quizHistoryDatabase;
    }


    @SuppressWarnings("unchecked")
    // This would be helpful for lint warnings for casts.
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == QuizHistoryViewModel.class) {
            return (T) new QuizHistoryViewModel(quizHistoryDatabase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
