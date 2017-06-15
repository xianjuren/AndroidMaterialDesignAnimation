package com.design;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jone on 17/6/14.
 */
public class FragmentAndActivity extends AppCompatActivity {

    TextView mTextView;
    CircleImageView mCircleImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_activity);
        mTextView = (TextView) findViewById(R.id.share_title);
        mCircleImageView = (CircleImageView) findViewById(R.id.circle_photo);
    }


    public void fragment(View view) {
        startActivity(new Intent(this,FragmentDemo.class));
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void activity(View view) {
        Intent mIntent = new Intent(this, SharePhotoAndTextActivity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair<View, String>(mTextView, "text")
                , new Pair<View, String>(mCircleImageView, "photo"));
//第二种方式
//        ActivityOptions options =ActivityOptions.makeSceneTransitionAnimation(this, Pair.create((View)mTextView,"text")
//                ,Pair.create((View)mCircleImageView,"photo"));
        startActivity(mIntent, compat.toBundle());
    }
}
