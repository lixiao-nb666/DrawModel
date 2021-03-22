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
public class ContentPushOutSetActivity extends BaseCompatActivity {
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()== R.id.bt_cancel){
                finish();
            }else if(v.getId()== R.id.bt_push_out_one){
                toActivity(ContentPushOutOneActivity.class, MyGson.getInstance().toGsonStr(contentBean));
                finish();
            }else if(v.getId()== R.id.bt_push_out_more){
                toActivity(ContentPushOutMoreActivity.class, MyGson.getInstance().toGsonStr(contentBean));
                finish();
            }else if(v.getId()== R.id.bt_push_out_all){
                toActivity(ContentPushOutAllActivity.class, MyGson.getInstance().toGsonStr(contentBean));
                finish();
            }


        }
    };
    private ContentBean contentBean;
    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_push_out_set;
    }

    @Override
    public void initView() {

    }

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
        findViewById(R.id.bt_push_out_more).setVisibility(View.GONE);
        findViewById(R.id.bt_push_out_more).setOnClickListener(clickListener);
        findViewById(R.id.bt_push_out_all).setVisibility(View.GONE);
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
