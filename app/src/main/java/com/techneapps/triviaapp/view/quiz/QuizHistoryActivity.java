package com.techneapps.triviaapp.view.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.room.Room;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.techneapps.triviaapp.database.QuizHistoryDatabase;
import com.techneapps.triviaapp.database.models.QuizHistory;
import com.techneapps.triviaapp.utils.QuizHistoryViewModelFactory;
import com.techneapps.triviaapp.view.adapter.QuizHistoryAdapter;
import com.techneapps.triviaapp.viewmodel.QuizHistoryViewModel;
import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.databinding.ActivityQuizHistoryBinding;

import java.util.List;

public class QuizHistoryActivity extends AppCompatActivity {

    private ActivityQuizHistoryBinding quizHistoryBinding;
    private QuizHistoryViewModel quizHistoryViewModel;
    private QuizHistoryDatabase quizHistoryDatabase;
    private QuizHistoryAdapter quizHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data binding initialization
        quizHistoryBinding= DataBindingUtil.setContentView(this, R.layout.activity_quiz_history);
        //quiz History Database initialization
        quizHistoryDatabase = Room.databaseBuilder(this, QuizHistoryDatabase.class, "quiz_history.db").build();
        initiateViews();
        initiateViewModel();
        initiateHistory();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initiateViews() {
        //set toolbar's back icon and custom title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quiz History");
    }

    private void initiateViewModel() {
        quizHistoryViewModel = ViewModelProviders.of(this, new QuizHistoryViewModelFactory(quizHistoryDatabase))
                .get(QuizHistoryViewModel.class);
    }

    private void initiateHistory() {
        //observing for data using our view model
        quizHistoryViewModel.getAllQuizHistories().observe(this, quizHistories -> {
            quizHistoryAdapter=new QuizHistoryAdapter(QuizHistoryActivity.this,quizHistories);
            quizHistoryBinding.gameHistoryView.setAdapter(quizHistoryAdapter);
        });
    }
}
