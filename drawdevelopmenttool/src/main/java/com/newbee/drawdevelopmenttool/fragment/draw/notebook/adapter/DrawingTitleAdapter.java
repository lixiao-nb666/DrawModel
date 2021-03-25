package com.newbee.drawdevelopmenttool.fragment.draw.notebook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;

import com.newbee.drawdevelopmenttool.draw.base.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.DrawFunctionUtil;


import java.util.List;


public class DrawingTitleAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private LayoutInflater layoutInflater;
    private List<BaseDrawType> drawTypeList;
    private List<BaseDrawViewFunctionType> functionTypeList;
    private ItemClick itemClick;

    public DrawingTitleAdapter(Context context, List<BaseDrawType> drawTypeList, List<BaseDrawViewFunctionType> titleFunctionList, ItemClick itemClick) {
        layoutInflater = LayoutInflater.from(context);
        this.drawTypeList = drawTypeList;
        this.functionTypeList = titleFunctionList;
        this.itemClick = itemClick;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_drawing_title, parent, false));
        return viewHodler;
    }

    private ImageView selectDrawView;
    private ImageView selectFunctionView;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LG.i(tag, "on bing view " + position);
        final ViewHodler viewHodler = (ViewHodler) holder;
        if (position < drawTypeList.size()) {
            final BaseDrawType drawType = drawTypeList.get(position);
            final int showRsId = DrawFunctionUtil.useDrawTypeGetImgRs(drawType);
            viewHodler.titltIV.setImageResource(showRsId);
            viewHodler.titltIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != selectDrawView) {
                        selectDrawView.setSelected(false);
                    }
                    if (null != selectFunctionView) {
                        selectFunctionView.setSelected(false);
                        selectFunctionView = null;
                    }
                    selectDrawView = viewHodler.titltIV;
                    selectDrawView.setSelected(true);
                    itemClick.itemClick(showRsId, position, drawType);
                }
            });

        } else {
            int needPosition = position - drawTypeList.size();
            final BaseDrawViewFunctionType drawFunctionType = functionTypeList.get(needPosition);
            final int showRsId = DrawFunctionUtil.useFunctionTypeGetImgRs(drawFunctionType);
            viewHodler.titltIV.setImageResource(showRsId);
            viewHodler.titltIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != selectFunctionView) {
                        selectFunctionView.setSelected(false);
                    }
                    selectFunctionView = viewHodler.titltIV;
                    selectFunctionView.setSelected(true);
                    itemClick.itemClick(showRsId, position, drawFunctionType);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return drawTypeList.size() + functionTypeList.size();
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        ImageView titltIV;

        public ViewHodler(View itemView) {
            super(itemView);
            titltIV = itemView.findViewById(R.id.iv_drawing_title);
        }
    }

    public interface ItemClick {
        void itemClick(int rsId, int positon, BaseDrawType baseDrawType);

        void itemClick(int rsId, int positon, BaseDrawViewFunctionType drawFunctionType);
    }
}
