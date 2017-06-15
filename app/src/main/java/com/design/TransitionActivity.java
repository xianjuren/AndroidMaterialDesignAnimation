package com.design;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.design.scene.SceneActivity;

import static android.view.Window.FEATURE_CONTENT_TRANSITIONS;

/**
 * Created by Jone on 17/6/12.
 */

public class TransitionActivity extends Activity {

    Button mShareName, mShareMoreName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_transition);
        mShareName = (Button) findViewById(R.id.btn_activityOptions);
        mShareMoreName = (Button) findViewById(R.id.shareMoreName);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void explode(View view) {
        startActivity(new Intent(this, ExplodeActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void slide(View view) {
        startActivity(new Intent(this, SlideActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void fade(View view) {
        startActivity(new Intent(this, FadeActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void activityOptions(View view) {
        Intent mIntent = new Intent(this, ShareElementActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, "shareName");
        startActivity(mIntent, options.toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void shareMoreName(View view) {
        Intent mIntent = new Intent(this, ShareMoreElementActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                (this, Pair.create((View) mShareName, "shareName"), Pair.create((View) mShareMoreName, "shareMoreName"));
        startActivity(mIntent, options.toBundle());
    }


    public void go(View view) {
        startActivity(new Intent(this, SceneActivity.class));
    }

    public void beginDelayed(View view) {
        startActivity(new Intent(this, BeginDelayedTransitionActivity.class));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void contentTransition(View view) {
        startActivity(new Intent(this, ContentTransitionActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void frAc(View view) {
        startActivity(new Intent(this, FragmentAndActivity.class));
    }

}
