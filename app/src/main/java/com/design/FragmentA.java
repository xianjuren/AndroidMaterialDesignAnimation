package com.design;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jone on 17/6/14.
 */
public class FragmentA extends Fragment {

    CircleImageView circleImageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container,false);
        circleImageView = (CircleImageView) view.findViewById(R.id.shared_small_circle);
        view.findViewById(R.id.nextFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentB = new FragmentB();
                fragmentB.setEnterTransition(new Slide(Gravity.RIGHT));
                fragmentB.setSharedElementEnterTransition(new ChangeBounds());
                getFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentB).addToBackStack(null).addSharedElement(circleImageView, "shared_circle_").commit();
            }
        });
        return view;
    }


}
