package com.newbee.drawdevelopmenttool.bean.content;

import android.util.ArrayMap;

import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ContentPagerBean implements Serializable {
    private long id;
    private long headId;
    private int showPagerNumb;
    private int countPagerNumb;
    private Map<String,Integer>drawBgMap=new HashMap<>();

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

    public Map<String, Integer> getDrawBgMap() {
        if(null==drawBgMap){
            drawBgMap=new HashMap<>();
        }
        return drawBgMap;
    }

    public void setDrawBgMap(Map<String, Integer> drawBgMap) {
        this.drawBgMap = drawBgMap;
        if(null==this.drawBgMap){
            this.drawBgMap=new HashMap<>();
        }
    }

    @Override
    public String toString() {
        return "ContentPagerBean{" +
                "headId=" + headId +
                ", showPagerNumb=" + showPagerNumb +
                ", countPagerNumb=" + countPagerNumb +
                ", drawBgMap=" + drawBgMap +
                '}';
    }

    public void saveToShare(){
        DrawShare.getInstance().putContentPagerBean(this);
    }


}
