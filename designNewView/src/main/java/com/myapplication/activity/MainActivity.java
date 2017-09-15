package com.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String content = "2017-05-29 21:00:00";
//        content.replace("0","a");
//        String newContent =  content.replace('-','.');
        // content.replaceAll("-",".");

        String formatTime =ymdHmTime(content);
        String formattingTime;
        if (!TextUtils.isEmpty(content)) {
            formattingTime = content.replace("-", ".");
        } else {
            formattingTime = "";
        }

        String newContent = !TextUtils.isEmpty(formattingTime) ? formattingTime.substring(0, formattingTime.length() - 3) : "";
        Log.d(TAG, "newContent===" + newContent+"======formatTime"+formatTime);
//        String [] values  =new String[0];
//        if(values.length==0){
//            values = new String[1];
//            values[0] ="中国";
//        }
//        System.out.println("赋值比赛============"+values[0]);
    }


    public static String ymdHmTime(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        String format = formatter.format(strtodate);
        return format.replace("-", ".");
    }



    public void floatingActionButton(View view) {
        startActivity(new Intent(this, FloatingActionButtonAndSnackbarActivity.class));
    }

    public void navigation(View view) {
        startActivity(new Intent(this, NavigationActivity.class));

    }

    public void appBarLayout(View view) {
        startActivity(new Intent(this, XiTuActivity.class));
    }


    public void toolbar(View view) {

    }

    public void collapsingToolbarLayout(View view) {

    }

    public void coordinatorLayout(View view) {

    }
}
