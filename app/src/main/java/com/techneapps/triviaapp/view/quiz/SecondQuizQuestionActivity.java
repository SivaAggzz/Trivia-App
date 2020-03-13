package com.techneapps.triviaapp.view.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.database.QuizHistoryDatabase;
import com.techneapps.triviaapp.database.models.QuizHistory;
import com.techneapps.triviaapp.databinding.ActivitySecondQuizQuestionBinding;
import com.techneapps.triviaapp.utils.OnOperationDoneCallback;
import com.techneapps.triviaapp.utils.QuizHistoryViewModelFactory;
import com.techneapps.triviaapp.viewmodel.QuizHistoryViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class SecondQuizQuestionActivity extends AppCompatActivity {
    private ActivitySecondQuizQuestionBinding secondQuizQuestionBinding;
    private QuizHistoryViewModel quizHistoryViewModel;
    private QuizHistoryDatabase quizHistoryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data binding initialization
        secondQuizQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_second_quiz_question);
        initializeViewModel();
        initializeViews();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }

    private void initializeViewModel() {
        //quiz History Database initialization
        quizHistoryDatabase = Room.databaseBuilder(this, QuizHistoryDatabase.class, "quiz_history.db").build();
        quizHistoryViewModel = ViewModelProviders.of(this, new QuizHistoryViewModelFactory(quizHistoryDatabase))
                .get(QuizHistoryViewModel.class);
    }

    private void initializeViews() {
        //setting setOnCheckedChangeListener to toggle next button state accordingly
        secondQuizQuestionBinding.radioOption1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            secondQuizQuestionBinding.radioOption1.setChecked(isChecked);
            if (isChecked) {
                enableNextButton();
            } else {
                if (secondQuizQuestionBinding.radioOption2.isChecked()
                        || secondQuizQuestionBinding.radioOption3.isChecked()
                        || secondQuizQuestionBinding.radioOption4.isChecked()) {
                    enableNextButton();
                } else {
                    disableNextButton();
                }
            }
        });

        secondQuizQuestionBinding.radioOption2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            secondQuizQuestionBinding.radioOption2.setChecked(isChecked);
            if (isChecked) {
                enableNextButton();
            } else {
                if (secondQuizQuestionBinding.radioOption1.isChecked()
                        || secondQuizQuestionBinding.radioOption3.isChecked()
                        || secondQuizQuestionBinding.radioOption4.isChecked()) {
                    enableNextButton();
                } else {
                    disableNextButton();
                }
            }
        });

        secondQuizQuestionBinding.radioOption3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            secondQuizQuestionBinding.radioOption3.setChecked(isChecked);
            if (isChecked) {
                enableNextButton();
            } else {
                if (secondQuizQuestionBinding.radioOption1.isChecked()
                        || secondQuizQuestionBinding.radioOption2.isChecked()
                        || secondQuizQuestionBinding.radioOption4.isChecked()) {
                    enableNextButton();
                } else {
                    disableNextButton();
                }
            }
        });

        secondQuizQuestionBinding.radioOption4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            secondQuizQuestionBinding.radioOption4.setChecked(isChecked);
            if (isChecked) {
                enableNextButton();
            } else {
                if (secondQuizQuestionBinding.radioOption1.isChecked()
                        || secondQuizQuestionBinding.radioOption2.isChecked()
                        || secondQuizQuestionBinding.radioOption3.isChecked()) {
                    enableNextButton();
                } else {
                    disableNextButton();
                }
            }
        });

    }

    private void disableNextButton() {
        secondQuizQuestionBinding.nextBtn.setEnabled(false);
    }

    private void enableNextButton() {
        secondQuizQuestionBinding.nextBtn.setEnabled(true);
    }

    public void goToNextPage(View view) {
        //called when user press the next button
        if (validateUserAnswerSelection()) {
            QuizHistory currentQuizHistoryObject = getCurrentQuizHistoryObject();
            quizHistoryViewModel.addQuizHistory(currentQuizHistoryObject, (resultID) -> {
                Intent showResultIntent = new Intent(SecondQuizQuestionActivity.this, QuizResultActivity.class);
                showResultIntent.putExtra("currentQuizHistoryID", resultID);
                startActivity(showResultIntent);
            });

        }
    }

    private QuizHistory getCurrentQuizHistoryObject() {
        //method which creates a QuizHistory object , using the data entered by the user
        QuizHistory quizHistory = new QuizHistory();
        //this attributes are already passed to this activity by previous activities,so just get them
        quizHistory.setUserName(Objects.requireNonNull(getIntent().getExtras()).getString("userName"));
        //this attribute is not passed previously,so get currentTimeMillis
        quizHistory.setTimeStamp(System.currentTimeMillis());

        //this attributes are already passed to this activity by previous activities,so just get them
        quizHistory.setQuizQuestion1(Objects.requireNonNull(getIntent().getExtras()).getString("question1"));
        quizHistory.setQuizAnswered1(Objects.requireNonNull(getIntent().getExtras()).getString("answer1"));

        quizHistory.setQuizQuestion2(secondQuizQuestionBinding.quizSecondQuestionTextView.getText().toString().trim());
        quizHistory.setQuizAnswered2(getSelectedAnswer());
        return quizHistory;
    }

    private String getSelectedAnswer() {
        //get the values which user has selected
        ArrayList<String> selectedItem = new ArrayList<>();
        if (secondQuizQuestionBinding.radioOption1.isChecked()) {
            // answer+=secondQuizQuestionBinding.radioOption1.getText().toString();
            selectedItem.add(secondQuizQuestionBinding.radioOption1.getText().toString());
        }
        if (secondQuizQuestionBinding.radioOption2.isChecked()) {
            //  answer+=","+secondQuizQuestionBinding.radioOption2.getText().toString();
            selectedItem.add(secondQuizQuestionBinding.radioOption2.getText().toString());
        }
        if (secondQuizQuestionBinding.radioOption3.isChecked()) {
            // answer+=","+secondQuizQuestionBinding.radioOption3.getText().toString();
            selectedItem.add(secondQuizQuestionBinding.radioOption3.getText().toString());
        }
        if (secondQuizQuestionBinding.radioOption4.isChecked()) {
            // answer+=","+secondQuizQuestionBinding.radioOption4.getText().toString();
            selectedItem.add(secondQuizQuestionBinding.radioOption4.getText().toString());
        }
        return Collections.singletonList(selectedItem).toString().replace("[", "").replace("]", "");
    }

    private boolean validateUserAnswerSelection() {
        //validate if user has selected any option
        if (!isAnyOptionSelectedByUser()) {
            Toast.makeText(this, "Please select your answer before continuing.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isAnyOptionSelectedByUser() {
        //validate if user has selected any option
        if (secondQuizQuestionBinding.radioOption1.isChecked()) {
            return true;
        } else if (secondQuizQuestionBinding.radioOption2.isChecked()) {
            return true;
        } else if (secondQuizQuestionBinding.radioOption3.isChecked()) {
            return true;
        } else return secondQuizQuestionBinding.radioOption4.isChecked();
    }


}
