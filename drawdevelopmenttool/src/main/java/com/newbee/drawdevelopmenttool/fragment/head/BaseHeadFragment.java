package com.newbee.drawdevelopmenttool.fragment.head;

import android.text.TextUtils;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.fragment.BaseFragmen_v4;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.fragment.head.util.HeadFragmentUtil;
import com.newbee.drawdevelopmenttool.fragment.head.view.HeadFragmentShowContentView;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

import java.io.File;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/19 0019 20:10
 */
public abstract class BaseHeadFragment extends BaseFragmen_v4 {
    protected final String tag=getClass().getSimpleName()+">>>>";
    public abstract int getViewLayout();
    protected HeadFragmentShowContentView headFragmentShowContentView;
    protected String lastFilePath;

    public String getLastFilePath() {
        return lastFilePath;
    }

    @Override
    protected int initViewLayout() {
        lastFilePath = DrawShare.getInstance().getString(tag+"_lastFilePath");
        if (TextUtils.isEmpty(lastFilePath)) {
            lastFilePath = File.separator;
        }
        return getViewLayout();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void viewIsShow() {

    }

    @Override
    protected void viewIsPause() {

    }

    @Override
    protected void close() {

    }

    @Override
    protected void changeConfig() {

    }

    protected void doRetrunFile(){
        String[] strs = lastFilePath.split(File.separator);
        if (strs.length > 1) {
            String useFilePath = lastFilePath.substring(0, lastFilePath.lastIndexOf(strs[strs.length - 1]));
            useFilePathQueList(useFilePath);
        }
    }

    protected void useFilePathQueList(String filePath){
        lastFilePath=filePath;
        DrawShare.getInstance().putString(tag+"_lastFilePath",lastFilePath);
        setResultContentHeadBean(ContentHeadSqlServer.getInstance().queByFilePath(lastFilePath));
    }

    protected void setResultContentHeadBean(ResultContentHeadBean resetBeans) {
        LG.i(tag, "resetList2");
        if (null != headFragmentShowContentView) {
            LG.i(tag, "resetList4");
            headFragmentShowContentView.setResultContentHeadBean(resetBeans, HeadFragmentUtil.needRetrunt(lastFilePath));
        }
    }
}
