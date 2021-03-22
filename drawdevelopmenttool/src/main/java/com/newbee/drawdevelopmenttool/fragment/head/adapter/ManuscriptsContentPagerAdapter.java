package com.newbee.drawdevelopmenttool.fragment.head.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;
import com.newbee.drawdevelopmenttool.fragment.head.content.ManuscriptsContentShowModelType;
import com.newbee.drawdevelopmenttool.fragment.head.view.HeadFragmentShowContentPagerView;
import com.newbee.drawdevelopmenttool.share.DrawShare;

import java.util.List;

public class ManuscriptsContentPagerAdapter extends BaseContentHeadAdapter{
    private int pager=1;
    private int countPager=1;
    private String shareKey;
    private HeadFragmentShowContentPagerView pagerView;
    private HeadFragmentShowContentPagerView.IvClickListen ivClickListen=new HeadFragmentShowContentPagerView.IvClickListen() {
        @Override
        public void needLast() {
            pager--;
            if(pager<1){
                pager=1;
            }
            setText();
            notifyDataSetChanged();
        }

        @Override
        public void needNext() {
            pager++;
            if(pager*getCanShowMaxNumb()>=contentHeadBeanList.size()){
                pager=countPager;
            }
            setText();
            notifyDataSetChanged();
        }
    };

    public ManuscriptsContentPagerAdapter(Context context, HeadFragmentShowContentType contentType, List<ContentHeadBean> contentHeadBeanList, ItemClick itemClick, ManuscriptsContentShowModelType showModelType, boolean adapterNeedAddContent, boolean adapterNeedRetrun, HeadFragmentShowContentPagerView pagerView) {
        super(context, contentType, contentHeadBeanList, itemClick, showModelType, adapterNeedAddContent, adapterNeedRetrun);
        this.pagerView=pagerView;
        this.pagerView.setList(ivClickListen);
        startCount();
    }

    @Override
    public void setNeedRetrun(boolean setNeedRetrun) {
        super.setNeedRetrun(setNeedRetrun);
        startCount();
    }

    @Override
    public void resetList(List<ContentHeadBean> resetList) {
        super.resetList(resetList);
        startCount();
    }

    private void startCount(){
        if(null==contentHeadBeanList||contentHeadBeanList.size()==0){
            pager=1;
            countPager=1;
            shareKey="";
        }else {
            countPager=contentHeadBeanList.size()/getCanShowMaxNumb();
            if(contentHeadBeanList.size()%getCanShowMaxNumb()>0){
                countPager++;
            }
            switch (contentType){
                case MANUSCRIPTS:
                case FAVORITES:
                    shareKey=contentType.name()+"_pager_"+contentHeadBeanList.get(0).getFilePath();
                    String shareStr=DrawShare.getInstance().getString(shareKey);
                    if(TextUtils.isEmpty(shareStr)){
                        pager=1;
                    }else {
                        pager=Integer.valueOf(shareStr);
                        if(pager*getCanShowMaxNumb()>=contentHeadBeanList.size()){
                            pager=countPager;
                        }
                    }
                    break;
                default:
                    pager=1;
                    shareKey="";
                    break;
            }

        }
        setText();

    }

    public void setText(){
        if(!TextUtils.isEmpty(shareKey)){
            DrawShare.getInstance().putString(shareKey,pager+"");
        }
        pagerView.setText(pager,countPager);

    }
    public int getStartNumb(){
        int startShowNumb=(pager-1)*getCanShowMaxNumb();
        return startShowNumb;
    }

    @Override
    public ContentHeadBean getNeedContenHeadBean(int index) {

        return contentHeadBeanList.get(getStartNumb()+index);
    }

    @Override
    public int getCanShowMaxNumb(){
        int canShowNumb=9;
        if(adapterNeedAddContent){
            canShowNumb--;
        }
        if(adapterNeedRetrun){
            canShowNumb--;
        }
        return canShowNumb;
    }

    @Override
    public int getItemCount() {
        int showNumb=0;
        if(null==contentHeadBeanList){
            showNumb=0;
        }else {
            showNumb=contentHeadBeanList.size()-getStartNumb();
            if(showNumb>getCanShowMaxNumb()){
                showNumb=getCanShowMaxNumb();
            }

        }
        if(adapterNeedAddContent){
            showNumb++;
        }
        if(adapterNeedRetrun){
            showNumb++;
        }
        return showNumb;
    }
}
