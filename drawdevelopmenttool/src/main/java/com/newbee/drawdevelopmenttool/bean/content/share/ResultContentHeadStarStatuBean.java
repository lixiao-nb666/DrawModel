package com.newbee.drawdevelopmenttool.bean.content.share;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/9/21 0021 10:27
 */
public class ResultContentHeadStarStatuBean implements Serializable {
    private List<ContentHeadStarStatuBean>list;

    public ResultContentHeadStarStatuBean() {
        this.list=new ArrayList<>();
    }

    public ResultContentHeadStarStatuBean(List<ContentHeadStarStatuBean> list) {
        this.list = list;
    }

    public List<ContentHeadStarStatuBean> getList() {
        return list;
    }

    public void setList(List<ContentHeadStarStatuBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ResultContentHeadStarStatuBean{" +
                "list=" + list +
                '}';
    }

    public void add(ContentHeadStarStatuBean addBean){
        if(null==addBean||null==addBean.getContentHeadStarType()){
            return;
        }
        if(null==list){
            list=new ArrayList<>();
        }
        list.add(addBean);
    }


}
