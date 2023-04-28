package com.example.a07;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {

    LottieAnimationView animationView;
    @SuppressLint("StaticFieldLeak")
    public static Activity SplashActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_introductory);
        SplashActivity = IntroductoryActivity.this;

        animationView = findViewById(R.id.animationView);

        animationView.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(IntroductoryActivity.this, MainActivity.class);
            startActivity(intent);
        },3900);
    }
}