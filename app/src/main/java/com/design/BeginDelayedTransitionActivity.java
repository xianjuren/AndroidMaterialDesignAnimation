package com.design;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jone on 17/6/12.
 */

public class BeginDelayedTransitionActivity extends Activity implements View.OnClickListener {
    RelativeLayout mRootView;
    private CircleImageView cuteboy, cutegirl, hxy, lly;
    private boolean isImageBigger;
    private int primarySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beigin_delayed_transition);
        initView();
    }

    private void initView() {
        mRootView = (RelativeLayout) findViewById(R.id.scene_root);
        cuteboy = (CircleImageView) findViewById(R.id.cuteboy);
        cutegirl = (CircleImageView) findViewById(R.id.cutegirl);
        hxy = (CircleImageView) findViewById(R.id.hxy);
        lly = (CircleImageView) findViewById(R.id.lly);
        cuteboy.post(new Runnable() {
            @Override
            public void run() {
                primarySize = cuteboy.getLayoutParams().width;
            }
        });
        cuteboy.setOnClickListener(this);
        cutegirl.setOnClickListener(this);
        hxy.setOnClickListener(this);
        lly.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(mRootView, TransitionInflater.from(this).inflateTransition(R.transition.explode_and_changebounds));
        changeView(v);
    }

    private void changeView(View v) {
        changViewSize(v);
        changeViewVisibility(cuteboy, cutegirl, lly, hxy);
        v.setVisibility(View.VISIBLE);
    }

    private void changeViewVisibility(View... views) {

        for (View view : views) {
            view.setVisibility(view.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        }
    }

    /**
     * view的宽高1.5倍和原尺寸大小切换
     * 配合ChangeBounds实现缩放效果
     *
     * @param
     */
    private void changViewSize(View v) {
        isImageBigger = !isImageBigger;
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (isImageBigger) {
            layoutParams.width = (int) (1.5 * primarySize);
            layoutParams.height = (int) (1.5 * primarySize);

        } else {
            layoutParams.width = primarySize;
            layoutParams.height = primarySize;
        }
        v.setLayoutParams(layoutParams);
    }
}
