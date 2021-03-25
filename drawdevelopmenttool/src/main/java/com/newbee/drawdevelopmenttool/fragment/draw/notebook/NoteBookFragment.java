package com.newbee.drawdevelopmenttool.fragment.draw.notebook;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.application.BaseDrawModelApplication;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentInItBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawType;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawView;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.BaseDrawViewListen;
import com.newbee.drawdevelopmenttool.draw.base.DrawFunctionUtil;
import com.newbee.drawdevelopmenttool.fragment.draw.base.BaseDrawViewFragment;
import com.newbee.drawdevelopmenttool.fragment.draw.notebook.adapter.DrawingTitleAdapter;

import java.util.ArrayList;
import java.util.List;

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
            drawControlUtil.setDrawFunctionType(drawFunctionType);
//            drawFunctionSlect(drawFunctionType);
            switch (drawFunctionType){
                case PAGER_ADD:
                case TO_FIRST_PAGER:
                case TO_BOTTOM_PAGER:
                case LAST_PAGER:
                case NEXT_PAGER:
                    showTextPager();
                    break;
                case PREVIEW:
//                    toActivity(LookContentActivity.class, MyGson.getInstance().toGsonStr(contentBean));
                    break;
                case PUSH_OUT:
//                    toActivity(ContentPushOutSetActivity.class, MyGson.getInstance().toGsonStr(contentBean));
                    break;
            }
        }
    };

    private void showTextPager(){

        pagerNumbTV.setText(drawControlUtil.getPagerBean().getShowPagerNumb()+"/"+drawControlUtil.getPagerBean().getCountPagerNumb());
    }


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

        drawingTitleAdapter = new DrawingTitleAdapter(getContext(),MyDrawBoardConfig.canUseDrawTypeList, MyDrawBoardConfig.canUseFunctionTypeList, itemClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        titleRV.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void viewSetData() {
        titleRV.setAdapter(drawingTitleAdapter);
    }

    @Override
    protected void drawInitOkNowSetView(ContentInItBean inItBean, ContentPagerBean pagerBean) {
        int lastDrawTypeIndex=inItBean.getLastDrawTypeOrdinal();
        if(lastDrawTypeIndex>0&&lastDrawTypeIndex<BaseDrawType.values().length){
            BaseDrawType lastDrawType=BaseDrawType.values()[lastDrawTypeIndex];
            itemClick.itemClick(DrawFunctionUtil.useDrawTypeGetImgRs(lastDrawType),-1,lastDrawType);
        }
        showTextPager();




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
}
