package com.design;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Jone on 17/6/14.
 */
public class SharePhotoAndTextActivity extends AppCompatActivity {
    View viewBa;
    FloatingActionButton mActionButton;
    LinearLayout mButtonLayout;
    RelativeLayout mTopLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_photo_text);
        initToolbar();
        initView();
        getWindow().setEnterTransition(getEnterAnimation());
        //共享元素的移动
        getWindow().setSharedElementEnterTransition(new ChangeBounds());
        getWindow().setReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.share_photo_text_return));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Transition getEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.share_photo_text);
        transition.addListener(new Transition.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {
                Animator animator = ViewAnimationUtils.createCircularReveal(viewBa, viewBa.getWidth() / 2, viewBa.getHeight() / 2, 0, viewBa.getWidth());
                viewBa.setBackgroundColor(Color.BLACK);
                animator.setDuration(600).start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                mActionButton.animate().scaleY(1).scaleX(1).start();
                transition.removeListener(this);
                //布局内容整体分为了上下两个布局控件，mButtonLayout,mTopLayout,在退出界面时，如果xml中没有对控件单独的处理，
                // 可以把他们作为一个整体一起执行摸个动画，可以设置setTransitionGroup,true一起执行，false是各自执行。比如在setEnterTransition
                //动画时，xml对控件都有单独处理，所以没有设置setTransitionGroup。这里我们把底部整个布局退出时一起执行，执行的动画逻辑在xml中。
                mButtonLayout.setTransitionGroup(true);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return transition;
    }

    private void initView() {
        viewBa = findViewById(R.id.image_bg);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mButtonLayout = (LinearLayout) findViewById(R.id.liney_bottom);
        mTopLayout = (RelativeLayout) findViewById(R.id.relay_flag);
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
