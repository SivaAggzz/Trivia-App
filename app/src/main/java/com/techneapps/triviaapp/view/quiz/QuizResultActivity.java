package com.techneapps.triviaapp.view.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.database.QuizHistoryDatabase;
import com.techneapps.triviaapp.databinding.ActivityQuizResultBinding;
import com.techneapps.triviaapp.utils.QuizHistoryViewModelFactory;
import com.techneapps.triviaapp.view.user.EnterUserNameActivity;
import com.techneapps.triviaapp.viewmodel.QuizHistoryViewModel;

import java.util.Objects;

public class QuizResultActivity extends AppCompatActivity {

    private ActivityQuizResultBinding quizResultBinding;
    private QuizHistoryViewModel quizHistoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data binding initialization
        quizResultBinding= DataBindingUtil.setContentView(this, R.layout.activity_quiz_result);
        //set toolbar's custom title
        Objects.requireNonNull(getSupportActionBar()).setTitle("Summary");
        initializeViewModel();
        initializeResult();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }
    private void initializeViewModel() {
        //quiz History Database initialization
        QuizHistoryDatabase quizHistoryDatabase = Room.databaseBuilder(this, QuizHistoryDatabase.class, "quiz_history.db").build();
        quizHistoryViewModel = new ViewModelProvider(this, new QuizHistoryViewModelFactory(quizHistoryDatabase))
                .get(QuizHistoryViewModel.class);
    }

    private void initializeResult() {
        //get id of quiz history which user just added to database
        long currentQuizHistoryID = (long)Objects.requireNonNull(getIntent().getExtras()).get("currentQuizHistoryID");
        quizHistoryViewModel.getQuizHistoryByID(currentQuizHistoryID).observe(this, quizHistory -> quizResultBinding.setQuizHistory(quizHistory));

    }


    public void restartGame(View view){
        //called when user press the finish button
        Intent restartGameIntent=new Intent(this, EnterUserNameActivity.class);
        //clearing all the instances of previously accessed activities
        restartGameIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(restartGameIntent);
    }

    public void showHistory(View view){
        //called when user press the history button
        Intent historyIntent=new Intent(this,QuizHistoryActivity.class);
        startActivity(historyIntent);

    }
}
