package com.newbee.drawdevelopmenttool.bean.manuscript;

import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;


import java.io.Serializable;

public class ManuscriptAddTypeInfoBean implements Serializable {
    private ContentHeadType contentHeadType;
    private String typeStr;
    private int rs;


    public ManuscriptAddTypeInfoBean(ContentHeadType contentHeadType, String typeStr, int rs) {
        this.contentHeadType = contentHeadType;
        this.typeStr = typeStr;
        this.rs = rs;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public ContentHeadType getContentHeadType() {
        return contentHeadType;
    }

    public void setContentHeadType(ContentHeadType contentHeadType) {
        this.contentHeadType = contentHeadType;
    }

    @Override
    public String toString() {
        return "ManuscriptAddTypeInfoBean{" +
                "contentHeadType=" + contentHeadType +
                ", typeStr='" + typeStr + '\'' +
                ", rs=" + rs +
                '}';
    }
}
