package com.newbee.drawdevelopmenttool.bean.content;



import com.newbee.drawdevelopmenttool.share.DrawShare;

import java.io.Serializable;


public class ContentInItBean implements Serializable {
    private long headId;
    private int bgRsOrdinal;
    private int color;
    private int size;
    private int lastDrawTypeOrdinal;


    public ContentInItBean(long headId){
        this.headId=headId;
        bgRsOrdinal=-1;
        color=-1;
        size=-1;
        lastDrawTypeOrdinal=-1;
    }


    public int getBgRsOrdinal() {
        return bgRsOrdinal;
    }

    public void setBgRsOrdinal(int bgRsOrdinal) {
        this.bgRsOrdinal = bgRsOrdinal;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLastDrawTypeOrdinal() {
        return lastDrawTypeOrdinal;
    }

    public void setLastDrawTypeOrdinal(int lastDrawTypeOrdinal) {
        this.lastDrawTypeOrdinal=lastDrawTypeOrdinal;
        color=-1;
        size=-1;
    }

    public long getHeadId() {
        return headId;
    }

    public void setHeadId(long headId) {
        this.headId = headId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ContentInItBean{" +
                "headId=" + headId +
                ", bgRsOrdinal=" + bgRsOrdinal +
                ", color=" + color +
                ", size=" + size +
                ", lastDrawTypeOrdinal=" + lastDrawTypeOrdinal +
                '}';
    }

    public void saveToShare(){
        DrawShare.getInstance().putContentInitBean(this);
    }
}
