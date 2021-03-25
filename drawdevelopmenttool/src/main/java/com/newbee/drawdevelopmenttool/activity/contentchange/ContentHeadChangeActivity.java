package com.newbee.drawdevelopmenttool.activity.contentchange;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.ToastUtil;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.activity.util.ActivityUtil;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.pushout.ContentPushOutSetActivity;
import com.newbee.drawdevelopmenttool.activity.update.UpdateContentHeadFileFolderActivity;
import com.newbee.drawdevelopmenttool.activity.update.UpdateContentHeadNoteBookActivity;

import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;

import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheaddorbs.ContentHeadDoRbsEventType;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/10 0010 17:23
 */
public class ContentHeadChangeActivity extends BaseCompatActivity {
    private ContentHeadBean contentHeadBean;
    private TextView titleTV;
    private Button updateBT,toRbsBT,pushOutBT,retrunBT;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.bt_change_content_head){
                switch (contentHeadBean.getEnumType()){
                    case FILE_FOLDER:
                        LG.i(tag,"change_content_head:2--"+contentHeadBean.getEnumType());
                        toActivity(UpdateContentHeadFileFolderActivity.class, MyGson.getInstance().toGsonStr(contentHeadBean));
                        finish();
                        break;
                    default:
                        LG.i(tag,"change_content_head:3--"+contentHeadBean.getEnumType());
                        toActivity(UpdateContentHeadNoteBookActivity.class, MyGson.getInstance().toGsonStr(contentHeadBean));
                        finish();
                        break;
                }
            }else if(v.getId()==R.id.bt_to_rbs){
                contentHeadBean.setNeedDelect(true);
                if(ContentHeadSqlServer.getInstance().update(contentHeadBean)){
                    ContentHeadDoRbsEventBusSubscriptionSubject.getInstance().eventListen(ContentHeadDoRbsEventType.TO_RBS,contentHeadBean);
                    finish();
                }else {
                    showToast(getString(R.string.to_rbs_err));
                }
            }else if(v.getId()== R.id.bt_push_out){
//                ContentBean pushOutContentBean = DrawShare.getInstance().getContentBean(contentHeadBean);
//                if(null!=pushOutContentBean){
//                    toActivity(ContentPushOutSetActivity.class, MyGson.getInstance().toGsonStr(pushOutContentBean));
//                }
//                finish();
            }else if(v.getId()==R.id.bt_retrun){
                finish();
            }
        }
    };
    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_content_head_change;
    }

    @Override
    public void initView() {
        titleTV=findViewById(R.id.tv_title);
        updateBT=view.findViewById(R.id.bt_change_content_head);
        toRbsBT=view.findViewById(R.id.bt_to_rbs);
        pushOutBT=view.findViewById(R.id.bt_push_out);
        retrunBT=view.findViewById(R.id.bt_retrun);
    }

    @Override
    public void initData() {
        contentHeadBean= MyGson.getInstance().fromJson(getIntentString(),ContentHeadBean.class);
        updateBT.setOnClickListener(onClickListener);
        toRbsBT.setOnClickListener(onClickListener);
        pushOutBT.setOnClickListener(onClickListener);
        retrunBT.setOnClickListener(onClickListener);
    }

    @Override
    public void initControl() {
        if(null==contentHeadBean){
            finish();
            return;
        }
        StringBuilder sb=new StringBuilder();
        sb.append(contentHeadBean.getEnumTypeName());
        sb.append("("+contentHeadBean.getName()+")");
        titleTV.setText(sb.toString());
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
