package com.myapplication.adpter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myapplication.fragment.Fragment1;


/**
 * Created by Jone on 17/6/19.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {


    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{ "分享", "收藏", "关注", "关注者"};
    private Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return new Fragment1();

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
