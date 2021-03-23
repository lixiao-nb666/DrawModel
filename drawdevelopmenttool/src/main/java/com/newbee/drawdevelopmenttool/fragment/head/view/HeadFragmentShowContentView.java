package com.newbee.drawdevelopmenttool.fragment.head.view;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.MyWindowSet;
import com.lixiao.build.mybase.ToastUtil;
import com.lixiao.build.mybase.activity.util.ActivityUtil;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
import com.lixiao.build.mybase.popupwindow.BasePoputWindowListen;
import com.lixiao.build.mybase.popupwindow.util.PopupManagerUtil;
import com.lixiao.build.mybase.sqlite.event.SqlListenObserver;
import com.lixiao.build.mybase.sqlite.event.SqlListenSubscriptionSubject;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.contentchange.ContentHeadChangeActivity;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadSortType;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.eventbus.EventBusObserver;
import com.newbee.drawdevelopmenttool.eventbus.EventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.eventbus.EventType;
import com.newbee.drawdevelopmenttool.fragment.head.BaseHeadFragment;
import com.newbee.drawdevelopmenttool.fragment.head.HeadFragmentNeedListen;
import com.newbee.drawdevelopmenttool.fragment.head.adapter.BaseContentHeadAdapter;
import com.newbee.drawdevelopmenttool.fragment.head.adapter.ManuscriptsContentAdapter;
import com.newbee.drawdevelopmenttool.fragment.head.adapter.ManuscriptsContentPagerAdapter;
import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;
import com.newbee.drawdevelopmenttool.fragment.head.content.ManuscriptsContentShowModelType;
import com.newbee.drawdevelopmenttool.popupwindow.contentchange.ContentHeadChangePopupWindow;
import com.newbee.drawdevelopmenttool.popupwindow.selectaddcontentheadtype.SelectAddContentHeadTypePopupWindow;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;
import com.newbee.drawdevelopmenttool.util.draw.thread.SaveBitMapConfig;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventBusObserver;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventType;

import java.io.File;


public class HeadFragmentShowContentView {
    private final String tag=getClass().getSimpleName()+">>>>";
    private TextView autoTV, dateTV, nameTV;
    private String titleSelectStr;
    private ImageView showModelIV;
    private ManuscriptsContentShowModelType showModelType;
    private RecyclerView contentRV;
    private ResultContentHeadBean resultContentHeadBean;
    private BaseContentHeadAdapter listAdapter;
    private Activity activity;
    private HeadFragmentShowContentType contentType;
    private String shareKey;
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
                return new SelectAddContentHeadTypePopupWindow(contentRV.getContext(),basePoputWindowListen);
            }else if(popupType.equals(ContentHeadChangePopupWindow.class.getSimpleName())){
                return new ContentHeadChangePopupWindow(contentRV.getContext());
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

    private ManuscriptsContentAdapter.ItemClick itemClick=new ManuscriptsContentAdapter.ItemClick() {
        @Override
        public void itemClick(View v, int position, ContentHeadBean contentHeadBean, ManuscriptsContentAdapter.ItemClickType itemClickType) {
            try {
                switch (itemClickType) {
                    case ADD:
                        headFragmentNeedListen.clickAdd(v);

                        break;
                    case RETRUN:
                        headFragmentNeedListen.retrunFilePath();
                        break;
                    case CONTENT:
                        if(SaveBitMapConfig.nowSaveFileMap.size()>0){
                            ToastUtil.showToast(BaseApplication.getRsString(R.string.saving_please_wait)+":"+SaveBitMapConfig.nowSaveFileMap);
                            return;
                        }

                        switch (contentHeadBean.getEnumType()) {
                            case FILE_FOLDER:
                                String  lastFilePath = contentHeadBean.getFilePath() + contentHeadBean.getId() + File.separator;
                                headFragmentNeedListen.needReSetFilePath(lastFilePath);
                                break;
                            default:
                                Class toCls= BaseDrawModelApplication.getInstance().useContentHeadSelectClass(contentHeadBean);
                                if(null==toCls){
                                    ToastUtil.showToast(BaseApplication.getRsString(R.string.no_to_class));
                                }else {
                                    ActivityUtil.toActivity(activity,toCls,MyGson.getInstance().toGsonStr(contentHeadBean));
                                }

                                break;
                        }
                        break;
                }
            } catch (Exception e) {
            }
        }
        @Override
        public void itemLongClick(View v,ContentHeadBean contentHeadBean) {
            if(null!=contentHeadBean){
                switch (MyDrawBoardConfig.getAddContentHeadType()){
                    case USE_POPUPWINDOW:
                       popupManagerUtil.showByCenter(ContentHeadChangePopupWindow.class.getSimpleName(),v,0,0,contentHeadBean);
                        break;
                    default:
                        ActivityUtil.toActivity(activity,ContentHeadChangeActivity.class, MyGson.getInstance().toGsonStr(contentHeadBean));
                        break;
                }

           }
        }
    };
    private boolean adapterNeedAddContent;
    private boolean adapterNeedRetrun;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.tv_manuscripts_auto){
                setTitleStr(BaseApplication.getRsString(R.string.manuscripts_auto));
            }else if(v.getId()==R.id.tv_manuscripts_date){
                setTitleStr(BaseApplication.getRsString(R.string.manuscripts_date));
            }else if(v.getId()==R.id.tv_manuscripts_name){
                setTitleStr(BaseApplication.getRsString(R.string.manuscripts_name));
            }else if(v.getId()==R.id.iv_show_model){
                if(null==showModelType){
                    showModelType=ManuscriptsContentShowModelType.GRID;
                }
                switch (showModelType){
                    case GRID:
                        showModelType=ManuscriptsContentShowModelType.LIST;
                        break;
                    case LIST:
                        showModelType=ManuscriptsContentShowModelType.GRID;
                        break;
                }
                DrawShare.getInstance().putShowModel(shareKey, showModelType.ordinal()+"");
                setShowModel();
                initSetRV();
            }
        }
    };

    private ContentHeadDoRbsEventBusObserver doRbsEventBusObserver=new ContentHeadDoRbsEventBusObserver() {
        @Override
        public void eventListen(ContentHeadDoRbsEventType eventType, Object... objects) {
            switch (eventType){
                case TO_RBS:
                        ContentHeadBean delectContentHeadBean = (ContentHeadBean) objects[0];
                        int removePostion = resultContentHeadBean.getPosition(delectContentHeadBean);
                        if(removePostion!=-1){
                            resultContentHeadBean.getContentHeadBeanList().remove(removePostion);
                            listAdapter.notifyDataSetChanged();
                        }
                    break;
                case RETRUN_RBS:
                    ContentHeadBean retrunContentHeadBean = (ContentHeadBean) objects[0];
                    if(null!=retrunContentHeadBean){
                        switch (contentType){
                            case MANUSCRIPTS:
                                if(!TextUtils.isEmpty(retrunContentHeadBean.getFilePath())&&baseHeadFragment.getLastFilePath().equals(retrunContentHeadBean.getFilePath())){
                                    addContentHeadBean(retrunContentHeadBean);
                                }
                                break;
                            case FAVORITES:
                                if(retrunContentHeadBean.isStar()){
                                    addContentHeadBean(retrunContentHeadBean);
                                }
                                break;
                        }
                    }
                    break;
            }
        }
    };



    private HeadFragmentNeedListen headFragmentNeedListen;
    private HeadFragmentShowContentPagerView pagerView;
    private SqlListenObserver sqlListenObserver=new SqlListenObserver() {
        @Override
        public void add(Class cls, Object object, boolean isOk) {
            if(adapterNeedAddContent){
                if(cls==ContentHeadBean.class){
                    ContentHeadBean contentHeadBean= (ContentHeadBean) object;
                    LG.i(tag,"kankandateadd:"+contentHeadBean);
                    if (null == contentHeadBean) {
                        return;
                    }
                    addContentHeadBean(contentHeadBean);
                }
            }

        }

        @Override
        public void remove(Class cls, String key, String vue, boolean isOk) {

        }

        @Override
        public void update(Class cls, Object object, boolean isOk) {
            if(cls==ContentHeadBean.class) {
                ContentHeadBean updateContentHeadBean = (ContentHeadBean) object;
                int updatePositon = resultContentHeadBean.getPosition(updateContentHeadBean);
                updatePositon=updatePositon%listAdapter.getCanShowMaxNumb();
                switch (contentType){
                    case MANUSCRIPTS:
                        if (updatePositon != -1) {
                            resultContentHeadBean.getContentHeadBeanList().remove(updatePositon);
                            resultContentHeadBean.getContentHeadBeanList().add(updatePositon, updateContentHeadBean);
                            listAdapter.notifyItemChanged(updatePositon+1);
                        }
                        break;
                    case FAVORITES:
                        setResultContentHeadBean(ContentHeadSqlServer.getInstance().queByIsstar(),false);
                        break;
                    case SEARCH:
                        if (updatePositon != -1) {
                            resultContentHeadBean.getContentHeadBeanList().remove(updatePositon);
                            resultContentHeadBean.getContentHeadBeanList().add(updatePositon, updateContentHeadBean);
                            listAdapter.notifyItemChanged(updatePositon);
                        }
                        break;
                }
            }

        }

        @Override
        public void err(String errStr) {
            LG.i(tag,"--------kankanerrStr:"+errStr);
        }
    };
    private BaseHeadFragment baseHeadFragment;

    public HeadFragmentShowContentView(View view, BaseHeadFragment baseHeadFragment, HeadFragmentShowContentType contentType, Activity activity, ResultContentHeadBean initResultContenHeadBean, boolean adapterNeedAddContent, boolean adapterNeedRetrun, HeadFragmentNeedListen headFragmentNeedListen) {
        this.contentType=contentType;
        this.baseHeadFragment=baseHeadFragment;
        this.shareKey = contentType.toString();
        this.activity = activity;
        this.resultContentHeadBean=initResultContenHeadBean;
        this.adapterNeedAddContent = adapterNeedAddContent;
        this.adapterNeedRetrun = adapterNeedRetrun;
        this.headFragmentNeedListen=headFragmentNeedListen;
        popupManagerUtil=new PopupManagerUtil(popupManagerUtilListen);
        autoTV = view.findViewById(R.id.tv_manuscripts_auto);
        dateTV = view.findViewById(R.id.tv_manuscripts_date);
        nameTV = view.findViewById(R.id.tv_manuscripts_name);
        showModelIV = view.findViewById(R.id.iv_show_model);
        contentRV = view.findViewById(R.id.rv_content);
        switch (MyDrawBoardConfig.getShowContentHeadType()){
            case PAGER:
              pagerView=  new HeadFragmentShowContentPagerView(view.findViewById(R.id.ll_show_pager));
                break;
        }
        initData();
        ContentHeadDoRbsEventBusSubscriptionSubject.getInstance().addObserver(doRbsEventBusObserver);
        SqlListenSubscriptionSubject.getInstence().attach(sqlListenObserver);
    }

    public void close() {
        if(null!=pagerView){
            pagerView.close();
        }
        ContentHeadDoRbsEventBusSubscriptionSubject.getInstance().delectObjserver(doRbsEventBusObserver);
        SqlListenSubscriptionSubject.getInstence().detach(sqlListenObserver);
    }

    private void initData() {
        titleSelectStr = DrawShare.getInstance().getTitleSelectStr(shareKey);
        if (TextUtils.isEmpty(titleSelectStr)) {
            titleSelectStr = dateTV.getText().toString();
        }
        String  showModelStr= DrawShare.getInstance().getShowModel(shareKey);
        try {
            int showModelInt=Integer.parseInt(showModelStr);
            showModelType=ManuscriptsContentShowModelType.values()[showModelInt];


        }catch (Exception e){

        }finally {
            if(null==showModelType){
                showModelType=ManuscriptsContentShowModelType.GRID;
            }
        }
        if (null == resultContentHeadBean) {
            resultContentHeadBean = new ResultContentHeadBean();
        }
        initSetRV();
        initControl();
    }

    private void initControl() {
        setTitle(true);
        setShowModel();
        autoTV.setOnClickListener(onClickListener);
        nameTV.setOnClickListener(onClickListener);
        dateTV.setOnClickListener(onClickListener);
        showModelIV.setOnClickListener(onClickListener);
    }

    private void initSetRV() {
        setLayoutManager();
        switch (MyDrawBoardConfig.getShowContentHeadType()){
            case PAGER:
                listAdapter = new ManuscriptsContentPagerAdapter(activity,contentType, resultContentHeadBean.getContentHeadBeanList(), itemClick, showModelType, adapterNeedAddContent, adapterNeedRetrun,pagerView);
                break;
            default:
                listAdapter = new ManuscriptsContentAdapter(activity,contentType, resultContentHeadBean.getContentHeadBeanList(), itemClick, showModelType, adapterNeedAddContent, adapterNeedRetrun);
                break;
        }
        contentRV.setAdapter(listAdapter);
    }

    public void setLayoutManager() {
        LG.i(getClass().getName() + ">>>>", "------------setLayoutManager");
        MyWindowSet myWindowSet = new MyWindowSet(activity);
        int setShowNumb;
        if(null==showModelType){
            showModelType=ManuscriptsContentShowModelType.GRID;
        }
        switch (showModelType){
            case LIST:
                setShowNumb = 1;
                break;
            case GRID:

            default:
                if (myWindowSet.screenIsLandscape()) {
                    setShowNumb = 5;
                } else {
                    setShowNumb = 3;
                }
                break;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, setShowNumb);
        contentRV.setLayoutManager(gridLayoutManager);

    }

    private void setTitle(boolean needSort) {
        if (BaseApplication.getRsString(R.string.manuscripts_date).equals(titleSelectStr)) {
            dateTV.setBackgroundResource(R.drawable.manuscripts_title_select);
            nameTV.setBackgroundResource(0);
            autoTV.setBackgroundResource(0);
        } else if (BaseApplication.getRsString(R.string.manuscripts_name).equals(titleSelectStr)) {
            dateTV.setBackgroundResource(0);
            nameTV.setBackgroundResource(R.drawable.manuscripts_title_select);
            autoTV.setBackgroundResource(0);
        } else if (BaseApplication.getRsString(R.string.manuscripts_auto).equals(titleSelectStr)) {
            dateTV.setBackgroundResource(0);
            nameTV.setBackgroundResource(0);
            autoTV.setBackgroundResource(R.drawable.manuscripts_title_select);
        }
        if (needSort) {
            setTitleSort(false);
        }

    }

    private void setTitleSort(boolean needResever) {

        if (BaseApplication.getRsString(R.string.manuscripts_date).equals(titleSelectStr)) {
            resultContentHeadBean.sortBy(ContentHeadSortType.DATE, needResever);
        } else if (BaseApplication.getRsString(R.string.manuscripts_name).equals(titleSelectStr)) {
            resultContentHeadBean.sortBy(ContentHeadSortType.NAME, needResever);
        } else if (BaseApplication.getRsString(R.string.manuscripts_auto).equals(titleSelectStr)) {
            resultContentHeadBean.sortBy(ContentHeadSortType.AUTO, needResever);
        }
        listAdapter.resetList(resultContentHeadBean.getContentHeadBeanList());
        listAdapter.notifyDataSetChanged();
    }


    private void setTitleStr(String titleStr) {
        if (TextUtils.isEmpty(titleStr)) {
            return;
        }
        if (titleStr.equals(titleSelectStr)) {
            setTitleSort(true);
        } else {
            titleSelectStr = titleStr;
            DrawShare.getInstance().putTitleSelectStr(shareKey, titleSelectStr);
            setTitle(true);
        }
    }

    private void setShowModel() {
        if(null==showModelType){
            showModelType=ManuscriptsContentShowModelType.GRID;
        }
        switch (showModelType){
            case LIST:
                showModelIV.setBackgroundResource(R.drawable.manuscripts_show_model_1);
                break;
            case GRID:
            default:
                showModelIV.setBackgroundResource(R.drawable.manuscripts_show_model_2);
                break;
        }
    }

    public void setResultContentHeadBean(ResultContentHeadBean resultContentHeadBean, boolean needRetrun) {
        LG.i(titleSelectStr, "resetList5");
        this.resultContentHeadBean =resultContentHeadBean;
        if(null==this.resultContentHeadBean){
            this.resultContentHeadBean=new ResultContentHeadBean();
        }
        listAdapter.setNeedRetrun(needRetrun);
        setTitleSort(false);
    }

    private void addContentHeadBean(ContentHeadBean contentHeadBean) {
        if(contentHeadBean.isNeedDelect()){
            return;
        }

        if (null == resultContentHeadBean) {
            resultContentHeadBean = new ResultContentHeadBean();
        }
        resultContentHeadBean.add(contentHeadBean);
        setTitleSort(false);
    }


    long notifyTime=0;
    private Runnable adapterNotifyRunnable=new Runnable() {
        @Override
        public void run() {
            listAdapter.notifyDataSetChanged();
        }
    };
    public void adapterNotify(){
        LG.i(shareKey,"---------------adapterNotify");
        if(notifyTime==0){
            notifyTime=System.currentTimeMillis();
            return;
        }
        if(System.currentTimeMillis()-notifyTime<1111){
            return;
        }
        notifyTime=System.currentTimeMillis();
        if(null!=listAdapter){
            contentRV.removeCallbacks(adapterNotifyRunnable);
            contentRV.postDelayed(adapterNotifyRunnable,888);
        }
    }






}
