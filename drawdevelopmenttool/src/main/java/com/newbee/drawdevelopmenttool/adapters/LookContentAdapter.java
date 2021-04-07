package com.newbee.drawdevelopmenttool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.glide.MyGlide;
import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.bean.content.bg.ContentBgUtil;
import com.newbee.drawdevelopmenttool.share.DrawShare;



public class LookContentAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private Context context;
    private LayoutInflater layoutInflater;
    private ContentHeadBean headBean;
    private ContentPagerBean pagerBean;
    private ItemClick itemClick;

    public LookContentAdapter(Context context, ContentHeadBean headBean, ItemClick itemClick) {
        this.context=context;
        layoutInflater = LayoutInflater.from(context);
        this.headBean=headBean;
        this.pagerBean= DrawShare.getInstance().getContentPagerBean(this.headBean.getId());
        this.itemClick = itemClick;



    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_look_content, parent, false));
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LG.i(tag, "on bing view " + position);
        ViewHodler viewHodler = (ViewHodler) holder;
        final int pagerNumb=position+1;

        String filePath= headBean.getLocalFilePath(pagerNumb);
        MyGlide.getInstance().setBitMapNoCache(viewHodler.iv,filePath);
        int bgRs=ContentBgUtil.getBgTempRs(headBean,position);

        viewHodler.rv.setBackgroundResource(bgRs);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerBean.setShowPagerNumb(pagerNumb);
                pagerBean.saveToShare();
                itemClick.itemClick(pagerNumb);
            }
        };
        viewHodler.iv.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return     pagerBean.getCountPagerNumb();
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        ImageView iv;
        RelativeLayout rv;

        public ViewHodler(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_item_look_content);
            rv = itemView.findViewById(R.id.rl_item_look_content);
        }
    }

    public interface ItemClick {
        void itemClick(int pagerNumb);
    }
}
