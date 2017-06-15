package com.design.scene;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */
public class SceneSlideActivity extends BaseSceneActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScene(R.layout.circle_explode_fade_slide,R.layout.circle_explode_fade_slide_invisibility);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    Transition getTransition() {

        return new Slide();
    }
}
