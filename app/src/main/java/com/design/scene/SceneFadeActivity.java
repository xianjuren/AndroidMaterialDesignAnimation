package com.design.scene;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */
public class SceneFadeActivity extends BaseSceneActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScene(R.layout.circle_fade_visibility,R.layout.circle_fade_gone);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    Transition getTransition() {
        return new Fade();
    }
}
