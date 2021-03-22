package com.newbee.drawdevelopmenttool.fragment.head;


import android.view.View;

import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;

import com.newbee.drawdevelopmenttool.fragment.head.view.HeadFragmentShowContentView;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

public class HeadFavoritesFragment extends BaseHeadFragment {

    private HeadFragmentNeedListen headFragmentNeedListen=new HeadFragmentNeedListen() {
        @Override
        public void clickAdd(View v) {

        }

        @Override
        public void retrunFilePath() {
            doRetrunFile();
        }

        @Override
        public void needReSetFilePath(String filePath) {
            useFilePathQueList(filePath);
        }


    };



    @Override
    public int getViewLayout() {
        return R.layout.fragment_head_favorites;
    }



    @Override
    protected void initView() {

        headFragmentShowContentView = new HeadFragmentShowContentView(view,this, HeadFragmentShowContentType.FAVORITES, getActivity(), null, false, false,headFragmentNeedListen);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void viewIsShow() {
        ResultContentHeadBean resultContentHeadBean = ContentHeadSqlServer.getInstance().queByIsstar();
        if (null != headFragmentShowContentView) {
            headFragmentShowContentView.setResultContentHeadBean(resultContentHeadBean, false);
            headFragmentShowContentView.adapterNotify();
        }

    }

    @Override
    protected void viewIsPause() {

    }

    @Override
    protected void close() {
        if (null != headFragmentShowContentView) {
            headFragmentShowContentView.close();
            headFragmentShowContentView = null;
        }
    }

    @Override
    protected void changeConfig() {
        headFragmentShowContentView.setLayoutManager();
    }



}
