package com.newbee.drawdevelopmenttool.bean.content;

import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

import java.io.Serializable;

public class ContentPagerBean implements Serializable {
    private long headId;
    private int showPagerNumb;
    private int countPagerNumb;

    public ContentPagerBean(long headId) {
        this.headId=headId;
        this.showPagerNumb=1;
        this.countPagerNumb=1;
    }

    public int getShowPagerNumb() {
        return showPagerNumb;
    }

    public void setShowPagerNumb(int showPagerNumb) {
        this.showPagerNumb = showPagerNumb;
    }

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


    public long getHeadId() {
        return headId;
    }

    public void setHeadId(long headId) {
        this.headId = headId;
    }

    @Override
    public String toString() {
        return "ContentPagerBean{" +
                "headId=" + headId +
                ", showPagerNumb=" + showPagerNumb +
                ", countPagerNumb=" + countPagerNumb +
                '}';
    }

    public void saveToShare(){
        DrawShare.getInstance().putContentPagerBean(this);
    }
}
