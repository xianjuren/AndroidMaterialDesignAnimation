package com.design;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jone on 17/6/14.
 */
public class VectorDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vetor_drawable);
        ImageView circleImageView = (ImageView) findViewById(R.id.vetor_circle);
        ImageView redImageView = (ImageView) findViewById(R.id.red_circle);
        Drawable circleDrawable = redImageView.getDrawable();
        Drawable drawable = circleImageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        if (circleDrawable instanceof Animatable) {
            ((Animatable) circleDrawable).start();
        }
    }
}
