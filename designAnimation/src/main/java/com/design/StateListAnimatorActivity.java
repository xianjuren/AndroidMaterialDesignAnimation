package com.design;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Jone on 17/6/14.
 */
public class StateListAnimatorActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);
        Button mButton = (Button) findViewById(R.id.btn_more_state);
        /**
         * 参数1：当前对象的引用
         * 参数2：定义的动画
         */
        StateListAnimator animator = AnimatorInflater.loadStateListAnimator(this, R.drawable.select_state_scale);
        mButton.setStateListAnimator(animator);


    }
}
