package com.newbee.drawdevelopmenttool.activity.look;

import android.text.TextUtils;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.activity.BaseCompatActivity;

import com.newbee.drawdevelopmenttool.R;

import com.newbee.drawdevelopmenttool.adapters.LookContentAdapter;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/9/11 0011 12:17
 */
public class LookContentActivity extends BaseCompatActivity {

    private RecyclerView lookContentRV;
    private LookContentAdapter lookContentAdapter;
    private ContentHeadBean headBean;
    private LookContentAdapter.ItemClick itemClick=new LookContentAdapter.ItemClick() {
        @Override
        public void itemClick(int pagerNumb) {
            toActivity( BaseDrawModelApplication.getInstance().useContentHeadSelectClass(headBean), MyGson.getInstance().toGsonStr(headBean));
            finish();
        }

    };

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_look_content;
    }

    @Override
    public void initView() {
        lookContentRV=findViewById(R.id.rv_look_content);
    }

    @Override
    public void initData() {
        String intentString=getIntentString();
        if(!TextUtils.isEmpty(intentString)){
            headBean= MyGson.getInstance().fromJson(intentString, ContentHeadBean.class);
        }
        if(null==headBean){
            finish();
            return;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        lookContentRV.setLayoutManager(gridLayoutManager);
        lookContentAdapter=new LookContentAdapter(context,headBean,itemClick);
        lookContentRV.setAdapter(lookContentAdapter);
    }

    @Override
    public void initControl() {

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
    }
}
