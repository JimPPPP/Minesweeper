package com.a3.rainbow.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final ImageView myStars = findViewById(R.id.stars);
        final ImageView myPerson = findViewById(R.id.astronaut);

        fadeBlink(myStars);
        FadeIn(myPerson);
        rotate(myPerson);
    }

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

    private void FadeIn (final ImageView image) {
        Animation fadeStart = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        image.startAnimation(fadeStart);
    }

    private void rotate (final ImageView image) {
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        image.startAnimation(rotation);
    }

}

