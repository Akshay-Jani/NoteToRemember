package com.example.akshay.notetoremember;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_ACTIVITY_TIMER = 5000;
    ImageView imgLogo;
    TextView tvAppName;
    Animation animation1, animation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        imgLogo = findViewById(R.id.imgLogo);
        tvAppName = findViewById(R.id.tvAppName);

        animation1 = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_out);
        animation2 = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.slide_up);

        imgLogo.startAnimation(animation1);
        tvAppName.startAnimation(animation2);

        SplashLauncher launcher = new SplashLauncher();
        launcher.start();
    }

    class SplashLauncher extends Thread{
        public void run(){
            try {
                sleep(SPLASH_ACTIVITY_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            SplashActivity.this.finish();
        }
    }
}
