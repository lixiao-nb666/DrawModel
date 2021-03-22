package com.newbee.drawdevelopmenttool.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.head.DrawHeadBean;

import java.util.List;


public class DrawHeadActivityAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private LayoutInflater layoutInflater;
    private List<DrawHeadBean> drawHeadList;
    private ItemClick itemClick;
    private String initSelectStr;

    public DrawHeadActivityAdapter(Context context, List<DrawHeadBean> drawHeadList, ItemClick itemClick, String initSelectStr) {
        layoutInflater = LayoutInflater.from(context);
        this.drawHeadList = drawHeadList;
        this.itemClick = itemClick;
        this.initSelectStr = initSelectStr;
    }


    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_drawing_head_activity, parent, false));
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        LG.i(tag, "on bing view " + position);
        final ViewHodler viewHodler = (ViewHodler) holder;
        final DrawHeadBean drawHeadBean = drawHeadList.get(position);
        if (!TextUtils.isEmpty(initSelectStr) && initSelectStr.equals(drawHeadBean.getHeadStr())) {
            itemClick.itemClick(viewHodler, drawHeadBean, position);
        }
        viewHodler.headIV.setImageResource(drawHeadBean.getHeadRs());

        viewHodler.headTV.setText(drawHeadBean.getHeadStr());
        viewHodler.headTV.setTextSize(16);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(viewHodler, drawHeadBean, position);
            }
        };

        viewHodler.headTV.setOnClickListener(onClickListener);
        viewHodler.headIV.setOnClickListener(onClickListener);
        viewHodler.headLL.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return drawHeadList.size();
    }


    public class ViewHodler extends RecyclerView.ViewHolder {
        public ImageView headIV;
        public TextView headTV;
        public LinearLayout headLL;

        public ViewHodler(View itemView) {
            super(itemView);
            headIV = itemView.findViewById(R.id.iv_draw_head_item);
            headTV = itemView.findViewById(R.id.tv_draw_head_item);
            headLL = itemView.findViewById(R.id.ll_draw_head_item);
        }


    }

    public interface ItemClick {

        public void itemClick(ViewHodler viewHodler, DrawHeadBean drawHeadBean, int positon);
    }
}
