package com.design;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.PathInterpolator;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jone on 17/6/14.
 */
public class PathInterpolatorActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_interpolator);
        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.circle_path);
        Path path = new Path();
        path.moveTo(100, 100);
        path.quadTo(1000, 300, 300, 700);
        ObjectAnimator animator = ObjectAnimator.ofFloat(circleImageView, View.X, View.Y, path);
//        Path p = new Path();
//        p.lineTo(0.6f, 0.9f);
//        p.lineTo(0.75f, 0.2f);
//        p.lineTo(1f,1f);
//        animator.setInterpolator(new PathInterpolator(p));
        animator.setDuration(3000);
        animator.start();
    }
}
