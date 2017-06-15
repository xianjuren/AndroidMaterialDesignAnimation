package com.design.scene;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.Transition;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */
public class SceneChangeBoundsActivity extends BaseSceneActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScene(R.layout.circle_move1, R.layout.circle_move2);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    Transition getTransition() {
        return new ChangeBounds();
    }
}
