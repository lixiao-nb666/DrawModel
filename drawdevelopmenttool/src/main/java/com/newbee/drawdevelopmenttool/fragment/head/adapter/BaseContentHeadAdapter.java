package com.newbee.drawdevelopmenttool.fragment.head.adapter;

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
import com.lixiao.build.mybase.appliction.MyApplicationFile;
import com.lixiao.build.util.TimeUtil;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
import com.newbee.drawdevelopmenttool.eventbus.EventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.eventbus.EventType;
import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;
import com.newbee.drawdevelopmenttool.fragment.head.content.ManuscriptsContentShowModelType;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

import java.io.File;
import java.util.List;

public class BaseContentHeadAdapter extends RecyclerView.Adapter {
    protected String tag = getClass().getName() + ">>>>";
    protected Context context;
    protected LayoutInflater layoutInflater;
    protected HeadFragmentShowContentType contentType;
    protected List<ContentHeadBean> contentHeadBeanList;
    protected ItemClick itemClick;
    protected ManuscriptsContentShowModelType showModelType;
    protected boolean adapterNeedAddContent;
    protected boolean adapterNeedRetrun;


    public BaseContentHeadAdapter(Context context, HeadFragmentShowContentType contentType, List<ContentHeadBean> contentHeadBeanList, ItemClick itemClick, ManuscriptsContentShowModelType showModelType, boolean adapterNeedAddContent, boolean adapterNeedRetrun) {
        this.context=context;
        layoutInflater = LayoutInflater.from(context);
        this.contentType=contentType;
        this.contentHeadBeanList = contentHeadBeanList;
        this.itemClick = itemClick;
        this.showModelType = showModelType;
        this.adapterNeedAddContent = adapterNeedAddContent;
        this.adapterNeedRetrun = adapterNeedRetrun;
    }


    public void setNeedRetrun(boolean setNeedRetrun) {
        this.adapterNeedRetrun = setNeedRetrun;
    }

    public void resetList(List<ContentHeadBean> resetList) {
        this.contentHeadBeanList = resetList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHodler viewHodler = null;
        switch (showModelType){
            case LIST:
                viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_manuscripts_content_list, parent, false));
                break;
            default:
                viewHodler = new ViewHodler(layoutInflater.inflate(R.layout.adapter_manuscripts_content_grid, parent, false));
                break;
        }
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ViewHodler viewHodler = (ViewHodler) holder;
        if (adapterNeedAddContent && position == 0) {
            MyGlide.getInstance().setBitMap(viewHodler.contentBgIV,R.drawable.content_head_add);
//            viewHodler.contentBgIV.setImageResource();
            viewHodler.contentNameTV.setText(BaseApplication.getRsString(R.string.manuscripts_add));
            viewHodler.contentTimeTV.setText("");
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.itemClick(v,position, null, ItemClickType.ADD);
                }
            };
            View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClick.itemClick(v,position, null, ItemClickType.ADD);
                    return true;
                }
            };
            viewHodler.starIV.setVisibility(View.GONE);
            viewHodler.contentNameTV.setOnClickListener(onClickListener);
            viewHodler.contentTimeTV.setOnClickListener(onClickListener);
            viewHodler.contentBgIV.setOnClickListener(onClickListener);
            viewHodler.contentLL.setOnClickListener(onClickListener);
            viewHodler.contentNameTV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentTimeTV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentBgIV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentLL.setOnLongClickListener(onLongClickListener);
        } else if (adapterNeedRetrun && position == getItemCount() - 1) {
            MyGlide.getInstance().setBitMap(viewHodler.contentBgIV,R.drawable.content_head_retrun);
//            viewHodler.contentBgIV.setImageResource();
            viewHodler.contentNameTV.setText(BaseApplication.getRsString(R.string.manuscripts_retrun));
            viewHodler.contentTimeTV.setText("");
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.itemClick(v,position, null, ItemClickType.RETRUN);
                }
            };
            View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClick.itemClick(v,position, null, ItemClickType.RETRUN);
                    return true;
                }
            };
            viewHodler.starIV.setVisibility(View.GONE);
            viewHodler.contentNameTV.setOnClickListener(onClickListener);
            viewHodler.contentTimeTV.setOnClickListener(onClickListener);
            viewHodler.contentBgIV.setOnClickListener(onClickListener);
            viewHodler.contentLL.setOnClickListener(onClickListener);
            viewHodler.contentNameTV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentTimeTV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentBgIV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentLL.setOnLongClickListener(onLongClickListener);
        } else {
            int index = position;
            if (adapterNeedAddContent) {
                index = position - 1;
            }
            final ContentHeadBean contentHeadBean=getNeedContenHeadBean(index);
            viewHodler.contentTimeTV.setText(TimeUtil.getDateStr(contentHeadBean.getCreateTime()));
            viewHodler.contentNameTV.setMaxLines(1);
            if (TextUtils.isEmpty(contentHeadBean.getName())) {
                viewHodler.contentNameTV.setText(contentHeadBean.getNoNameTitle());
            } else {
                viewHodler.contentNameTV.setText(contentHeadBean.getName());
            }
            int rsId= ContentHeadRsType.getRs(contentHeadBean.getEnumType(),contentHeadBean.getBgType());
            String imageUrl="";
            switch (contentType){
                case FAVORITES:
                        String fileName=contentHeadBean.getFileName(1);
                        if(!TextUtils.isEmpty(fileName)){
                            String filePath= MyApplicationFile.getInstance().getPic_files() + File.separator + fileName;
                            if(new File(filePath).exists()){
                                imageUrl=filePath;
                            }
                        }
                    break;
            }
            if(TextUtils.isEmpty(imageUrl)){
                MyGlide.getInstance().setBitMap(viewHodler.contentBgIV,rsId);
            }else {
                MyGlide.getInstance().setBitMapNoCache(viewHodler.contentBgIV,imageUrl);
            }
            viewHodler.starIV.setVisibility(View.VISIBLE);
            if (contentHeadBean.isStar()) {
                viewHodler.starIV.setImageResource(R.drawable.head_favorites_true);
            } else {
                viewHodler.starIV.setImageResource(R.drawable.head_favorites_false);
            }
            viewHodler.starIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contentHeadBean.setStar(!contentHeadBean.isStar());
                    ContentHeadSqlServer.getInstance().update(contentHeadBean);
                }
            });
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contentHeadBean.setClickNumb(contentHeadBean.getClickNumb() + 1);

                    itemClick.itemClick(v,position, contentHeadBean, ItemClickType.CONTENT);


                }
            };
            View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemClick.itemLongClick(v,contentHeadBean);
                    return true;
                }
            };
            viewHodler.contentNameTV.setOnClickListener(onClickListener);
            viewHodler.contentTimeTV.setOnClickListener(onClickListener);
            viewHodler.contentBgIV.setOnClickListener(onClickListener);
            viewHodler.contentLL.setOnClickListener(onClickListener);
            viewHodler.contentNameTV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentTimeTV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentBgIV.setOnLongClickListener(onLongClickListener);
            viewHodler.contentLL.setOnLongClickListener(onLongClickListener);
        }


    }

    public ContentHeadBean getNeedContenHeadBean(int index){
        return    contentHeadBeanList.get(index);
    }


    public int getCanShowMaxNumb(){
        return contentHeadBeanList.size();
    }

    @Override
    public int getItemCount() {
        int showNumb=0;
        if(null==contentHeadBeanList){
            showNumb=0;
        }else {
            showNumb=contentHeadBeanList.size();
        }
        if(adapterNeedAddContent){
            showNumb++;
        }
        if(adapterNeedRetrun){
            showNumb++;
        }
        return showNumb;
    }


    class ViewHodler extends RecyclerView.ViewHolder {
        ImageView contentBgIV;
        TextView contentNameTV;
        TextView contentTimeTV;
        LinearLayout contentLL;
        ImageView starIV;

        public ViewHodler(View itemView) {
            super(itemView);
            contentBgIV = itemView.findViewById(R.id.iv_manuscripts_content_bg);
            contentBgIV.setScaleType(ImageView.ScaleType.CENTER);
            contentNameTV = itemView.findViewById(R.id.tv_manuscripts_content_name);
            contentTimeTV = itemView.findViewById(R.id.tv_manuscripts_content_time);
            contentLL = itemView.findViewById(R.id.ll_manuscripts_content);
            starIV = itemView.findViewById(R.id.iv_star);
        }
    }

    public interface ItemClick {
        public void itemClick(View view, int position, ContentHeadBean contentHeadBean, ItemClickType itemClickType);

        public void itemLongClick(View view, ContentHeadBean contentHeadBean);
    }

    public enum ItemClickType {
        ADD,
        RETRUN,
        CONTENT,
    }
}
