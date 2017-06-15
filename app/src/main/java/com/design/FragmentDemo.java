package com.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Jone on 17/6/14.
 */
public class FragmentDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demo);
        FragmentA fragmentA = new FragmentA();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, fragmentA).commit();

    }
}
