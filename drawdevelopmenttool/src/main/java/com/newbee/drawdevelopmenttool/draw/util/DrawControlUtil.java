package com.newbee.drawdevelopmenttool.draw.util;


import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.newbee.drawdevelopmenttool.activity.init.DrawingHeadActivity;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentInItBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawView;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawViewListen;
import com.newbee.drawdevelopmenttool.draw.base.DrawFunctionUtil;
import com.newbee.drawdevelopmenttool.fragment.head.HeadFavoritesFragment;
import com.newbee.drawdevelopmenttool.share.DrawShare;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/25 0025 10:49
 */
public class DrawControlUtil {
    private ContentHeadBean headBean;
    private ContentInItBean inItBean;
    private ContentPagerBean pagerBean;
    private BaseDrawView baseDrawView;


    public DrawControlUtil(){
        headBean=new ContentHeadBean();
        inItBean=new ContentInItBean(-1);
        pagerBean=new ContentPagerBean(-1);
    }



    public void setContentHeadBean(ContentHeadBean headBean) {
        this.headBean=headBean;
        this.inItBean= DrawShare.getInstance().getContentInItBean(headBean.getId());
        this.pagerBean=DrawShare.getInstance().getContentPagerBean(headBean.getId());
    }



    public void setBaseDrawView(LinearLayout ll, BaseDrawViewListen baseDrawViewListen) {
        baseDrawView=BaseDrawModelApplication.getInstance().getBaseDrawView(ll.getContext());
        ll.addView(baseDrawView.getView());
        this.baseDrawView.setListen(baseDrawViewListen);
    }

    public void setDrawType(BaseDrawType baseDrawType){
        if(null==baseDrawView||null==baseDrawType){
            return;
        }
        inItBean.setLastDrawTypeOrdinal(baseDrawType.ordinal());
        inItBean.saveToShare();
        baseDrawView.setDrawType(baseDrawType);
    }

    public void setDrawFunctionType(BaseDrawViewFunctionType drawFunctionType){
        if(null==baseDrawView||null==drawFunctionType) {
            return;
        }
        baseDrawView.setFunctionType(drawFunctionType);
    }

    public ContentHeadBean getHeadBean() {
        return headBean;
    }

    public ContentInItBean getInItBean() {
        return inItBean;
    }

    public ContentPagerBean getPagerBean() {
        return pagerBean;
    }

    public BaseDrawView getBaseDrawView() {
        return baseDrawView;
    }
}
