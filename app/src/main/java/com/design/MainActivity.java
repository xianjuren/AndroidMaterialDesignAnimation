package com.design;

import android.content.Intent;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.PathInterpolator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Integer value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void circularReveal(View view) {
        Intent mIntent = new Intent(this, CircularRevealActivity.class);
        startActivity(mIntent);
    }


    public void transition(View view) {
        Intent mIntent = new Intent(this, TransitionActivity.class);
        startActivity(mIntent);

    }

    public void pathInterpolator(View view){
        Intent mIntent = new Intent(this, PathInterpolatorActivity.class);
        startActivity(mIntent);
    }

    public void stateList(View view){
        Intent mIntent = new Intent(this, StateListAnimatorActivity.class);
        startActivity(mIntent);
    }

    public void vector(View view){
        Intent mIntent = new Intent(this, VectorDrawableActivity.class);
        startActivity(mIntent);
    }
}
