package com.gkpoter.maptt;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.gkpoter.maptt.ui.BaseActivity;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view=findViewById(R.id.view);

        ObjectAnimator animator1=ObjectAnimator.ofFloat(view,"rotation", 0,360F);
        AnimatorSet set=new AnimatorSet();

        set.play(animator1);
        set.setDuration(1000);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(MainActivity.this, BaseActivity.class));
                finish();
            }
        });
    }

}
