package com.newbee.drawdevelopmenttool.fragment.draw.base;

import android.widget.FrameLayout;

import com.lixiao.build.mybase.fragment.BaseFragmen_v4;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentInItBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawView;
import com.newbee.drawdevelopmenttool.draw.base.type.BaseDrawViewFunctionType;
import com.newbee.drawdevelopmenttool.draw.base.view.BaseDrawViewListen;
import com.newbee.drawdevelopmenttool.draw.type.OpenFileNeedDoType;
import com.newbee.drawdevelopmenttool.draw.util.DrawControlUtil;
import com.newbee.drawdevelopmenttool.draw.util.DrawControlUtilListen;
import com.newbee.drawdevelopmenttool.draw.type.SaveBitMapType;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 9:23
 */
public abstract class BaseDrawViewFragment extends BaseFragmen_v4 implements DrawFragmentImp {


    private BaseDrawViewListen baseDrawViewListen=new BaseDrawViewListen() {
        @Override
        public void initOk() {
            drawInitOkNowSetView(drawControlUtil.getInItBean(),drawControlUtil.getPagerBean());
        }

        @Override
        public void save(String fileName, String filePath, boolean isSave, String otherStr, SaveBitMapType saveBitMapType) {

        }

        @Override
        public void err(String str) {

        }

        @Override
        public void openFile(String filePath, OpenFileNeedDoType openFileNeedDoType) {

        }
    };

    protected DrawControlUtil drawControlUtil=new DrawControlUtil();
    private DrawControlUtilListen drawControlUtilListen=new DrawControlUtilListen() {
        @Override
        public void needShowPagerText(int showPagerNumb, int countPagerNumb) {
            setShowPagerText(showPagerNumb,countPagerNumb);
        }


    };
    protected abstract int setViewRs();
    protected abstract void bindView();
    protected abstract BaseDrawView getBaseDrawView();
    protected abstract void bindData();
    protected abstract void viewSetData();
    protected abstract void drawInitOkNowSetView(ContentInItBean inItBean, ContentPagerBean pagerBean);
    protected abstract void drawNeedShow();
    protected abstract void drawNeedPause();
    protected abstract void drawChangeConfig();
    protected abstract void drawClose();
    protected abstract void setShowPagerText(int showPagerNumb, int countPagerNumb);




    public BaseDrawViewFragment(ContentHeadBean contentHeadBean){

        drawControlUtil.setContentHeadBean(contentHeadBean);

    }

    @Override
    protected int initViewLayout() {
        return setViewRs();
    }

    @Override
    protected void initView() {
        bindView();
    }



    @Override
    protected void initData() {
        bindData();
    }

    @Override
    protected void initControl() {
        viewSetData();
        drawControlUtil.setBaseDrawView(drawControlUtilListen,(FrameLayout) view.findViewById(R.id.ll_draw_view),baseDrawViewListen);
    }

    @Override
    protected void viewIsShow() {
        drawNeedShow();
    }

    @Override
    protected void viewIsPause() {
        drawNeedPause();
    }

    @Override
    protected void close() {
        drawClose();
    }

    @Override
    protected void changeConfig() {
        drawChangeConfig();
    }

    @Override
    public void needInitDrawView() {

    }

    @Override
    public void autoSave() {
        save(SaveBitMapType.AUTO_SAVE);
    }

    @Override
    public long getContentHeadId() {
        try {
            return drawControlUtil.getHeadBean().getId();
        }catch (Exception e){}
        return 0;
    }

    private SaveBitMapType saveBitMapType;
    public void save(SaveBitMapType saveBitMapType){
        try {
            drawControlUtil.getBaseDrawView().setFunctionType(BaseDrawViewFunctionType.SAVE,saveBitMapType);
        }catch (Exception e){}
    }
}
