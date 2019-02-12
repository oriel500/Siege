package com.siege.oriel.siege;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private static boolean splashLoaded = false;


    ImageView image;
    int secondsDelayed = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        image = findViewById(R.id.imageView);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fadein);
        image.startAnimation(anim);

        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);


    }
}
