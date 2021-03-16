package com.newbee.drawdevelopmenttool.bean.content.share;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/12 0012 14:36
 */
public class ContentHeadDelectStatuShareManager {
    private final String tag=getClass().getName()+">>>>";
    private static ContentHeadDelectStatuShareManager contentHeadStatuShareManager;
    private String shareket="ContentHeadDelectStatu";


    private ContentHeadDelectStatuShareManager(){
    }

    public static ContentHeadDelectStatuShareManager getInstance(){
        if(null==contentHeadStatuShareManager){
            synchronized (ContentHeadDelectStatuShareManager.class){
                if(null==contentHeadStatuShareManager){
                    contentHeadStatuShareManager=new ContentHeadDelectStatuShareManager();
                }
            }
        }
        return contentHeadStatuShareManager;
    }




   

}
