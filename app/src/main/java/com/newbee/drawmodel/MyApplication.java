package com.newbee.drawmodel;

import com.lixiao.build.mybase.activity.userprivate.bean.UserPrivateAgreemeetInfoBean;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.down.config.XiaoGeDownLoaderConfig;
import com.lixiao.down.manager.XiaoGeDownLoaderManager;
import com.newbee.drawdevelopmenttool.config.BaseDefDrawBoardSet;
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
        BaseDefDrawBoardSet.defAddContentHeadType=AddContentHeadType.USE_POPUPWINDOW;
        BaseDefDrawBoardSet.defShowContentHeadType=ShowContentHeadType.PAGER;
        BaseDefDrawBoardSet.defSearchNeedDelect=false;
        UserPrivateAgreemeetInfoBean userPrivateAgreemeetInfoBean=new UserPrivateAgreemeetInfoBean();
        userPrivateAgreemeetInfoBean.setAppName("曼象笔记");
        userPrivateAgreemeetInfoBean.setComPanyName("东莞曼象科技有限");
        userPrivateAgreemeetInfoBean.setTime("2021/2/23");
        MyDrawBoardConfig.getInstance().setUserPrivateAgreemeetInfoBean(userPrivateAgreemeetInfoBean);

    }

}
