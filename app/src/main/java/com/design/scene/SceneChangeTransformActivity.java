package com.design.scene;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.ChangeTransform;
import android.transition.Transition;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */
public class SceneChangeTransformActivity extends BaseSceneActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScene(R.layout.circle_scale_normal,R.layout.circle_scale_rotate);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    Transition getTransition() {
        return new ChangeTransform();
    }
}
