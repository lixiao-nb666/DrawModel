package com.newbee.drawdevelopmenttool.activity.init;


import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.activity.util.ActivityManager;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.init.adapter.DrawBoardHeadContentAdapter;
import com.newbee.drawdevelopmenttool.bean.content.ContentBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/10/23 0023 15:51
 */
public class DrawBoardHeadActivity extends BaseCompatActivity {
    private RecyclerView rv;
    private DrawBoardHeadContentAdapter adapter;
    private DrawBoardHeadContentAdapter.ItemClick itemClick=new DrawBoardHeadContentAdapter.ItemClick() {
        @Override
        public void click(ContentHeadBean contentHeadBean) {
            ActivityManager.getInstance().finishAllActivity();
            ContentBean contentBean=MySharePreferences.getInstance().getContentBean(contentHeadBean);
            toActivity(DrawBoardLauncherActivity.class, MyGson.getInstance().toGsonStr(contentBean));
        }

        @Override
        public void longClick(ContentHeadBean contentHeadBean) {
            if(null==updateContentHeadDialog){
                updateContentHeadDialog=new UpdateContentHeadDialog(context,updateContentHeadDialogItemClick);
            }
            updateContentHeadDialog.show(contentHeadBean);
        }
    };
    private UpdateContentHeadDialog updateContentHeadDialog;
    private UpdateContentHeadDialog.ItemClick updateContentHeadDialogItemClick=new UpdateContentHeadDialog.ItemClick() {
        @Override
        public void needFareshView() {
            setContentHeadRV();
        }
    };

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_draw_board_head;
    }

    @Override
    public void initView() {
        rv=findViewById(R.id.rv);
    }

    @Override
    public void initData() {
        updateContentHeadDialog=new UpdateContentHeadDialog(context,updateContentHeadDialogItemClick);
        setContentHeadRV();
    }


    private void setContentHeadRV() {
        ResultContentHeadBean resultContentHeadBean=ContentHeadSqlServer.getInstance().queByType(ContentHeadType.DRAW_BOARD.ordinal());
        LG.i(tag,"==========kankanshuju:"+resultContentHeadBean.getContentHeadBeanList());
        if(null==resultContentHeadBean||null==resultContentHeadBean.getContentHeadBeanList()){
            resultContentHeadBean=new ResultContentHeadBean();
        }else {
            resultContentHeadBean=resultContentHeadBean.checkNeedDelectGetList();
        }
        adapter=new DrawBoardHeadContentAdapter(context,resultContentHeadBean.getContentHeadBeanList(),itemClick);

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
