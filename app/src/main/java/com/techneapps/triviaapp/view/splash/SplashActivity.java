package com.techneapps.triviaapp.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.techneapps.triviaapp.R;
import com.techneapps.triviaapp.view.user.EnterUserNameActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //wait for 1000 ms and then start the main activity
        new Handler().postDelayed(() -> {
            finish();
            startActivity(new Intent(getApplicationContext(), EnterUserNameActivity.class));
        },1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }
}
