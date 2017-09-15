package com.design;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Jone on 17/6/12.
 */

public class CircularRevealActivity extends Activity {


    ImageView ivReveal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        Button mBotton = (Button) findViewById(R.id.btn_ripple);
        ivReveal = (ImageView) findViewById(R.id.iv_reveal);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setCircularReveal(mBotton);
        } else {
            mBotton.setBackgroundResource(R.drawable.ripple);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setCircularReveal(Button mBotton) {
        int[] arrts = new int[]{android.R.attr.selectableItemBackground};
        TypedArray array = obtainStyledAttributes(arrts);
        mBotton.setBackground(array.getDrawable(0));
        array.recycle();
        ivReveal.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                //隐藏
                Animator animation = ViewAnimationUtils.createCircularReveal(ivReveal, ivReveal.getWidth() / 2,
                        ivReveal.getHeight() / 2, ivReveal.getWidth() / 2, 0);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setDuration(1500).start();
                animation.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ivReveal.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ripple(View view) {
        if (ivReveal.getVisibility() != View.VISIBLE) {
            //显示
            Animator animation = ViewAnimationUtils.createCircularReveal(ivReveal, 0, 0, 0, ivReveal.getHeight() / 2);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(1500).start();
            ivReveal.setVisibility(View.VISIBLE);
        }
    }
}
