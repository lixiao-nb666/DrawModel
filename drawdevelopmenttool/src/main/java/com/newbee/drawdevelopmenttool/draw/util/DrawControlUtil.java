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

    public void setDrawFunctionType(BaseDrawViewFunctionType drawFunctionType,Object... objects){
        if(null==baseDrawView||null==drawFunctionType) {
            return;
        }
        baseDrawView.setFunctionType(drawFunctionType,objects);
        switch (drawFunctionType){
            case PAGER_ADD:
                if(baseDrawView.canSaveOrOpen()){
                    int cpn=pagerBean.getCountPagerNumb();
                    pagerBean.setCountPagerNumb(cpn+1);
                    pagerBean.setShowPagerNumb(cpn+1);
                    pagerBean.saveToShare();
                    openFile();
                }
                break;
            case LAST_PAGER:
                if(baseDrawView.canSaveOrOpen()) {
                    int snl = pagerBean.getShowPagerNumb();
                    snl--;
                    if (snl < 1) {
                        snl = 1;
                    }
                    if (pagerBean.getShowPagerNumb() != snl) {
//                        save(SaveBitMapType.CHANGE_PAGER);
                       pagerBean.setShowPagerNumb(snl);
                       pagerBean.saveToShare();
                        openFile();

                    }
                }
                break;
            case NEXT_PAGER:
                if(baseDrawView.canSaveOrOpen()) {
                    int cpnn = pagerBean.getCountPagerNumb();
                    int snn = pagerBean.getShowPagerNumb();
                    snn++;
                    if (snn > cpnn) {
                        snn = cpnn;
                    }
                    if (pagerBean.getShowPagerNumb() != snn) {
//                        save(SaveBitMapType.CHANGE_PAGER);
                        pagerBean.setShowPagerNumb(snn);
                        pagerBean.saveToShare();
                        openFile();
                    }
                }
                break;
            case TO_FIRST_PAGER:
                if(baseDrawView.canSaveOrOpen()) {
                    int snf = 1;
                    if (pagerBean.getShowPagerNumb() != snf) {
//                        save(SaveBitMapType.CHANGE_PAGER);
                        pagerBean.setShowPagerNumb(snf);
                        pagerBean.saveToShare();
                        openFile();
                    }
                }
                break;
            case TO_BOTTOM_PAGER:
                if(baseDrawView.canSaveOrOpen()){
                    int snb=pagerBean.getCountPagerNumb();
                    if(pagerBean.getShowPagerNumb()!=snb){
//                        save(SaveBitMapType.CHANGE_PAGER);
                        pagerBean.setShowPagerNumb(snb);
                        pagerBean.saveToShare();
                        openFile();
                    }
                }



                break;
        }
    }


    public void openFile(){
        baseDrawView.setFunctionType(BaseDrawViewFunctionType.RESET_DRAW_IMG,headBean.getLocalFilePath(pagerBean.getShowPagerNumb()));
    }

    public void openFile(String filePath){
        baseDrawView.setFunctionType(BaseDrawViewFunctionType.RESET_DRAW_IMG,filePath);
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
