package com.newbee.drawdevelopmenttool.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.bean.manuscript.ManuscriptAddTypeInfoBean;

import java.util.List;


public class ManuscriptsSelectAddTypeAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private LayoutInflater layoutInflater;
    private List<ManuscriptAddTypeInfoBean> manuscriptAddTypeInfoBeanList;
    private ItemClick itemClick;

    public ManuscriptsSelectAddTypeAdapter(Context context, List<ManuscriptAddTypeInfoBean> manuscriptAddTypeInfoBeanList, ItemClick itemClick) {
        layoutInflater = LayoutInflater.from(context);
        this.manuscriptAddTypeInfoBeanList = manuscriptAddTypeInfoBeanList;
        this.itemClick = itemClick;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_manuscripts_select_add_type, parent, false));
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHodler viewHodler = (ViewHodler) holder;
        final ManuscriptAddTypeInfoBean manuscriptAddTypeInfoBean = manuscriptAddTypeInfoBeanList.get(position);
        viewHodler.iv.setImageResource(manuscriptAddTypeInfoBean.getRs());
        viewHodler.tv.setText(manuscriptAddTypeInfoBean.getTypeStr());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(manuscriptAddTypeInfoBean.getContentHeadType(), position);
            }
        };
        viewHodler.iv.setOnClickListener(onClickListener);
        viewHodler.tv.setOnClickListener(onClickListener);
        viewHodler.rl.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return manuscriptAddTypeInfoBeanList.size();
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        RelativeLayout rl;

        public ViewHodler(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_item_select_type);
            tv = itemView.findViewById(R.id.tv_item_select_type);
            rl = itemView.findViewById(R.id.rl_item_select_type);
        }
    }

    public interface ItemClick {
        void itemClick(ContentHeadType contentHeadType, int positon);
    }
}
