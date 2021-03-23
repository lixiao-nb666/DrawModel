package com.newbee.drawdevelopmenttool.fragment.head;

import android.text.TextUtils;
import android.view.View;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.util.ActivityUtil;
import com.lixiao.build.mybase.fragment.BaseFragmen_v4;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
import com.lixiao.build.mybase.popupwindow.BasePoputWindowListen;
import com.lixiao.build.mybase.popupwindow.util.PopupManagerUtil;
import com.lixiao.build.mybase.sqlite.event.SqlListenObserver;
import com.lixiao.build.mybase.sqlite.event.SqlListenSubscriptionSubject;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;

import com.newbee.drawdevelopmenttool.fragment.head.util.HeadFragmentUtil;
import com.newbee.drawdevelopmenttool.fragment.head.view.HeadFragmentShowContentView;
import com.newbee.drawdevelopmenttool.popupwindow.selectaddcontentheadtype.SelectAddContentHeadTypePopupWindow;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusObserver;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventType;


public class HeadManuscriptsFragment extends BaseHeadFragment {



    private PopupManagerUtil popupManagerUtil;
    private PopupManagerUtil.Listen popupManagerUtilListen= new PopupManagerUtil.Listen() {
        @Override
        public void err(String err) {

        }

        @Override
        public BasePopupWindow createPopupWindow(String popupType) {
            if(TextUtils.isEmpty(popupType)){
                return null;
            }
            if(popupType.equals(SelectAddContentHeadTypePopupWindow.class.getSimpleName())){
                return new SelectAddContentHeadTypePopupWindow(getContext(),basePoputWindowListen);
            }
            return null;
        }
    };
    private BasePoputWindowListen basePoputWindowListen=new BasePoputWindowListen() {
        @Override
        public void getWAndH(int w, int h) {

        }

        @Override
        public void event(String eventType, Object... objects) {

        }
    };
    private ContentHeadAddEventBusObserver contentHeadAddEventBusObserver=new ContentHeadAddEventBusObserver() {
        @Override
        public void eventListen(ContentHeadAddEventType eventType, Object... objects) {
            switch (eventType){

                case FAST_ADD_NOTE_BOOK:
                    addContentHeadBeanAndToActivity((ContentHeadBean) objects[0]);
                    break;
                case ADD_NOTE_BOOK:
                    addContentHeadBean((ContentHeadBean) objects[0]);
                    break;
            }
        }
    };







    private HeadFragmentNeedListen headFragmentNeedListen=new HeadFragmentNeedListen() {
        @Override
        public void clickAdd(View v) {
            popupManagerUtil.showByDown(SelectAddContentHeadTypePopupWindow.class.getSimpleName(), v,50,-120);
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
        return R.layout.fragment_head_manuscripts;
    }



    @Override
    protected void initView() {

        ResultContentHeadBean resultContentHeadBean = ContentHeadSqlServer.getInstance().queByFilePath(lastFilePath);
        if (null == resultContentHeadBean) {
            resultContentHeadBean = new ResultContentHeadBean();
        }
        headFragmentShowContentView = new HeadFragmentShowContentView(view,this, HeadFragmentShowContentType.MANUSCRIPTS, getActivity(), resultContentHeadBean, true, HeadFragmentUtil.needRetrunt(lastFilePath),headFragmentNeedListen);
    }

    @Override
    protected void initData() {
        popupManagerUtil=new PopupManagerUtil(popupManagerUtilListen);
    }

    @Override
    protected void initControl() {
        ContentHeadAddEventBusSubscriptionSubject.getInstance().addObserver(contentHeadAddEventBusObserver);

    }

    @Override
    protected void viewIsShow() {


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
        ContentHeadAddEventBusSubscriptionSubject.getInstance().delectObjserver(contentHeadAddEventBusObserver);

    }

    @Override
    protected void changeConfig() {

        if (null != headFragmentShowContentView) {
            headFragmentShowContentView.setLayoutManager();
        }
    }












    private void addContentHeadBeanAndToActivity(ContentHeadBean contentHeadBean) {

        contentHeadBean.setFilePath(lastFilePath);
        contentHeadBean.setCreateTime(System.currentTimeMillis());
        ContentHeadBean add= ContentHeadSqlServer.getInstance().add(contentHeadBean);
        if(null!=add){
           Class cls=BaseDrawModelApplication.getInstance().useContentHeadSelectClass(add);
           ActivityUtil.toActivity(getContext(),cls, MyGson.getInstance().toGsonStr(add));
       }
    }





    private void addContentHeadBean(ContentHeadBean contentHeadBean) {
            contentHeadBean.setFilePath(lastFilePath);
            contentHeadBean.setCreateTime(System.currentTimeMillis());
            ContentHeadSqlServer.getInstance().add(contentHeadBean);

    }







}
