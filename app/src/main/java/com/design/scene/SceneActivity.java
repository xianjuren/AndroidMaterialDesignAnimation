package com.design.scene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */
public class SceneActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_manager);
    }

    public void changeBounds(View view){
        startActivity(new Intent(this, SceneChangeBoundsActivity.class));
    }
    public void changeTransform(View view){
        startActivity(new Intent(this, SceneChangeTransformActivity.class));
    }
    public void changeClipBounds(View view){
        startActivity(new Intent(this, SceneChangeClipBoundsActivity.class));
    }
    public void changeImageTransform(View view){
        startActivity(new Intent(this, SceneChangeImageTransformActivity.class));
    }
    public void fade(View view){
        startActivity(new Intent(this, SceneFadeActivity.class));
    }

    public void slide(View view){
        startActivity(new Intent(this, SceneSlideActivity.class));
    }

    public void explode(View view){
        startActivity(new Intent(this, SceneExplodeActivity.class));
    }

}
