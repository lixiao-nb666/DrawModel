package com.newbee.drawdevelopmenttool.activity.init.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.glide.MyGlide;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.util.TimeUtil;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;

import java.util.List;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/10/23 0023 16:27
 */
public class DrawBoardHeadContentAdapter extends RecyclerView.Adapter {
    private String tag = getClass().getName() + ">>>>";
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ContentHeadBean> contentHeadBeanList;
    private ItemClick itemClick;


    public DrawBoardHeadContentAdapter(Context context, List<ContentHeadBean> contentHeadBeanList, ItemClick itemClick) {
        this.context=context;
        layoutInflater = LayoutInflater.from(context);
        this.contentHeadBeanList = contentHeadBeanList;
        this.itemClick=itemClick;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_draw_board_content_head, parent, false));
        return viewHodler;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        LG.i(tag, "on bing view " + position);

        final ViewHodler viewHodler = (ViewHodler) holder;
        final ContentHeadBean contentHeadBean = contentHeadBeanList.get(position);
        String contentHeadName=contentHeadBean.getName();
        String showStr=contentHeadName;
        if(TextUtils.isEmpty(showStr)||showStr.contains( BaseApplication.getRsString(R.string.no_name_content))){
            showStr= TimeUtil.getDateStr(contentHeadBean.getCreateTime());
        }

        viewHodler.contentHeadTV.setText(showStr);
        String filePath=contentHeadBean.getLocalFilePath(1);
        MyGlide.getInstance().setBitMapNoCache(viewHodler.contentHeadCloseIV,filePath);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               itemClick.click( contentHeadBean);
            }
        };
        View.OnLongClickListener onLongClickListener=new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClick.longClick(contentHeadBean);
                return true;
            }
        };
        viewHodler.contentHeadTV.setOnClickListener(onClickListener);
        viewHodler.contentHeadCloseIV.setOnClickListener(onClickListener);
        viewHodler.LL.setOnClickListener(onClickListener);

        viewHodler.contentHeadTV.setOnLongClickListener(onLongClickListener);
        viewHodler.contentHeadCloseIV.setOnLongClickListener(onLongClickListener);
        viewHodler.LL.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return contentHeadBeanList.size();
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        TextView contentHeadTV;
        ImageView contentHeadCloseIV;
        LinearLayout LL;
        public ViewHodler(View itemView) {
            super(itemView);
            contentHeadTV = itemView.findViewById(R.id.tv_content_head_item);
            contentHeadCloseIV = itemView.findViewById(R.id.iv_icon);
            LL = itemView.findViewById(R.id.ll);
        }
    }

    public interface ItemClick{
        public void click(ContentHeadBean contentHeadBean);

        public void longClick(ContentHeadBean contentHeadBean);
    }

}
