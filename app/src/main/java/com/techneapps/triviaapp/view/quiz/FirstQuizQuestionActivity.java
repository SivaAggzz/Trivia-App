package com.techneapps.triviaapp.view.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.databinding.ActivityFirstQuizQuestionBinding;

import java.util.Objects;

public class FirstQuizQuestionActivity extends AppCompatActivity {
    private ActivityFirstQuizQuestionBinding firstQuizQuestionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data binding initialization
        firstQuizQuestionBinding = DataBindingUtil.setContentView(this, R.layout.activity_first_quiz_question);
        initializeViews();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }

    private void initializeViews() {
        //setting setOnCheckedChangeListener to toggle next button state accordingly
        firstQuizQuestionBinding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (group.getCheckedRadioButtonId()==-1){
                firstQuizQuestionBinding.nextBtn.setEnabled(false);
            }else {
                firstQuizQuestionBinding.nextBtn.setEnabled(true);
            }
        });
    }

    public void goToNextPage(View view) {
        //called when user press the next button
        if (validateUserAnswerSelection()) {
            Intent secondQuizIntent = new Intent(this, SecondQuizQuestionActivity.class);
            secondQuizIntent.putExtra("userName", Objects.requireNonNull(getIntent().getExtras()).getString("userName"));
            secondQuizIntent.putExtra("question1", firstQuizQuestionBinding.quizFirstQuestionTextView.getText().toString().trim());
            secondQuizIntent.putExtra("answer1", getSelectedAnswer());
            startActivity(secondQuizIntent);
        }
    }

    private String getSelectedAnswer() {
        //get the value which user has selected
        int checkedRadioButtonId = firstQuizQuestionBinding.radioGroup.getCheckedRadioButtonId();
        RadioButton checkedBtn = firstQuizQuestionBinding.getRoot().findViewById(checkedRadioButtonId);
        return checkedBtn.getText().toString();

    }

    private boolean validateUserAnswerSelection() {
        //validate if user has selected any option
        if (firstQuizQuestionBinding.radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your answer before continuing.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
