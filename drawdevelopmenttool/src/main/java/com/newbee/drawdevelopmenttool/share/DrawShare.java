package com.newbee.drawdevelopmenttool.share;



import android.text.TextUtils;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.share.BaseShare;
import com.newbee.drawdevelopmenttool.bean.content.ContentBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;

public class DrawShare extends BaseShare {
    private static DrawShare mySharePreferences;


    private DrawShare() {

    }

    public static DrawShare getInstance() {
        if (mySharePreferences == null) {
            synchronized (DrawShare.class) {
                if (mySharePreferences == null) {
                    mySharePreferences = new DrawShare();
                }

            }
        }
        return mySharePreferences;
    }



    private final String openContentHeadListKey = "openContentHeadListKey";

    public ResultContentHeadBean getOpenContentHeadList(String tag) {
        try {
            String shareStr = getString(tag + openContentHeadListKey);
            return MyGson.getInstance().fromJson(shareStr, ResultContentHeadBean.class);
        } catch (Exception e) {
            return null;
        }
    }


    public void putOpenContentHeadList(String tag, ResultContentHeadBean resultContentHeadBean) {
        putString(tag + openContentHeadListKey, MyGson.getInstance().toGsonStr(resultContentHeadBean));
    }

    private final String contentKey = "contentKey";

    public void putContentBean(ContentBean contentBean) {
        if (null == contentBean) {
            return;
        }
        putString(contentKey + contentBean.getContentHeadBean().getId(), MyGson.getInstance().toGsonStr(contentBean));
    }

    public ContentBean getContentBean(ContentHeadBean contentHeadBean) {
        try {
            String shareStr = getString(contentKey + contentHeadBean.getId());
            return MyGson.getInstance().fromJson(shareStr, ContentBean.class);
        } catch (Exception e) {
            return null;
        }


    }

    private final String lastOpenActivity = "lastOpenActivity";

    public void putLastOpenActivity(Class cls) {

        putString(lastOpenActivity, cls.getName());
    }

    public Class getLastOpenActivity() {

        try {
            String str=getString(lastOpenActivity);

            return Class.forName(str);
        }catch (Exception e){
            return null;
        }


    }




    private final String headLastSelectStrKey = "headLastSelectStrKey";

    public void putHeadLastSelectStr(String headLastSelectStr) {
        putString(headLastSelectStrKey, headLastSelectStr);
    }

    public String getHeadLastSelectStr() {
        return getString(headLastSelectStrKey);
    }



    private final String titleSelectStrKey = "TitleSelectStrKey";

    public void putTitleSelectStr(String shareKey, String str) {
        if(TextUtils.isEmpty(shareKey)){
            return;
        }

        putString(shareKey+titleSelectStrKey, str);
    }

    public String getTitleSelectStr(String shareKey) {
        if(TextUtils.isEmpty(shareKey)){
            return "";
        }
        return getString(shareKey+titleSelectStrKey);
    }






    private final String showModelKey = "showModelKey";
    public void putShowModel(String shareKey, String str) {
        if(TextUtils.isEmpty(shareKey)){
            return ;
        }
        putString(shareKey+showModelKey, str);
    }

    public String getShowModel(String shareKey) {
        if(TextUtils.isEmpty(shareKey)){
            return "";
        }
        return getString(shareKey+showModelKey);
    }



    private final String contentMaxShowNumbKey = "contentMaxShowNumbKey";
    public void putContentMaxShowNumb(int maxShowNumb){
        putString(contentMaxShowNumbKey,maxShowNumb+"");
    }

    public int getContentMaxShowNumb(){
        try {
            String shareStr=getString(contentMaxShowNumbKey);
            return Integer.valueOf(shareStr);
        }catch (Exception e){
            return 0;
        }


    }


    final String appIndex = "appIndex";

    public void putAppIndex(String pagName,int index) {
        if(TextUtils.isEmpty(pagName)){
            return;
        }

        putString(pagName+appIndex, index+"");
    }

    public int getAppIndex(String pagName) {
        try {
            if(!TextUtils.isEmpty(pagName)){
                String shareStr = getString(pagName+appIndex);
                return Integer.valueOf(shareStr);
            }

        } catch (Exception e) {

        }
        return -1;
    }

}
