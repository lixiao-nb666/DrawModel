package com.newbee.drawdevelopmenttool.activity.configset.bean;

import com.lixiao.view.radiogroup.MyRadioDataInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 14:41
 */
public class ConfigSetInfoBean implements Serializable {
    private String showName;
    private List<MyRadioDataInfoBean> dataInfoBeanList;
    private String defType;

    public ConfigSetInfoBean() {
        dataInfoBeanList=new ArrayList<>();
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public List<MyRadioDataInfoBean> getDataInfoBeanList() {
        return dataInfoBeanList;
    }

    public void setDataInfoBeanList(List<MyRadioDataInfoBean> dataInfoBeanList) {
        this.dataInfoBeanList = dataInfoBeanList;
    }

    public void add(MyRadioDataInfoBean dataInfoBean){
        this.dataInfoBeanList.add(dataInfoBean);
    }

    public String getDefType() {
        return defType;
    }

    public void setDefType(String defType) {
        this.defType = defType;
    }

    @Override
    public String toString() {
        return "ConfigSetInfoBean{" +
                "showName='" + showName + '\'' +
                ", dataInfoBeanList=" + dataInfoBeanList +
                '}';
    }
}
