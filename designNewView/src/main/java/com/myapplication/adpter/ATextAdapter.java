package com.myapplication.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.bean.AText;
import com.myapplication.R;

import java.util.List;

/**
 * Created by Jone on 17/6/17.
 */
public class ATextAdapter extends RecyclerView.Adapter<ATextAdapter.ATextHolder> {

    private List<AText> mTextList;

    @Override
    public ATextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atext, null);
        return new ATextHolder(view);
    }

    @Override
    public void onBindViewHolder(ATextHolder holder, int position) {
        AText aText = mTextList.get(position);
        if (null != aText) {
            holder.mTextView.setText(aText.getName());
        }
    }

    @Override
    public int getItemCount() {
        if (mTextList != null && mTextList.size() > 0) {
            return mTextList.size();
        }
        return 0;
    }


    public void setData(List<AText> aTexts) {
        mTextList = aTexts;
        notifyDataSetChanged();
    }

    public class ATextHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public ATextHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text_name);
        }
    }
}
