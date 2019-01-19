package com.movieratings.igorgvozdic.movies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1200;

    private ImageView imgSplash;
    private TextView txtSplashName;
    private TextView txtSplashEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashScreen();
    }

    private void splashScreen() {

        Animation aniFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_fast);
        imgSplash = findViewById(R.id.imgSplash);
        txtSplashName = findViewById(R.id.txtSplashName);
        txtSplashEmail = findViewById(R.id.txtSplashEmail);

        imgSplash.startAnimation(aniFadeIn);
        txtSplashName.startAnimation(aniFadeIn);
        txtSplashEmail.startAnimation(aniFadeIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
