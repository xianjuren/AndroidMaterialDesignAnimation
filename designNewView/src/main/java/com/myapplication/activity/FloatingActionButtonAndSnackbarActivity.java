package com.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myapplication.bean.AText;
import com.myapplication.R;
import com.myapplication.adpter.ATextAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jone on 17/6/16.
 */
public class FloatingActionButtonAndSnackbarActivity extends AppCompatActivity {

    Snackbar mSnackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rl_floating_button);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ATextAdapter mATextAdapter = new ATextAdapter();
        mRecyclerView.setAdapter(mATextAdapter);
        List<AText> mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            AText mAText = new AText();
            mAText.setName("name:银河星系：" + i);
            mList.add(mAText);
        }
        mATextAdapter.setData(mList);
    }


    public void floating(View view) {
        //需要想要FloatingActionButton随着Snackbar的出现(消失)自动上升(降落)，在xml布局中添加的layout_behavior必须是直接继承FloatingActionButton.Behavior
        //如果直接继承的是CoordinatorLayout.Behavior<View>，是没有自动效果的，需要单独处理。
        if (null == mSnackbar || !mSnackbar.isShown()) {
            mSnackbar = Snackbar.make(view, "这是你想要的吗？", Snackbar.LENGTH_LONG);


            //自定义mSnackbar
            //改变文字与背景颜色
//            mSnackbar.setActionTextColor(Color.WHITE);
//            View mSnackView =mSnackbar.getView();
//            mSnackView.setBackgroundColor(Color.GRAY);
//            int snackbarTextId =android.support.design.R.id.snackbar_text;
//            TextView tv= (TextView) mSnackView.findViewById(snackbarTextId);
//            tv.setTextColor(Color.WHITE);

            //添加icon
         //   setBug();

            //改变位置，在design25.2.0中，floatingButton按钮会随着Snackbar一起滚动到中间位置，很傻比的bug,而在design23.4.0没有问题
//            View mSnackView = mSnackbar.getView();
//            ViewGroup.LayoutParams vl = mSnackView.getLayoutParams();
//            CoordinatorLayout.LayoutParams ls = new CoordinatorLayout.LayoutParams(vl.width, vl.height);
//            ls.gravity = Gravity.CENTER_VERTICAL;
//            mSnackView.setLayoutParams(ls);


            mSnackbar.show();
            mSnackbar.setAction("关注", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSnackbar.dismiss();
                }
            });
        }
    }


    /**
     * 经验证，添加图片在design25.2.0崩溃，而在design23.4.0没有问题可以正常加载
     */

    private void setBug() {
        View snackbarView = mSnackbar.getView();
        ImageView iconImage = new ImageView(FloatingActionButtonAndSnackbarActivity.this);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
        iconImage.setImageResource(R.mipmap.ic_launcher);
        Snackbar.SnackbarLayout.LayoutParams sl = new Snackbar.SnackbarLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //让icon的布局位于父布局垂直居中的位置
        sl.gravity = Gravity.CENTER_VERTICAL;
        iconImage.setLayoutParams(sl);
        snackbarLayout.addView(iconImage, 0);
    }
}
