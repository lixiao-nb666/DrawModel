package com.newbee.drawdevelopmenttool.bean.content.share;

import android.text.TextUtils;

import com.lixiao.build.gson.MyGson;
import com.newbee.drawdevelopmenttool.share.DrawShare;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/12 0012 14:36
 */
public class ContentHeadStarStatuShareManager {
    private final String tag=getClass().getName()+">>>>";
    private static ContentHeadStarStatuShareManager contentHeadStatuShareManager;
    private String sharekey="ContentHeadStarStatu";
    private ResultContentHeadStarStatuBean resultContentHeadStarStatuBean;

    private ContentHeadStarStatuShareManager(){
        try {
            String shareStr= DrawShare.getInstance().getString(sharekey);
            resultContentHeadStarStatuBean= MyGson.getInstance().fromJson(shareStr,ResultContentHeadStarStatuBean.class);
        }catch (Exception e){

        }
        if(null==resultContentHeadStarStatuBean||null==resultContentHeadStarStatuBean.getList()){
            resultContentHeadStarStatuBean=new ResultContentHeadStarStatuBean();
        }

    }

    public static ContentHeadStarStatuShareManager getInstance(){
        if(null==contentHeadStatuShareManager){
            synchronized (ContentHeadStarStatuShareManager.class){
                if(null==contentHeadStatuShareManager){
                    contentHeadStatuShareManager=new ContentHeadStarStatuShareManager();
                }
            }
        }
        return contentHeadStatuShareManager;
    }


    public boolean queContentHeadIsStar(String contentHeadId){
        if(TextUtils.isEmpty(contentHeadId)){
            return false;
        }

        for(ContentHeadStarStatuBean contentHeadStarStatuBean:resultContentHeadStarStatuBean.getList()){
            if(ContentHeadStarType.CONTENT_HEAD==contentHeadStarStatuBean.getContentHeadStarType()&&contentHeadStarStatuBean.getContentHeadId()==contentHeadId){
                return true;
            }
        }
        return false;
    }

   

}
