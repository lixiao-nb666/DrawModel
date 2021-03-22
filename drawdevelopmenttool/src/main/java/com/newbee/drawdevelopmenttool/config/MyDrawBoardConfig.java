package com.newbee.drawdevelopmenttool.config;

import android.app.Activity;
import android.text.TextUtils;

import com.lixiao.build.mybase.activity.userprivate.bean.UserPrivateAgreemeetInfoBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.config.type.AddContentHeadType;
import com.newbee.drawdevelopmenttool.config.type.ShowContentHeadType;
import com.newbee.drawdevelopmenttool.share.DrawShare;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/17 0017 16:10
 */
public class  MyDrawBoardConfig {
    private final String tag=getClass().getSimpleName()+">>>>";
    private static MyDrawBoardConfig myDrawBoardConfig;
    private MyDrawBoardConfig(){}

    public static MyDrawBoardConfig getInstance(){
        if(null==myDrawBoardConfig){
            synchronized (MyDrawBoardConfig.class){
                if(null==myDrawBoardConfig){
                    myDrawBoardConfig=new MyDrawBoardConfig();
                }
            }
        }
        return myDrawBoardConfig;
    }

    private UserPrivateAgreemeetInfoBean userPrivateAgreemeetInfoBean;
    public void setUserPrivateAgreemeetInfoBean(UserPrivateAgreemeetInfoBean userPrivateAgreemeetInfoBean){
        this.userPrivateAgreemeetInfoBean=userPrivateAgreemeetInfoBean;
    }

    public UserPrivateAgreemeetInfoBean getUserPrivateAgreemeetInfoBean() {
        return userPrivateAgreemeetInfoBean;
    }



    public AddContentHeadType getAddContentHeadType() {
       try {
           String shareStr=DrawShare.getInstance().getString(tag+"addContentHeadType");
           if(!TextUtils.isEmpty(shareStr)){
                int index=Integer.valueOf(shareStr);
                return AddContentHeadType.values()[index];
           }
       }catch (Exception e){}
        return BaseDefDrawBoardSet.defAddContentHeadType;
    }

    public void setAddContentHeadType(AddContentHeadType addContentHeadType) {
        if(null!=addContentHeadType){
            DrawShare.getInstance().putString(tag+"addContentHeadType",addContentHeadType.ordinal()+"");
        }
    }

    public ShowContentHeadType getShowContentHeadType(){
        try {
            String shareStr=DrawShare.getInstance().getString(tag+"showContentHeadType");
            if(!TextUtils.isEmpty(shareStr)){
                int index=Integer.valueOf(shareStr);
                return ShowContentHeadType.values()[index];
            }
        }catch (Exception e){}
        return BaseDefDrawBoardSet.defShowContentHeadType;
    }

    public void setShowContentHeadType(ShowContentHeadType showContentHeadType) {
        if(null!=showContentHeadType){
            DrawShare.getInstance().putString(tag+"showContentHeadType",showContentHeadType.ordinal()+"");
        }
    }


    public void setSearchNeedDelect(boolean searchNeedDelect){
        DrawShare.getInstance().putString(tag+"searchNeedDelect",searchNeedDelect+"");
    }

    public boolean getSerarchNeedDelect(){

        try {
            String shareStr=DrawShare.getInstance().getString(tag+"searchNeedDelect");
            if(!TextUtils.isEmpty(shareStr)){
                return Boolean.valueOf(shareStr);
            }
        }catch (Exception e){}
        return BaseDefDrawBoardSet.defSearchNeedDelect;
    }


}
