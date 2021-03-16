package com.newbee.drawdevelopmenttool.bean.content;

import com.newbee.taozinoteboard.draw.bean.init.TaoZiDrawViewInItBean;
import com.newbee.taozinoteboard.splite.ContentHeadSqlServer;
import com.newbee.taozinoteboard.utils.share.MySharePreferences;

import java.io.Serializable;

public class ContentBean implements Serializable {
    private ContentHeadBean contentHeadBean;
    private TaoZiDrawViewInItBean inItBean;
    private int countPagerNumb;

    public int getCountPagerNumb() {
        if(countPagerNumb<1){
            countPagerNumb=1;
        }
        if(countPagerNumb>999){
            countPagerNumb=999;
        }
        return countPagerNumb;
    }

    public void setCountPagerNumb(int countPagerNumb) {
        if(countPagerNumb<1){
            countPagerNumb=1;
        }
        if(countPagerNumb>999){
            countPagerNumb=999;
        }
        this.countPagerNumb = countPagerNumb;
    }

    public ContentBean(ContentHeadBean contentHeadBean) {
        this.contentHeadBean = contentHeadBean;
        inItBean=new TaoZiDrawViewInItBean();
    }


    public ContentHeadBean getContentHeadBean() {
        if(null==contentHeadBean){
            contentHeadBean=new ContentHeadBean();
        }
        return contentHeadBean;
    }

    public void setContentHeadBean(ContentHeadBean contentHeadBean) {
        this.contentHeadBean = contentHeadBean;
    }

    public TaoZiDrawViewInItBean getInItBean() {
        if(null==inItBean){
            inItBean=new TaoZiDrawViewInItBean();
        }
        return inItBean;
    }

    public void setInItBean(TaoZiDrawViewInItBean inItBean) {
        this.inItBean = inItBean;
    }



    public String getFileName() {
        int pager=getInItBean().getShowPagerNumb();



        return  contentHeadBean.getFileName(pager);
    }

    public String getFileName(int pager) {

        return  contentHeadBean.getFileName(pager);
    }

    public void saveToSql(){
        ContentHeadBean contentHeadBean=getContentHeadBean();
        if(getContentHeadBean().getId()==-1){
           contentHeadBean= ContentHeadSqlServer.getInstance().add(contentHeadBean);
           setContentHeadBean(contentHeadBean);
        }else {
          ContentHeadSqlServer.getInstance().update(contentHeadBean);
        }
        MySharePreferences.getInstance().putContentBean(this);
    }


    @Override
    public String toString() {
        return "ContentBean{" +
                "contentHeadBean=" + contentHeadBean +
                ", inItBean=" + inItBean +
                ", countPagerNumb=" + countPagerNumb +
                '}';
    }
}
