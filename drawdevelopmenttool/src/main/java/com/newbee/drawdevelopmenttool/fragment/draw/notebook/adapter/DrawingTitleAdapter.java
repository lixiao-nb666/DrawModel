package com.newbee.drawdevelopmenttool.fragment.draw.notebook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;

import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawUserFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.DrawFunctionUtil;


import java.util.List;


public class DrawingTitleAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private LayoutInflater layoutInflater;
    private List<BaseDrawType> drawTypeList;
    private List<BaseDrawViewFunctionType> drawFunctionTypeList;
    private List<BaseDrawUserFunctionType> drawUserFunctionTypeList;
    private ItemClick itemClick;

    public DrawingTitleAdapter(Context context, List<BaseDrawType> drawTypeList, List<BaseDrawViewFunctionType> titleFunctionList, List<BaseDrawUserFunctionType> drawUserFunctionTypeList,ItemClick itemClick) {
        layoutInflater = LayoutInflater.from(context);
        this.drawTypeList = drawTypeList;
        this.drawFunctionTypeList= titleFunctionList;
        this.drawUserFunctionTypeList=drawUserFunctionTypeList;
        this.itemClick = itemClick;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_drawing_title, parent, false));
        return viewHodler;
    }

    private int selectDrawTypeIndex;
    public void setSelectDrawTypeIndex(int selectDrawTypeIndex){
       this.selectDrawTypeIndex=selectDrawTypeIndex;
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
            if(selectDrawTypeIndex==drawType.ordinal()){
                selectDrawTypeIndex=-1;
                if (null != selectDrawView) {
                    selectDrawView.setSelected(false);
                }
                if (null != selectFunctionView) {
                    selectFunctionView.setSelected(false);
                    selectFunctionView = null;
                }
                selectDrawView = viewHodler.titltIV;
                selectDrawView.setSelected(true);
            }

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

        } else if(position < drawTypeList.size()+drawFunctionTypeList.size()){
            int needPosition = position - drawTypeList.size();
            final BaseDrawViewFunctionType drawFunctionType = drawFunctionTypeList.get(needPosition);
            final int showRsId = DrawFunctionUtil.useDrawViewFunctionTypeGetImgRs(drawFunctionType);
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
        }else {
            int needPosition = position - drawTypeList.size()-drawFunctionTypeList.size();
            final BaseDrawUserFunctionType drawUserFunctionType = drawUserFunctionTypeList.get(needPosition);
            final int showRsId = DrawFunctionUtil.useDrawUserFunctionTypeGetImgRs(drawUserFunctionType);
            viewHodler.titltIV.setImageResource(showRsId);
            viewHodler.titltIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != selectFunctionView) {
                        selectFunctionView.setSelected(false);
                    }
                    selectFunctionView = viewHodler.titltIV;
                    selectFunctionView.setSelected(true);
                    itemClick.itemClick(showRsId, position, drawUserFunctionType);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return drawTypeList.size() + drawFunctionTypeList.size()+drawUserFunctionTypeList.size();
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

        void itemClick(int rsId, int positon,BaseDrawUserFunctionType drawUserFunctionType);
    }
}
