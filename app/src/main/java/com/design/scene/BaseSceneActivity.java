package com.design.scene;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */

public abstract class BaseSceneActivity extends AppCompatActivity {

    FrameLayout mContentLayout;
    Scene scene1, scene2;
    boolean isScene2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_scene);
        mContentLayout = (FrameLayout) findViewById(R.id.base_scene_content_layout);
    }

    public void setScene(int layoutId1, int layoutId2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scene1 = Scene.getSceneForLayout(mContentLayout, layoutId1, this);
            scene2 = Scene.getSceneForLayout(mContentLayout, layoutId2, this);
            TransitionManager.go(scene1);
        }
    }


    public void setScene(View view1, View view2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scene1 = new Scene(mContentLayout, view1);
            scene2 = new Scene(mContentLayout, view2);
            TransitionManager.go(scene1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void changeScene(View view) {
        changeTransition(getTransition());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void changeTransition(Transition transition) {
        if (null != scene1 && null != scene2) {
            TransitionManager.go(isScene2 ? scene1 : scene2, transition);
            isScene2 = !isScene2;
        }
    }

    abstract Transition getTransition();

}
