package com.newbee.drawdevelopmenttool.bean.content;

import java.io.Serializable;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/12 0012 14:42
 */
public class ContentHeadShareStatuBean implements Serializable {
    private ContentHeadShareStatutype shareStatutype;
    private String id;
    private int pagerNumb;

    public ContentHeadShareStatutype getShareStatutype() {
        return shareStatutype;
    }

    public void setShareStatutype(ContentHeadShareStatutype shareStatutype) {
        this.shareStatutype = shareStatutype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPagerNumb() {
        return pagerNumb;
    }

    public void setPagerNumb(int pagerNumb) {
        this.pagerNumb = pagerNumb;
    }

    @Override
    public String toString() {
        return "ContentHeadShareStatuBean{" +
                "shareStatutype=" + shareStatutype +
                ", id='" + id + '\'' +
                ", pagerNumb=" + pagerNumb +
                '}';
    }
}
