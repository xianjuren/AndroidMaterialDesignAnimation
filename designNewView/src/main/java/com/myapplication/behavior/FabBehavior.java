package com.myapplication.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by Jone on 17/6/16.
 */

public class FabBehavior extends CoordinatorLayout.Behavior<View> {

    Interpolator mInterpolator = new FastOutSlowInInterpolator();
    private float mDistanceY;
    private boolean isAnimate;//动画是否在进行

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild 需要与
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //返回true,才能接受后续滑动事件。// 确保是竖直判断的滚动
        if (child.getVisibility() == View.VISIBLE && mDistanceY == 0) {
            mDistanceY = coordinatorLayout.getHeight() - child.getY();
        }
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }



    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //滑动事件处理,
        //dyConsumed大于0是向上滚动 小于0是向下滚动下滑显示和上滑隐藏

        if (dyConsumed > 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (dyConsumed < 0 && !isAnimate && child.getVisibility() != View.VISIBLE) {
            show(child);
        }

    }

    private void show(final View child) {
        ViewPropertyAnimator animator = child.animate().translationY(0).setInterpolator(mInterpolator).setDuration(300);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                child.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                hide(child);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void hide(final View child) {
        ViewPropertyAnimator animator = child.animate().translationY(mDistanceY).setInterpolator(mInterpolator).setDuration(300);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                child.setVisibility(View.INVISIBLE);//这里不能设置成GONE,不然隐藏后们无法在显示，版本design25的bug。
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                show(child);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

}
