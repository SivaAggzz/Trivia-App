package com.techneapps.triviaapp.repo;

import androidx.lifecycle.MutableLiveData;


import com.techneapps.triviaapp.database.QuizHistoryDatabase;
import com.techneapps.triviaapp.database.models.QuizHistory;
import com.techneapps.triviaapp.utils.OnOperationDoneCallback;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class QuizHistoryRepository {
    //Data to provide to View
    private MutableLiveData<List<QuizHistory>> quizHistories;
    //Data source
    private final QuizHistoryDatabase quizHistoryDatabase;

    public QuizHistoryRepository(QuizHistoryDatabase quizHistoryDatabase) {
        this.quizHistoryDatabase = quizHistoryDatabase;
    }

    //method to return all saved QuizHistory
    public MutableLiveData<List<QuizHistory>> getQuizHistories() {
        if (quizHistories == null) {
            quizHistories = new MutableLiveData<>();
        }
        return getQuizHistoriesThread();
    }

    //method to get all saved QuizHistory from Room database by using RXJava
    private MutableLiveData<List<QuizHistory>> getQuizHistoriesThread() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Observable.fromCallable(() -> quizHistoryDatabase.getQuizHistoryDao().getQuizHistory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> quizHistories.setValue(result)));
        return quizHistories;
    }
    //method to get a saved QuizHistory by id from Room database by using RXJava
    public MutableLiveData<QuizHistory> getQuizHistoryByID(long quizID) {
        MutableLiveData<QuizHistory> quizHistoryMutableLiveData=new MutableLiveData<>();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Observable.fromCallable(() -> quizHistoryDatabase.getQuizHistoryDao().getQuizHistoryById(quizID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quizHistoryMutableLiveData::setValue));
        return quizHistoryMutableLiveData;
    }
// --Commented out by Inspection START (14/03/20, 1:37 AM):
//    //method to delete all saved QuizHistory from Room database by using RXJava
//    public void clearQuizHistories() {
//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//        compositeDisposable.add(Observable.fromCallable(() -> {
//            quizHistoryDatabase.clearAllTables();
//            return true;
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((result) -> {
//                }));
//    }
// --Commented out by Inspection STOP (14/03/20, 1:37 AM)

// --Commented out by Inspection START (14/03/20, 1:37 AM):
//    //method to delete a saved QuizHistory from Room database by using RXJava
//    public void deleteQuizHistory(QuizHistory quizHistory) {
//
//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//        compositeDisposable.add(Observable.fromCallable(() -> {
//            quizHistoryDatabase.getQuizHistoryDao().deleteQuizHistory(quizHistory);
//            return true;
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((result) -> {
//                }));
//    }
// --Commented out by Inspection STOP (14/03/20, 1:37 AM)

// --Commented out by Inspection START (14/03/20, 1:37 AM):
//    //method to update QuizHistory to Room database by using RXJava
//    public void updateQuizHistory(QuizHistory quizHistory) {
//
//        CompositeDisposable compositeDisposable = new CompositeDisposable();
//        compositeDisposable.add(Observable.fromCallable(() -> {
//            quizHistoryDatabase.getQuizHistoryDao().updateQuizHistory(quizHistory);
//            return true;
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((result) -> {
//
//                }));
//    }
// --Commented out by Inspection STOP (14/03/20, 1:37 AM)

    //method to add QuizHistory to Room database by using RXJava
    public void addQuizHistory(QuizHistory quizHistory, OnOperationDoneCallback onOperationDoneCallback) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable
                .add(Observable.fromCallable(() -> quizHistoryDatabase.getQuizHistoryDao().addQuizHistory(quizHistory))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onOperationDoneCallback::OnOperationDone));
    }


}
