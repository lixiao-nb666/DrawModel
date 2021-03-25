package com.newbee.drawdevelopmenttool.application;

import android.content.Context;

import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.down.config.XiaoGeDownLoaderConfig;
import com.lixiao.down.manager.XiaoGeDownLoaderManager;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawView;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/23 0023 11:09
 */
public abstract class BaseDrawModelApplication extends BaseApplication {
    protected abstract void initNeedDo();
    protected abstract void closeNeedDo();
    public abstract BaseDrawView getBaseDrawView(Context context);
    public abstract Class useContentHeadSelectClass(ContentHeadBean contentHead);

    private static BaseDrawModelApplication baseDrawModelApplication;
    public static BaseDrawModelApplication getInstance(){
        return baseDrawModelApplication;
    }



    @Override
    protected void init() {
        baseDrawModelApplication=this;
        XiaoGeDownLoaderConfig.needDownEncoded=false;
        XiaoGeDownLoaderManager.getInstance().startService(getContext());
        initNeedDo();
    }

    @Override
    protected void needClear(String str) {

    }

    @Override
    protected void close() {
        closeNeedDo();
        XiaoGeDownLoaderManager.getInstance().stopService();
    }


}
