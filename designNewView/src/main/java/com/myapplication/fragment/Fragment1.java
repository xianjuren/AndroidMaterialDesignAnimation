package com.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.bean.AText;
import com.myapplication.adpter.ATextAdapter;
import com.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jone on 17/6/19.
 */

public class Fragment1 extends Fragment {

    ATextAdapter mATextAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        List<AText> mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            AText mAText = new AText();
            mAText.setName("name:银河星系：" + i);
            mList.add(mAText);
        }
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.lv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
         mATextAdapter = new ATextAdapter();
        mRecyclerView.setAdapter(mATextAdapter);
        mATextAdapter.setData(mList);
        return view;
    }



}
