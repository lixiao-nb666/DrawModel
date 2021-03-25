package com.newbee.drawdevelopmenttool.popupwindow.contentchange;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.ToastUtil;
import com.lixiao.build.mybase.activity.util.ActivityUtil;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
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
public class ContentHeadChangePopupWindow extends BasePopupWindow {
    private ContentHeadBean contentHeadBean;
    private TextView titleTV;
    private Button updateBT,toRbsBT,pushOutBT,retrunBT;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.bt_change_content_head){
                switch (contentHeadBean.getEnumType()){
                    case FILE_FOLDER:
                        ActivityUtil.toActivity(context,UpdateContentHeadFileFolderActivity.class, MyGson.getInstance().toGsonStr(contentHeadBean));
                        hide();
                        break;
                    default:


                        ActivityUtil.toActivity(context,UpdateContentHeadNoteBookActivity.class, MyGson.getInstance().toGsonStr(contentHeadBean));
                        hide();
                        break;
                }
            }else if(v.getId()==R.id.bt_to_rbs){
                contentHeadBean.setNeedDelect(true);
                if(ContentHeadSqlServer.getInstance().update(contentHeadBean)){
                    ContentHeadDoRbsEventBusSubscriptionSubject.getInstance().eventListen(ContentHeadDoRbsEventType.TO_RBS,contentHeadBean);
                    hide();
                }else {
                    ToastUtil.showToast(BaseApplication.getRsString(R.string.to_rbs_err));

                }
            }else if(v.getId()== R.id.bt_push_out){
                    ActivityUtil.toActivity(context,ContentPushOutSetActivity.class, MyGson.getInstance().toGsonStr(contentHeadBean));
                hide();
            }else if(v.getId()==R.id.bt_retrun){
                hide();
            }
        }
    };


     private Context context;
     public ContentHeadChangePopupWindow(Context context){
        this.context=context;
     }

    @Override
    public void nowIsShow(Object... objects) {
        super.nowIsShow();
        contentHeadBean= (ContentHeadBean) objects[0];
        if(null==contentHeadBean){
            hide();
            return;
        }
        StringBuilder sb=new StringBuilder();
        sb.append(contentHeadBean.getEnumTypeName());
        sb.append("("+contentHeadBean.getName()+")");
        titleTV.setText(sb.toString());
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected int bindView() {
        return R.layout.activity_content_head_change;
    }

    @Override
    protected void initView(View view) {
        titleTV=view.findViewById(R.id.tv_title);
        updateBT=view.findViewById(R.id.bt_change_content_head);
        toRbsBT=view.findViewById(R.id.bt_to_rbs);
        pushOutBT=view.findViewById(R.id.bt_push_out);
        retrunBT=view.findViewById(R.id.bt_retrun);
        updateBT.setOnClickListener(onClickListener);
        toRbsBT.setOnClickListener(onClickListener);
        pushOutBT.setOnClickListener(onClickListener);
        retrunBT.setOnClickListener(onClickListener);
    }


    @Override
    protected boolean clickOutHide() {
        return true;
    }



    @Override
    protected void closeNeedDo() {

    }
}
