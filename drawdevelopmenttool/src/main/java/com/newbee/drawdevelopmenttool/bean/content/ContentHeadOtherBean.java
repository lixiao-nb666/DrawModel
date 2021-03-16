package com.newbee.drawdevelopmenttool.bean.content;

import java.io.Serializable;

public class ContentHeadOtherBean implements Serializable {
    private int bgType;//1,2,3分别对应，1,2,3号图,封面的背景种类
    private int clickNumb;//点击次数
    private int defDrawBgType;//手写绘图VIEW的默认背景


    public int getBgType() {
        return bgType;
    }

    public void setBgType(int bgType) {
        this.bgType = bgType;
    }



    public int getClickNumb() {
        return clickNumb;
    }

    public void setClickNumb(int clickNumb) {
        this.clickNumb = clickNumb;
    }

    public int getDefDrawBgType() {
        return defDrawBgType;
    }

    public void setDefDrawBgType(int defDrawBgType) {
        this.defDrawBgType = defDrawBgType;
    }

    @Override
    public String toString() {
        return "ContentHeadOtherBean{" +
                "bgType=" + bgType +
                ", clickNumb=" + clickNumb +
                ", defDrawBgType=" + defDrawBgType +
                '}';
    }
}
