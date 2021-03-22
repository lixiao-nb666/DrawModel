package com.newbee.drawdevelopmenttool.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;

import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;


import java.util.List;


public class ContentHeadAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private LayoutInflater layoutInflater;
    private List<ContentHeadBean> contentHeadBeanList;
    private ItemClick itemClick;
    private int showNumb;
    private long selectId;
    private boolean isFirstSet = false;

    public ContentHeadAdapter(Context context, List<ContentHeadBean> contentHeadBeanList, ItemClick itemClick, int showNumb, long selectId) {
        layoutInflater = LayoutInflater.from(context);
        this.contentHeadBeanList = contentHeadBeanList;
        this.itemClick = itemClick;
        this.showNumb = showNumb;
        this.selectId = selectId;
    }

    public void setList(List<ContentHeadBean> contentHeadBeanList) {
        this.contentHeadBeanList = contentHeadBeanList;
        this.selectId = 0;
        isFirstSet = false;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_content_head, parent, false));
        return viewHodler;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        LG.i(tag, "on bing view " + position);
        final ViewHodler viewHodler = (ViewHodler) holder;
        if(position>=contentHeadBeanList.size()){
            return;
        }

        final ContentHeadBean contentHeadBean = contentHeadBeanList.get(position);
        String contentHeadName;
        if (TextUtils.isEmpty(contentHeadBean.getName())) {

            contentHeadName = contentHeadBean.getNoNameTitle();
        } else {
            contentHeadName = contentHeadBean.getName();
        }
        viewHodler.contentHeadTV.setText(contentHeadName);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(viewHodler.contentHeadTV, viewHodler.contentHeadRL, contentHeadBean);
            }
        };
        if (!isFirstSet) {
            if (selectId != 0 && selectId == (contentHeadBean.getId())) {
                itemClick.itemClick(viewHodler.contentHeadTV, viewHodler.contentHeadRL, contentHeadBean);
                isFirstSet = true;
            } else if (position == 0) {
                itemClick.itemClick(viewHodler.contentHeadTV, viewHodler.contentHeadRL, contentHeadBean);
                isFirstSet = true;
            }
        }
        viewHodler.contentHeadRL.setOnClickListener(onClickListener);
        viewHodler.contentHeadTV.setOnClickListener(onClickListener);
        viewHodler.contentHeadCloseIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.contentHeadClose(contentHeadBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return showNumb;
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        TextView contentHeadTV;
        ImageView contentHeadCloseIV;
        RelativeLayout contentHeadRL;
        public ViewHodler(View itemView) {
            super(itemView);
            contentHeadTV = itemView.findViewById(R.id.tv_content_head_item);
            contentHeadCloseIV = itemView.findViewById(R.id.iv_content_head_item_close);
            contentHeadRL = itemView.findViewById(R.id.rl_content_head_item);
        }
    }

    public interface ItemClick {

        public void contentHeadClose(ContentHeadBean contentHeadBean);

        public void itemClick(TextView contentHeadTV, RelativeLayout contentHeadRL, ContentHeadBean contentHeadBean);
    }
}
