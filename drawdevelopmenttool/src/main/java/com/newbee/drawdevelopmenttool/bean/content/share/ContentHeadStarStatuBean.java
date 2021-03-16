package com.newbee.drawdevelopmenttool.bean.content.share;

import java.io.Serializable;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/9/21 0021 10:22
 */
public class ContentHeadStarStatuBean implements Serializable {
    private String contentHeadId;
    private int starTypeIndex;

    public String getContentHeadId() {
        return contentHeadId;
    }

    public void setContentHeadId(String contentHeadId) {
        this.contentHeadId = contentHeadId;
    }

    public int getStarTypeIndex() {
        return starTypeIndex;
    }

    public void setStarTypeIndex(int starTypeIndex) {
        this.starTypeIndex = starTypeIndex;
    }


    public ContentHeadStarType getContentHeadStarType(){
        try {
            return ContentHeadStarType.values()[starTypeIndex];
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public String toString() {
        return "ContentHeadStarStatuBean{" +
                "contentHeadId='" + contentHeadId + '\'' +
                ", starTypeIndex=" + starTypeIndex +
                '}';
    }
}
