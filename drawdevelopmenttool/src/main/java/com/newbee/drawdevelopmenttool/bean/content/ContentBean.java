package com.newbee.drawdevelopmenttool.bean.content;

import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;


import java.io.Serializable;

public class ContentBean implements Serializable {
    private ContentHeadBean contentHeadBean;
    private ContentHeadInItBean inItBean;
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
        inItBean=new ContentHeadInItBean();
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

    public ContentHeadInItBean getInItBean() {
        if(null==inItBean){
            inItBean=new ContentHeadInItBean();
        }
        return inItBean;
    }

    public void setInItBean(ContentHeadInItBean inItBean) {
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
           contentHeadBean=  ContentHeadSqlServer.getInstance().add(contentHeadBean);
           setContentHeadBean(contentHeadBean);
        }else {
          ContentHeadSqlServer.getInstance().update(contentHeadBean);
        }
        DrawShare.getInstance().putContentBean(this);
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
