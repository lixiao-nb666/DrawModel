package com.newbee.drawdevelopmenttool.activity.pushout;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.activity.BaseCompatActivity;

import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentBean;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/9/11 0011 18:33
 */
public class ContentPushOutAllActivity extends BaseCompatActivity {
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()== R.id.bt_cancel){
                    finish();
            }else if(v.getId()== R.id.bt_push_out_one){

            }else if(v.getId()== R.id.bt_push_out_more){

            }else if(v.getId()== R.id.bt_push_out_all){

            }


        }
    };

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_push_out_all;
    }

    @Override
    public void initView() {

    }
    private ContentBean contentBean;
    @Override
    public void initData() {
        String intentString=getIntentString();
        if(!TextUtils.isEmpty(intentString)){
            contentBean= MyGson.getInstance().fromJson(intentString, ContentBean.class);
        }
        if(null==contentBean){
            finish();
            return;
        }
        TextView titleTV=findViewById(R.id.tv_title);
        titleTV.setText(titleTV.getText()+"("+contentBean.getContentHeadBean().getName()+")");
        findViewById(R.id.bt_push_out_one).setOnClickListener(clickListener);
        findViewById(R.id.bt_push_out_more).setOnClickListener(clickListener);
        findViewById(R.id.bt_push_out_all).setOnClickListener(clickListener);
        findViewById(R.id.bt_cancel).setOnClickListener(clickListener);
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
}
