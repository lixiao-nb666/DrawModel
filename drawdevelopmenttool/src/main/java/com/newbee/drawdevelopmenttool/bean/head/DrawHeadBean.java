package com.newbee.drawdevelopmenttool.bean.head;

import java.io.Serializable;

public class DrawHeadBean implements Serializable {
    private String headStr;
    private int headRs;

    public DrawHeadBean(String headStr, int headRs) {
        this.headStr = headStr;
        this.headRs = headRs;
    }

    public String getHeadStr() {
        return headStr;
    }

    public void setHeadStr(String headStr) {
        this.headStr = headStr;
    }

    public int getHeadRs() {
        return headRs;
    }

    public void setHeadRs(int headRs) {
        this.headRs = headRs;
    }

    @Override
    public String toString() {
        return "DrawHeadBean{" +
                "headStr='" + headStr + '\'' +
                ", headRs=" + headRs +
                '}';
    }
}
