package com.newbee.drawdevelopmenttool.config;

import android.app.Activity;
import android.text.TextUtils;

import com.lixiao.build.mybase.activity.userprivate.bean.UserPrivateAgreemeetInfoBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.config.type.AddContentHeadType;
import com.newbee.drawdevelopmenttool.config.type.ShowContentHeadType;
import com.newbee.drawdevelopmenttool.share.DrawShare;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/17 0017 16:10
 */
public class  MyDrawBoardConfig {
    private static final String tag="MyDrawBoardConfig>>>>";
    public static AddContentHeadType defAddContentHeadType=AddContentHeadType.USE_POPUPWINDOW;
    public static ShowContentHeadType defShowContentHeadType=ShowContentHeadType.RV;
    public static boolean defSearchNeedDelect=false;
    public static UserPrivateAgreemeetInfoBean userPrivateAgreemeetInfoBean;



    public static AddContentHeadType getAddContentHeadType() {
       try {
           String shareStr=DrawShare.getInstance().getString(tag+"addContentHeadType");
           if(!TextUtils.isEmpty(shareStr)){
                int index=Integer.valueOf(shareStr);
                return AddContentHeadType.values()[index];
           }
       }catch (Exception e){}
        return defAddContentHeadType;
    }

    public static void setAddContentHeadType(AddContentHeadType addContentHeadType) {
        if(null!=addContentHeadType){
            DrawShare.getInstance().putString(tag+"addContentHeadType",addContentHeadType.ordinal()+"");
        }
    }

    public static ShowContentHeadType getShowContentHeadType(){
        try {
            String shareStr=DrawShare.getInstance().getString(tag+"showContentHeadType");
            if(!TextUtils.isEmpty(shareStr)){
                int index=Integer.valueOf(shareStr);
                return ShowContentHeadType.values()[index];
            }
        }catch (Exception e){}
        return defShowContentHeadType;
    }

    public static void setShowContentHeadType(ShowContentHeadType showContentHeadType) {
        if(null!=showContentHeadType){
            DrawShare.getInstance().putString(tag+"showContentHeadType",showContentHeadType.ordinal()+"");
        }
    }


    public static void setSearchNeedDelect(boolean searchNeedDelect){
        DrawShare.getInstance().putString(tag+"searchNeedDelect",searchNeedDelect+"");
    }

    public static boolean getSerarchNeedDelect(){

        try {
            String shareStr=DrawShare.getInstance().getString(tag+"searchNeedDelect");
            if(!TextUtils.isEmpty(shareStr)){
                return Boolean.valueOf(shareStr);
            }
        }catch (Exception e){}
        return defSearchNeedDelect;
    }


}
