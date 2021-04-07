package com.newbee.drawmodel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.userprivate.bean.UserPrivateAgreemeetInfoBean;
import com.newbee.drawdevelopmenttool.activity.notebook.NoteBookActivity;
import com.newbee.drawdevelopmenttool.activity.notebook.NoteBookLandscapeActivity;
import com.newbee.drawdevelopmenttool.activity.notebook.NoteBookPortraitActivity;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadOrientationType;
import com.newbee.drawdevelopmenttool.config.type.AddContentHeadType;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.config.type.ShowContentHeadType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawUserFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawView;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawViewListen;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/2/1 0001 11:03
 */
public class MyApplication extends BaseDrawModelApplication {
    @Override
    protected void initNeedDo() {
        initDrawModel();
    }

    @Override
    protected void closeNeedDo() {

    }

    @Override
    public BaseDrawView getBaseDrawView(final Context context) {
        return new BaseDrawView() {
            @Override
            public View getView() {
                View view=new View(context);
                ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(layoutParams);
                return view;
            }

            @Override
            public boolean canSaveOrOpen() {
                return true;
            }

            @Override
            public void setListen(BaseDrawViewListen listen) {
            }
            @Override
            public void setDrawType(BaseDrawType drawType, Object... objects) {
            }
            @Override
            public void setFunctionType(BaseDrawViewFunctionType functionType, Object... objects) {
                if(objects.length>=1){
                    LG.i(tag,"---------kankansetFunctionType1:"+functionType+"--"+objects[0]);
                }else {
                    LG.i(tag,"---------kankansetFunctionType2:"+functionType);
                }
            }
        };
    }

    @Override
    public Class useContentHeadSelectClass(ContentHeadBean contentHead) {
        try {
            switch (contentHead.getEnumType()){
                case NOTE_BOOK:
                    switch (ContentHeadOrientationType.values()[contentHead.getOrientation()]){
                        case AUTO:
                            return NoteBookActivity.class;
                        case horizotal:
                            return NoteBookLandscapeActivity.class;
                        case vercital:
                            return NoteBookPortraitActivity.class;
                    }
                    return null;
            }
            return null;
        }catch (Exception e){
        }
        return null;
    }



    private void initDrawModel(){
        MyDrawBoardConfig.defAddContentHeadType=AddContentHeadType.USE_POPUPWINDOW;
        MyDrawBoardConfig.defShowContentHeadType=ShowContentHeadType.PAGER;
        MyDrawBoardConfig.defSearchNeedDelect=false;
        UserPrivateAgreemeetInfoBean userPrivateAgreemeetInfoBean=new UserPrivateAgreemeetInfoBean();
        userPrivateAgreemeetInfoBean.setAppName("曼象笔记");
        userPrivateAgreemeetInfoBean.setComPanyName("东莞曼象科技有限");
        userPrivateAgreemeetInfoBean.setTime("2021/2/23");
        MyDrawBoardConfig.userPrivateAgreemeetInfoBean=userPrivateAgreemeetInfoBean;

        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.PEN);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.PENCIL);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.BALL_PEN);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.BRUSH_PEN);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.MARK_PEN);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.LINE);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.RECT);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.CIRCLE);
        MyDrawBoardConfig.canUseDrawTypeList.add(BaseDrawType.OVAL);
        //设置可以用的功能模块
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.SET_BACKGOUND);

        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.PREVIEW);
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.PUSH_OUT);
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.PAGER_ADD);
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.TO_FIRST_PAGER);
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.LAST_PAGER);
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.NEXT_PAGER);
        MyDrawBoardConfig.canUseDrawUserFunctionTypeList.add(BaseDrawUserFunctionType.TO_BOTTOM_PAGER);
        MyDrawBoardConfig.canUseDrawViewFunctionTypeList.add(BaseDrawViewFunctionType.DRAW_LINE_SELECT);
        MyDrawBoardConfig.canUseDrawViewFunctionTypeList.add(BaseDrawViewFunctionType.CAN_NOT_DRAW);
        MyDrawBoardConfig.canUseDrawViewFunctionTypeList.add(BaseDrawViewFunctionType.UNDO);
        MyDrawBoardConfig.canUseDrawViewFunctionTypeList.add(BaseDrawViewFunctionType.REDO);
        MyDrawBoardConfig.canUseDrawViewFunctionTypeList.add(BaseDrawViewFunctionType.CLEAR);
    }

}
