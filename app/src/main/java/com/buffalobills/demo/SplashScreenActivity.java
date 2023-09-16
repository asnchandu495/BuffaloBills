package com.buffalobills.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;


public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2, imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
            } ,7000);

        // Start the initial zoom in and out animation for the ImageView
            imageView2.animate()
                    .scaleX(1.4f)
                    .scaleY(1.4f)
                    .setDuration(2000)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            // Hide the ImageView after zoom animation
                            imageView2.setScaleX(1f);
                            imageView2.setScaleY(1f);
                            // Slide in TextView1 from the left
                            slideInFromLeft(imageView1);

                            // Slide in TextView2 from the right
                            slideInFromRight(imageView3);
                        }
                    });
        }

        private void slideInFromLeft(View view) {
            view.setVisibility(View.VISIBLE);
            view.setAlpha(0f);
            view.setTranslationX(-view.getWidth());
            view.animate()
                    .alpha(1f)
                    .translationX(0)
                    .setDuration(1000)
                    .setInterpolator(new AccelerateDecelerateInterpolator());
        }

        private void slideInFromRight(View view) {
            view.setVisibility(View.VISIBLE);
            view.setAlpha(0f);
            view.setTranslationX(view.getWidth());
            view.animate()
                    .alpha(1f)
                    .translationX(0)
                    .setDuration(1000)
                    .setInterpolator(new AccelerateDecelerateInterpolator());
        }
    }

