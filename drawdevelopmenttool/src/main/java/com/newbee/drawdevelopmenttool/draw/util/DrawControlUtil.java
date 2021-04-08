package com.newbee.drawdevelopmenttool.draw.util;


import android.content.Context;
import android.widget.FrameLayout;
import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.util.ActivityManager;
import com.lixiao.build.mybase.activity.util.ActivityUtil;
import com.lixiao.build.mybase.dialog.BaseDialog;
import com.lixiao.build.mybase.dialog.BaseDialogListen;
import com.lixiao.build.mybase.dialog.util.DialogManagerUtil;
import com.newbee.drawdevelopmenttool.activity.look.LookContentActivity;
import com.newbee.drawdevelopmenttool.activity.pushout.ContentPushOutSetActivity;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentInItBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.bean.content.bg.ContentBgUtil;
import com.newbee.drawdevelopmenttool.dialog.bg.SelectBgDialog;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawUserFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawView;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawViewListen;
import com.newbee.drawdevelopmenttool.share.DrawShare;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/25 0025 10:49
 */
public class DrawControlUtil {
    private final String tag=getClass().getSimpleName()+">>>>";
    private FrameLayout bgFL;
    private ContentHeadBean headBean;
    private ContentInItBean inItBean;
    private ContentPagerBean pagerBean;
    private BaseDrawView baseDrawView;
    private DialogManagerUtil dialogManagerUtil;
    private DialogManagerUtil.Listen dialogManagerUtilListen=new DialogManagerUtil.Listen() {
        @Override
        public void err(String err) {

        }

        @Override
        public BaseDialog createDialog(String dialogType) {
            if(dialogType.equals(SelectBgDialog.class.getSimpleName())){
                LG.i("kankanshowDialog","kankanshowDialog222:----------------");
                return new SelectBgDialog(useObjectGetContext(),dialogListen);
            }


            return null;
        }
    };
    private BaseDialogListen dialogListen=new BaseDialogListen() {
        @Override
        public void getWAndH(int w, int h) {

        }

        @Override
        public void event(String eventType, Object... objects) {
            if(eventType.equals(SelectBgDialog.class.getSimpleName())){
                setBG();
            }
        }
    };

    public DrawControlUtil(){
        headBean=new ContentHeadBean();
        inItBean=new ContentInItBean(-1);
        pagerBean=new ContentPagerBean(-1);
        dialogManagerUtil = new DialogManagerUtil(dialogManagerUtilListen);
    }



    public void setContentHeadBean(ContentHeadBean headBean) {
        this.headBean=headBean;
        this.inItBean= DrawShare.getInstance().getContentInItBean(headBean.getId());
        this.pagerBean=DrawShare.getInstance().getContentPagerBean(headBean.getId());
        dialogManagerUtil = new DialogManagerUtil(dialogManagerUtilListen);
    }

    private DrawControlUtilListen listen;



    public void setBaseDrawView(DrawControlUtilListen listen,FrameLayout fl, BaseDrawViewListen baseDrawViewListen) {
        this.listen=listen;
        this.bgFL=fl;
        baseDrawView=BaseDrawModelApplication.getInstance().getBaseDrawView(fl.getContext());
        fl.addView(baseDrawView.getView());
        this.baseDrawView.setListen(baseDrawViewListen);
        setBGAndNeedShowText();
    }

    private void setBG(){
        int bgRs= ContentBgUtil.getBgRs(headBean);
        bgFL.setBackgroundResource(bgRs);
    }

    private void setBGAndNeedShowText(){
        setBG();
        listen.needShowPagerText(pagerBean.getShowPagerNumb(),pagerBean.getCountPagerNumb());
    }

    public void setDrawType(BaseDrawType baseDrawType){
        if(null==baseDrawView||null==baseDrawType){
            return;
        }
        inItBean.setLastDrawTypeOrdinal(baseDrawType.ordinal());
        inItBean.saveToShare();
        baseDrawView.setDrawType(baseDrawType);
    }

    public void setDrawViewFunction(BaseDrawViewFunctionType drawViewFunctionType){
        if(null==baseDrawView||null==drawViewFunctionType){
            return;
        }
        baseDrawView.setFunctionType(drawViewFunctionType);

    }

    public void setDrawUserFunctionType(BaseDrawUserFunctionType drawUserFunctionType, Object... objects){
        if(null==baseDrawView||null==drawUserFunctionType) {
            return;
        }
        switch (drawUserFunctionType){
            case PAGER_ADD:
                if(baseDrawView.canSaveOrOpen()){
                    int cpn=pagerBean.getCountPagerNumb();
                    pagerBean.setCountPagerNumb(cpn+1);
                    pagerBean.setShowPagerNumb(cpn+1);
                    pagerBean.saveToShare();
                    openFile();
                    setBGAndNeedShowText();
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
                    setBGAndNeedShowText();
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
                    setBGAndNeedShowText();
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
                    setBGAndNeedShowText();
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
                    setBGAndNeedShowText();
                }
                break;
            case PREVIEW:
                Context previewContext= useObjectGetContext(objects);
                ActivityUtil.toActivity(previewContext, LookContentActivity.class, MyGson.getInstance().toGsonStr(headBean));
                break;
            case PUSH_OUT:
                Context pushOutContext= useObjectGetContext(objects);
                ActivityUtil.toActivity(pushOutContext,ContentPushOutSetActivity.class, MyGson.getInstance().toGsonStr(headBean));
                break;
            case SET_BACKGOUND:
                dialogManagerUtil.show(SelectBgDialog.class.getSimpleName(),headBean);
                break;
        }
    }

    private Context useObjectGetContext(Object... objects){
        Context context=null;
        try {
            if(objects.length>0){
                context= (Context) objects[0];
            }else {
                int appSize=ActivityManager.getInstance().getlist().size();
                if(appSize>0){
                    context=ActivityManager.getInstance().getlist().get(appSize-1);
                }
            }
        }catch (Exception e){}

        return context;
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
