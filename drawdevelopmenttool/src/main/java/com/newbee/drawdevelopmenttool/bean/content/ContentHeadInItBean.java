package com.newbee.drawdevelopmenttool.bean.content;



import java.io.Serializable;


public class ContentHeadInItBean implements Serializable {
    private int bgRsOrdinal;
    private int color;
    private int lastFunctionTypeOrdinal;

    private int showPagerNumb;

    public ContentHeadInItBean(){
        bgRsOrdinal=-1;
        color=-1;
        lastFunctionTypeOrdinal=-1;
        showPagerNumb=1;
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

    public int getLastFunctionTypeOrdinal() {
        return lastFunctionTypeOrdinal;
    }

    public void setLastFunctionTypeOrdinal(int lastFunctionTypeOrdinal) {
        this.lastFunctionTypeOrdinal = lastFunctionTypeOrdinal;
    }

    public int getShowPagerNumb() {
        return showPagerNumb;
    }

    public void setShowPagerNumb(int showPagerNumb) {
        this.showPagerNumb = showPagerNumb;
    }

    @Override
    public String toString() {
        return "ContentHeadInItBean{" +
                "bgRsOrdinal=" + bgRsOrdinal +
                ", color=" + color +
                ", lastFunctionTypeOrdinal=" + lastFunctionTypeOrdinal +
                ", showPagerNumb=" + showPagerNumb +
                '}';
    }
}
