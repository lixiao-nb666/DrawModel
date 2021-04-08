package com.newbee.drawdevelopmenttool.fragment.draw.notebook;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentInItBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawUserFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawView;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.DrawFunctionUtil;
import com.newbee.drawdevelopmenttool.fragment.draw.base.BaseDrawViewFragment;
import com.newbee.drawdevelopmenttool.fragment.draw.notebook.adapter.DrawingTitleAdapter;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 10:34
 */
public class NoteBookFragment extends BaseDrawViewFragment {
    private ImageView selectIv;
    private RecyclerView titleRV;
    private DrawingTitleAdapter drawingTitleAdapter;
    private TextView pagerNumbTV;
    private DrawingTitleAdapter.ItemClick itemClick = new DrawingTitleAdapter.ItemClick() {
        @Override
        public void itemClick(int rsId, int positon, BaseDrawType baseDrawType) {
            selectIv.setImageResource(rsId);
            drawControlUtil.setDrawType(baseDrawType);
        }

        @Override
        public void itemClick(int rsId, int positon,BaseDrawViewFunctionType drawFunctionType) {
            selectIv.setImageResource(rsId);
            drawControlUtil.setDrawViewFunction(drawFunctionType);
            switch (drawFunctionType){
                case CAN_NOT_DRAW:
                    drawingTitleAdapter.setCanDraw(drawControlUtil.getBaseDrawView().userCanDraw());
                    drawingTitleAdapter.notifyDataSetChanged();
                    break;
            }
        }

        @Override
        public void itemClick(int rsId, int positon, BaseDrawUserFunctionType drawUserFunctionType) {
            selectIv.setImageResource(rsId);
            drawControlUtil.setDrawUserFunctionType(drawUserFunctionType);
        }
    };




    public NoteBookFragment(ContentHeadBean contentHeadBean) {
        super(contentHeadBean);
    }

    @Override
    protected int setViewRs() {
        return R.layout.fragment_note_book;
    }

    @Override
    protected void bindView() {
        selectIv = view.findViewById(R.id.iv_now_select);
        titleRV = view.findViewById(R.id.rv_title);
//        taoZiDrawView = view.findViewById(R.id.tzdv);
        pagerNumbTV=view.findViewById(R.id.tv_pager_numb);
    }

    @Override
    protected BaseDrawView getBaseDrawView() {
        return BaseDrawModelApplication.getInstance().getBaseDrawView(getContext());
    }

    @Override
    protected void bindData() {

        drawingTitleAdapter = new DrawingTitleAdapter(getContext(),MyDrawBoardConfig.canUseDrawTypeList, MyDrawBoardConfig.canUseDrawViewFunctionTypeList,MyDrawBoardConfig.canUseDrawUserFunctionTypeList, itemClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        titleRV.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void viewSetData() {
        selectIv.setImageResource(DrawFunctionUtil.useDrawTypeGetImgRs(MyDrawBoardConfig.canUseDrawTypeList.get(0)));
        titleRV.setAdapter(drawingTitleAdapter);
    }

    @Override
    protected void drawInitOkNowSetView(ContentInItBean inItBean, ContentPagerBean pagerBean) {
        int lastDrawTypeIndex=inItBean.getLastDrawTypeOrdinal();
        if(lastDrawTypeIndex>0&&lastDrawTypeIndex<BaseDrawType.values().length){
            BaseDrawType lastDrawType=BaseDrawType.values()[lastDrawTypeIndex];
            itemClick.itemClick(DrawFunctionUtil.useDrawTypeGetImgRs(lastDrawType),-1,lastDrawType);
            drawingTitleAdapter.setSelectDrawTypeIndex(lastDrawTypeIndex);
        }
        drawControlUtil.openFile();
    }



    @Override
    protected void drawNeedShow() {

    }

    @Override
    protected void drawNeedPause() {

    }

    @Override
    protected void drawChangeConfig() {

    }

    @Override
    protected void drawClose() {

    }

    @Override
    protected void setShowPagerText(int showPagerNumb, int countPagerNumb) {
        if(null!=pagerNumbTV){
            pagerNumbTV.setText(showPagerNumb+"/"+countPagerNumb);
        }
        LG.i("setShowPagerText","setShowPagerText:"+showPagerNumb+"---"+countPagerNumb);
    }




}
