package com.newbee.drawdevelopmenttool.activity.wastepaper;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.wastepaper.adapter.WastepaperBasketContentAdapter;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.dialog.wastepaper.WastepaperBasketDialog;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventType;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/11/16 0016 16:54
 */
public class WastepaperBasketActivity extends BaseCompatActivity {
    private RecyclerView rv;
    private WastepaperBasketContentAdapter adapter;
    private ContentHeadBean nowBean;
    private WastepaperBasketContentAdapter.ItemClick itemClick=new WastepaperBasketContentAdapter.ItemClick() {
        @Override
        public void click(ContentHeadBean contentHeadBean) {
            nowBean=contentHeadBean;
            if(null!=nowBean){
                switch (nowBean.getEnumType()){
                    case NOTE_BOOK:

                        break;

                }
            }
        }

        @Override
        public void longClick(ContentHeadBean contentHeadBean) {
            wastepaperBasketDialog.show();
            nowBean=contentHeadBean;
        }
    };
    private WastepaperBasketDialog wastepaperBasketDialog;
    private WastepaperBasketDialog.ItemClick dialogItemClick=new WastepaperBasketDialog.ItemClick() {
        @Override
        public void pagerRetrun() {
            if(null==nowBean){
                return;
            }
            nowBean.setNeedDelect(false);
            if(ContentHeadSqlServer.getInstance().update(nowBean)){
                ContentHeadDoRbsEventBusSubscriptionSubject.getInstance().eventListen(ContentHeadDoRbsEventType.RETRUN_RBS,nowBean);
                showToast(BaseApplication.getRsString(R.string.wastepaper_retrun_ok));

            }else {
                showToast(BaseApplication.getRsString(R.string.wastepaper_retrun_err));
            }
            finish();
        }

        @Override
        public void delectQue() {
            if(null==nowBean||nowBean.getId()==-1){
                return;
            }
            if(ContentHeadSqlServer.getInstance().delectById(nowBean.getId())){
                showToast(BaseApplication.getRsString(R.string.wastepaper_que_delect_ok));
                finish();
            } else {
                showToast(BaseApplication.getRsString(R.string.wastepaper_que_delect_err));
            }
        }
    };


    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_wastepaper_basket;
    }

    @Override
    public void initView() {
        rv=findViewById(R.id.rv);

    }

    @Override
    public void initData() {
        setContentHeadRV();
        wastepaperBasketDialog=new WastepaperBasketDialog(context,dialogItemClick);
    }


    private void setContentHeadRV() {
        ResultContentHeadBean resultContentHeadBean= ContentHeadSqlServer.getInstance().queIsRbs();
        if(null==resultContentHeadBean||null==resultContentHeadBean.getContentHeadBeanList()){
            resultContentHeadBean=new ResultContentHeadBean();
        }
        adapter=new WastepaperBasketContentAdapter(context,resultContentHeadBean.getContentHeadBeanList(),itemClick);

        int needNumb=0;
        if(myWindowSet.screenIsLandscape()){
            needNumb=5;
        }else {
            needNumb=3;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, needNumb);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void initControl() {
        rv.setAdapter(adapter);
    }

    @Override
    public void closeActivity() {

    }

    @Override
    public void viewIsShow() {

    }

    @Override
    public void viewIsPause() {

    }

    @Override
    public void changeConfig() {
        setContentHeadRV();
    }
}
