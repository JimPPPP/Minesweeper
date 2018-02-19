package com.a3.rainbow.minesweeper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView myStars = findViewById(R.id.stars);
        ImageView myPerson = findViewById(R.id.astronaut);

        setupSkipBtn();
        start();
        fadeBlink(myStars);
        rotate(myPerson);
        onBackPressed();
    }

    private void setupSkipBtn() {
        Button skip = findViewById(R.id.skip_btn);

        skip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(Welcome.this, "Skipping...", Toast.LENGTH_LONG);
                toast.show();
                stop();
            }
        });
    };

    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Welcome.this, MainMenu.class);
            startActivity(intent);
        }
    };

    private Handler myHandler = new Handler();

    public void start() {
        myHandler.postDelayed(runner, 5000);
    };

    private void stop() {
        myHandler.removeCallbacks(runner);
        Intent intent = new Intent(Welcome.this, MainMenu.class);
        startActivity(intent);
    };

    private void fadeBlink (final ImageView image) {
        final Animation FadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation FadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        Animation.AnimationListener animList = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation == FadeIn) {
                    image.startAnimation(FadeOut);
                }
                else {
                    image.startAnimation(FadeIn);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };

        FadeIn.setAnimationListener(animList);
        FadeOut.setAnimationListener(animList);
        image.startAnimation(FadeIn);
    }

    public void onBackPressed() {
        Intent close = new Intent(Intent.ACTION_MAIN);
        close.addCategory(Intent.CATEGORY_HOME);
        close.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(close);
    }

    private void rotate (final ImageView image) {
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        image.startAnimation(rotation);
    }

}

