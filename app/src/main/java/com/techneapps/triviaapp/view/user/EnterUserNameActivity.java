package com.techneapps.triviaapp.view.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.databinding.ActivityEnterUserNameBinding;
import com.techneapps.triviaapp.view.quiz.FirstQuizQuestionActivity;

public class EnterUserNameActivity extends AppCompatActivity {

    private ActivityEnterUserNameBinding enterUserNameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //data binding initialization
        enterUserNameBinding = DataBindingUtil.setContentView(this, R.layout.activity_enter_user_name);
        initializeViews();
    }



    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }

    private void initializeViews() {
        //addTextChangedListener added to toggle button state accordingly
        enterUserNameBinding.nameEditText.addTextChangedListener(userNameTextWatcher);
    }

    public void goToNextPage(View view) {
        //called when user press the next button
        if (validateUserName()) {
            Intent firstQuizIntent = new Intent(this, FirstQuizQuestionActivity.class);
            firstQuizIntent.putExtra("userName", getEnteredUserName());
            startActivity(firstQuizIntent);
        }
    }

    private String getEnteredUserName() {
        //returns the text entered by the user
        return enterUserNameBinding.nameEditText.getText().toString().trim();
    }

    private boolean validateUserName() {
        //check if username is valid
        if (!TextUtils.isEmpty(enterUserNameBinding.nameEditText.getText().toString().trim())) {
            enterUserNameBinding.nameEditText.setError(null);
            return true;
        } else {
            enterUserNameBinding.nameEditText.setError("Enter name");
            return false;
        }
    }


    private final TextWatcher userNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(enterUserNameBinding.nameEditText.getText().toString().trim())) {
                enterUserNameBinding.nextBtn.setEnabled(false);
            } else {
                enterUserNameBinding.nextBtn.setEnabled(true);
            }
        }
    };

}
