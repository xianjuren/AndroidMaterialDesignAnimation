package com.myapplication.customView;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.utils.EvaluateUtil;


/**
 * Created by Jone on 17/2/3.
 */

public class DragLayout extends DrawerLayout {
    boolean mIsStopMOve;
    private ViewDragHelper.Callback mCallback;
    private ViewDragHelper mDragHelper;
    private ViewGroup mLeftContent;
    private ViewGroup mMainContent, mShuffleContent;
    private int mWidth;
    private int mHeight;
    private int mainLeft;
    private int mDragRange;
    private float myRange;
    private DragLayout.State mState;
    private GestureDetectorCompat gestureDetector;
    private DragLayout.OnDragStateChangeListener mOnDragStateChangeListener;

    public DragLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // gestureDetector = new GestureDetectorCompat(context, new YScrollDetector());
        this.mCallback = new ViewDragHelper.Callback() {

            /**
             * 用来决定是否可以拖动
             * @param child
             * @param pointerId
             * @return
             * 捕获子控件，返回true表示子控件可以拖动
             */
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            /**
             * 用来设置子控件将要显示的位置 [限制子控件拖动的范围]// 子控件显示的方向(horizontal, vertical)
             * @param child
             * @param left 被拖动控件的将要显示的位置
             * @param dx  位置的偏移量 = left - 当前的left
             * @return
             *
             */
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (child == mMainContent || child == mShuffleContent) {
                    left = fixLeft(left);
                }

                if (mIsStopMOve) {
                    left = 0;
                }
                return left;
            }

            /** 限定主界面的滑动范围 */
            private int fixLeft(int left) {
                if (left < 0) {
                    left = 0;
                } else if (left > mDragRange) {
                    left = mDragRange;
                }
                return left;
            }

            /**
             * 返回水平方向拖动的最大范围,返回大于0的值才可以拖动
             * 内部会根据返回值计算动画执行的时间
             * @param child
             * @return
             */
            public int getViewHorizontalDragRange(View child) {
                return mWidth;
            }

            /**
             * 位置改变时调用 [关联菜单与主界面的滑动，监听拖动状态，伴随动画]
             * @param changedView
             * @param left
             * @param top
             * @param dx
             * @param dy
             */
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {

                if (changedView == mMainContent || changedView == mShuffleContent) {
                    mainLeft = left;
                } else {
                    mainLeft = mainLeft + left;
                }
                if (mainLeft < 0) {
                    mainLeft = 0;
                } else if (mainLeft > mDragRange) {
                    mainLeft = mDragRange;
                }
                //关联子控件的滑动
                if (changedView == mLeftContent) {
                    mLeftContent.layout(0, 0, mWidth, mHeight);
                    mMainContent.layout(mainLeft, 0, mainLeft + mWidth, mHeight);
                }
                dispatchDragState(mainLeft);
                invalidate();
            }

            /**
             * 拖动结束后，松开手时调用 [平滑地打开或关闭侧滑菜单]
             * @param releasedChild
             * @param xvel 释放时的回调速度，在这里向右为正
             * @param yvel
             */
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (xvel == 0.0F && (float) mMainContent.getLeft() < (float) mDragRange * 0.5F) {
                    close();
                } else if (xvel < 0.0F) {
                    close();
                } else {
                    open();
                }

            }

        };
        myRange = 0.5F;
        mState = DragLayout.State.CLOSE;
        init();

    }


    /**
     * 控件尺寸发生改变时，回调该方法
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mLeftContent.getMeasuredWidth();
        mHeight = mLeftContent.getMeasuredHeight();
        mDragRange = (int) ((float) mWidth * myRange);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mLeftContent.layout(0, 0, mWidth, mHeight);
        mMainContent.layout(mainLeft, 0, mainLeft + mWidth, mHeight);
    }

    public void setStopSliding(boolean value) {
        mIsStopMOve = value;
    }

    public DragLayout.State getState() {
        return this.mState;
    }

    public void setState(DragLayout.State state) {
        this.mState = state;
    }

    public DragLayout.OnDragStateChangeListener getOnDragStateChangeListener() {
        return this.mOnDragStateChangeListener;
    }

    public void setOnDragStateChangeListener(DragLayout.OnDragStateChangeListener onDragStateChangeListener) {
        this.mOnDragStateChangeListener = onDragStateChangeListener;
    }

    protected void dispatchDragState(int left) {
        float percent = (float) left / (float) mDragRange;
        animViews(percent);
        DragLayout.State preState = this.mState;
        mState = updateState(percent);
        if (mOnDragStateChangeListener != null) {
            if (mState == DragLayout.State.DRAGGING) {
                mOnDragStateChangeListener.onDragging(percent);
            } else if (mState == DragLayout.State.CLOSE) {
                mOnDragStateChangeListener.onClose();
            } else if (mState == DragLayout.State.OPEN) {
                mOnDragStateChangeListener.onOpen();
            }
            if (mState != preState) {
                if (mState == DragLayout.State.CLOSE) {
                    mOnDragStateChangeListener.onClose();
                } else if (mState == DragLayout.State.OPEN) {
                    mOnDragStateChangeListener.onOpen();
                }
            }
        }

    }

    private DragLayout.State updateState(float percent) {
        return percent == 0.0F ? DragLayout.State.CLOSE : (percent == 1.0F ? DragLayout.State.OPEN : DragLayout.State.DRAGGING);
    }

    private void animViews(float percent) {

        mMainContent.setScaleX(evaluate(1f, 0.8f, percent));
        mMainContent.setScaleY(evaluate(1f, 0.8f, percent));
        mLeftContent.setScaleX(evaluate(0.5f, 1f, percent));
        mLeftContent.setScaleY(evaluate(0.5f, 1f, percent));
        mLeftContent.setTranslationX(evaluate(-mDragRange, 0f, percent));
        mLeftContent.setAlpha(EvaluateUtil.evaluateFloat(percent, Float.valueOf(0.0F), Float.valueOf(1.0F)).floatValue());
    }

    /**
     * 估值器：变化值 = 开始值 + (结束值 - 开始值) * 百分比
     *
     * @param start
     * @param end
     * @param percent
     * @return
     */
    public float evaluate(float start, float end, float percent) {
        return start + (end - start) * percent;
    }

    protected void open() {
        open(true);
    }

    public void open(boolean isSmooth) {
        if (isSmooth) {
            mDragHelper.smoothSlideViewTo(mMainContent, mDragRange, 0);
            invalidate();
        } else {
            mMainContent.layout(mDragRange, 0, mDragRange + mWidth, mHeight);
        }

    }

    protected void close() {
        this.close(true);
    }

    public void close(boolean isSmooth) {

        if (isSmooth) {
            mDragHelper.smoothSlideViewTo(this.mMainContent, 0, 0);
            invalidate();
        } else {
            mMainContent.layout(0, 0, 0 + mWidth, mHeight);
        }

    }

    private void init() {

        this.mDragHelper = ViewDragHelper.create(this, this.mCallback);
    }


    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);//&& gestureDetector.onTouchEvent(ev)
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.mDragHelper.processTouchEvent(event);
        return true;
    }


    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.getChildCount() < 2) {
            throw new RuntimeException("You must have at least 2 child views");
        } else if (this.getChildAt(0) instanceof ViewGroup && this.getChildAt(1) instanceof ViewGroup) {
            this.mLeftContent = (ViewGroup) this.getChildAt(0);
            this.mMainContent = (ViewGroup) this.getChildAt(1);
            this.mShuffleContent = (ViewGroup) this.getChildAt(2);//shuffle
        } else {
            throw new RuntimeException("Your child views must be ViewGroup");
        }

    }


    public void setDragRange(float range) {
        myRange = range;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public static enum State {
        CLOSE,
        OPEN,
        DRAGGING;

        private State() {
        }
    }

    public interface OnDragStateChangeListener {
        void onClose();

        void onOpen();

        void onDragging(float var1);
    }

    private class YScrollDetector extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceY) < Math.abs(distanceX);
        }
    }
}
