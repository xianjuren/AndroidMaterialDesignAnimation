package com.design.scene;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.ChangeClipBounds;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.design.R;

/**
 * Created by Jone on 17/6/12.
 */
public class SceneChangeClipBoundsActivity extends BaseSceneActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view1 = LayoutInflater.from(this).inflate(R.layout.clip_picture_items, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.clip_picture_items, null);
        ImageView vi1 = (ImageView) view1.findViewById(R.id.clip_picture);
        ImageView vi2 = (ImageView) view2.findViewById(R.id.clip_picture);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vi1.setClipBounds(new Rect(0, 0, 200, 200));
            vi2.setClipBounds(new Rect(200, 200, 400, 400));
            //这里只能使用 setScene(view1,view2)，而不能使用setScene(int id1,int id2);
            setScene(view1,view2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    Transition getTransition() {
        return new ChangeClipBounds();
    }
}
