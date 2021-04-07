package com.newbee.drawdevelopmenttool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.glide.MyGlide;
import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.bean.content.bg.ContentBgUtil;
import com.newbee.drawdevelopmenttool.share.DrawShare;

import java.util.List;


public class ShowBgAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private ItemClick itemClick;
    private Context context;

    private List<Integer> bgTempList;
    private int selectIndex;

    public ShowBgAdapter(Context context, ItemClick itemClick) {
        layoutInflater = LayoutInflater.from(context);
        this.itemClick = itemClick;
        this.context=context;
    }


    public void setHeadBean(ContentHeadBean headBean){

        bgTempList= ContentBgUtil.getBgRsTempList(headBean);
        ContentPagerBean pagerBean= DrawShare.getInstance().getContentPagerBean(headBean.getId());
        int pagerIndex=pagerBean.getShowPagerNumb();
        this.selectIndex=ContentBgUtil.getBgIndex(headBean,pagerIndex);
        LG.i("kankanshenmegui","-etHeadBean------:"+selectIndex);
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_show_item_bg, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private ImageView nowSelectIv;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final int rs=bgTempList.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        MyGlide.getInstance().setBitMap(viewHolder.penIV,rs);
            if(position==selectIndex){
                nowSelectIv=viewHolder.selectIV;
                viewHolder.selectIV.setVisibility(View.VISIBLE);
                itemClick.init(position);
            }else {
                viewHolder.selectIV.setVisibility(View.GONE);
            }


        viewHolder.penIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectIndex=position;
                itemClick.clickPenRs(rs,position);
                if(null!=nowSelectIv){
                    nowSelectIv.setVisibility(View.GONE);
                }
                nowSelectIv=viewHolder.selectIV;
                nowSelectIv.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(null==bgTempList){
            return 0;
        }
        return bgTempList.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView penIV,selectIV;

        public ViewHolder(View itemView) {
            super(itemView);
            penIV=itemView.findViewById(R.id.iv_pen_item);
            selectIV=itemView.findViewById(R.id.iv_select);
            MyGlide.getInstance().setBitMap(selectIV,R.drawable.select_true);
        }
    }

    public interface ItemClick {

        public void init(int position);

        public void clickPenRs(int rs,int position);


    }



}
