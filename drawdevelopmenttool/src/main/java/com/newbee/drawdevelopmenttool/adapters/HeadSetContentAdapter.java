package com.newbee.drawdevelopmenttool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.newbee.drawdevelopmenttool.R;

import java.util.List;

public class HeadSetContentAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private List<String> headSetContentList;
    private ItemClick itemClick;

    public HeadSetContentAdapter(Context context, List<String> headSetContentList, ItemClick itemClick) {
        layoutInflater = LayoutInflater.from(context);
        this.headSetContentList = headSetContentList;
        this.itemClick = itemClick;
    }


    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_head_set_content, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        final String headSetContent = headSetContentList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.headSetContentTv.setText(headSetContent);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.clickHeadSetContent(headSetContent);
            }
        };
        viewHolder.headSetContentTv.setOnClickListener(onClickListener);
        viewHolder.relativeLayout.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return headSetContentList.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relativeLayout;
        private TextView headSetContentTv;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rl_head_set_content);
            headSetContentTv = itemView.findViewById(R.id.tv_head_set_content);
        }
    }

    public interface ItemClick {

        public void clickHeadSetContent(String headSetContent);


    }

}
