package com.newbee.drawmodel;

import com.lixiao.build.mybase.activity.userprivate.bean.UserPrivateAgreemeetInfoBean;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.down.config.XiaoGeDownLoaderConfig;
import com.lixiao.down.manager.XiaoGeDownLoaderManager;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.config.type.AddContentHeadType;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.config.type.ShowContentHeadType;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/2/1 0001 11:03
 */
public class MyApplication extends BaseApplication {
    @Override
    protected void init() {
        XiaoGeDownLoaderConfig.needDownEncoded=false;
        XiaoGeDownLoaderManager.getInstance().startService(getContext());
        initDrawModel();

    }

    @Override
    protected void needClear(String str) {
    }

    @Override
    protected void close() {
        XiaoGeDownLoaderManager.getInstance().stopService();

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
        MyDrawBoardConfig.contentHeadToClassMap.put(ContentHeadType.NOTE_BOOK,DemoActivity.class);
    }

}
