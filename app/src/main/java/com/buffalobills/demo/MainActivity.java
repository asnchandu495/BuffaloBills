package com.buffalobills.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    private ImageView imageView;
    private boolean zoomedIn = true;
    private final long animationDuration = 1000; // Adjust the duration as needed
    private final long delayBetweenAnimations = 2000;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.ImageView);
        animateImage();

//        editText = findViewById(R.id.etext);
//        textView = findViewById(R.id.textView);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {


                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast

                        System.out.println(token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                        Log.e("Tag", "" + token);


                    }
                });


    }


             // Adjust the delay between animations as needed



            private void animateImage() {
                final Handler handler = new Handler();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (zoomedIn) {
                            zoomOut();
                            zoomedIn = false;
                        } else {
                            zoomIn();
                            zoomedIn = true;
                        }
                        handler.postDelayed(this, delayBetweenAnimations);
                    }
                };

                handler.postDelayed(runnable, 0);
            }

            private void zoomIn() {
                Animation anim = new ScaleAnimation(1, 1.5f, 1, 1.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setDuration(animationDuration);
                anim.setFillAfter(true);
                imageView.startAnimation(anim);
            }

            private void zoomOut() {
                Animation anim = new ScaleAnimation(1.5f, 1, 1.5f, 1,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setDuration(animationDuration);
                anim.setFillAfter(true);
                imageView.startAnimation(anim);
            }
        }



