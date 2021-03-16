package com.newbee.drawdevelopmenttool.util.share;



import com.lixiao.build.gson.MyGson;
import com.newbee.taozinoteboard.bean.content.ContentBean;
import com.newbee.taozinoteboard.bean.content.ContentHeadBean;
import com.newbee.taozinoteboard.bean.content.ResultContentHeadBean;
import com.newbee.taozinoteboard.contentresolver.SmartViewResolver;

public class DrawSharePreferences extends {
    private static DrawSharePreferences mySharePreferences;


    private DrawSharePreferences() {

    }

    public static DrawSharePreferences getInstance() {
        if (mySharePreferences == null) {
            synchronized (DrawSharePreferences.class) {
                if (mySharePreferences == null) {
                    mySharePreferences = new DrawSharePreferences();
                }

            }
        }
        return mySharePreferences;
    }


    // 清除共享数据
    public void editorClear() {
        CannotDel
        SmartViewResolver.deleteAll();
    }

    public void putString(String K, String V) {
        SmartViewResolver.put(K, V);
    }


    public String getString(String K) {
        return SmartViewResolver.getV(K);
    }


//    private final String manuscriptsContentHeadListKey = "manuscriptsContentHeadListKey";
//
//    public ResultContentHeadBean getManuscriptsContentHeadList() {
//        try {
//            String shareStr = getString(manuscriptsContentHeadListKey);
//            return MyGson.getInstance().fromJson(shareStr, ResultContentHeadBean.class);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//
//    public void putManuscriptsContentHeadList(ResultContentHeadBean resultContentHeadBean) {
//        putString(manuscriptsContentHeadListKey, MyGson.getInstance().toGsonStr(resultContentHeadBean));
//    }


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

}
