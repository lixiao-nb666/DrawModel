package com.newbee.drawdevelopmenttool.activity.pushout;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lixiao.build.glide.MyGlide;
import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.appliction.MyApplicationFile;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.share.DrawShare;

import java.io.File;

;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/9/11 0011 18:33
 */
public class ContentPushOutOneActivity extends BaseCompatActivity {
    private ImageView lastIV,nextIV;
    private TextView pagerTV;
    private ImageView showPagerIV;
    private View.OnClickListener overClickListener=new View.OnClickListener() {
        private boolean isClick=false;
        @Override
        public void onClick(View v) {
            if(isClick){
                return;
            }
            isClick=true;
            if(v.getId()== R.id.bt_push_out){
//                UploadRqNeedInfoBean uploadRqNeedInfoBean=new UploadRqNeedInfoBean();
//            String fileName=contentBean.getFileName(showNumb);
//            String filePath= FileUtils.getDrawingSavePath() + File.separator + fileName;
//            uploadRqNeedInfoBean.setFileName(fileName);
//            uploadRqNeedInfoBean.setFilePath(filePath);
//            toActivity(RqCreateActivity.class, MyGson.getInstance().toGsonStr(uploadRqNeedInfoBean));
        }
            finish();
        }
    };



    private int showNumb;
    private View.OnClickListener pagerChangeClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.iv_last_pager){
                showNumb--;
                if (showNumb < 1) {
                    showNumb = 1;
                }
            }else if(v.getId()==R.id.iv_next_pager){
                int cpnn = pagerBean.getCountPagerNumb();
                showNumb++;
                if ( showNumb > cpnn) {
                    showNumb = cpnn;
                }
            }


            showTextPager();
        }
    };


    private ContentHeadBean headBean;
    private ContentPagerBean pagerBean;
    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_push_out_one;
    }

    @Override
    public void initView() {
        showPagerIV=findViewById(R.id.iv_push_one);
        pagerTV=findViewById(R.id.tv_show_pager);
        lastIV=findViewById(R.id.iv_last_pager);
        nextIV=findViewById(R.id.iv_next_pager);
    }

    @Override
    public void initData() {
        String intentString=getIntentString();
        if(!TextUtils.isEmpty(intentString)){
            headBean= MyGson.getInstance().fromJson(intentString,ContentHeadBean.class);
        }
        if(null==headBean){
            finish();
            return;
        }
        pagerBean= DrawShare.getInstance().getContentPagerBean(headBean.getId());
        TextView titleTV=findViewById(R.id.tv_title);
        titleTV.setText(titleTV.getText()+"("+headBean.getName()+")");

        lastIV.setImageResource(R.drawable.icon_last_pager);
        nextIV.setImageResource(R.drawable.icon_next_pager);
        lastIV.setOnClickListener(pagerChangeClick);
        nextIV.setOnClickListener(pagerChangeClick);
        findViewById(R.id.bt_push_out).setOnClickListener(overClickListener);
        findViewById(R.id.bt_cancel).setOnClickListener(overClickListener);
        showNumb= pagerBean.getShowPagerNumb();
        showTextPager();
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
        finish();
    }

    @Override
    public void changeConfig() {

    }


    private void showTextPager(){

        int countPagerNumb=pagerBean.getCountPagerNumb();

        if(showNumb>countPagerNumb){
            showNumb=countPagerNumb;
        }
        if(showNumb<1){
            showNumb=1;
        }
        pagerTV.setText(showNumb+"/"+countPagerNumb);

        String filePath= headBean.getLocalFilePath(showNumb);
        MyGlide.getInstance().setBitMapNoCache(showPagerIV,filePath);
    }
}
