package com.myapplication.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.library.StatusBarUtil;
import com.myapplication.R;
import com.myapplication.adpter.ViewPagerAdapter;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Jone on 17/6/19.
 */
public class XiTuActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final CoordinatorLayout mRootView = (CoordinatorLayout) findViewById(R.id.root_layout);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        final LinearLayout mHeadLayout = (LinearLayout) findViewById(R.id.head_layout);
        final CollapsingToolbarLayout mCollapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        /**
         * Add a listener that will be called when the offset of this {@link AppBarLayout} changes.
         */
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            /**
             *  @param appBarLayout the {@link AppBarLayout} which offset has changed
             * @param verticalOffset the vertical offset for the parent {@link AppBarLayout}, in px
             */
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mHeadLayout.getHeight() / 2) {
                    mCollapsing.setTitle("涩郎");
                } else {
                    mCollapsing.setTitle("");
                }
            }
        });

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.main_vp_container);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        setBackground(mRootView, mHeadLayout, mCollapsing);

    }

    private void setBackground(final CoordinatorLayout mRootView, final LinearLayout mHeadLayout, final CollapsingToolbarLayout mCollapsing) {
        ImageView head_iv = (ImageView) findViewById(R.id.head_iv);
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new RoundedCornersTransformation(this, 90, 0)).into(head_iv);
        //设置沉浸状态栏
        StatusBarUtil.setTranslucent(this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        //设置渐变的背景色
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100)).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                //标题工具栏停留在顶部时候背景的设置，覆盖原来mHeadLayout内容颜色,达到内容颜色隐藏的功效
                mCollapsing.setContentScrim(resource);
            }
        });
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100)).into(new SimpleTarget<GlideDrawable>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                //主内容的颜色
                mHeadLayout.setBackground(resource);
                //设置状态栏的颜色
                mRootView.setBackground(resource);
                //不能把  mCollapsing.setContentScrim(resource);写在同一个方法中，不然内容颜色会覆盖mHeadLayout内容颜色
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xitu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.webview:
                msg += "博客跳转";
                break;
            case R.id.weibo:
                msg += "微博跳转";
                break;
            case R.id.action_settings:
                msg += "设置";
                break;
        }
        if (!msg.equals("")) {
            Toast.makeText(XiTuActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
