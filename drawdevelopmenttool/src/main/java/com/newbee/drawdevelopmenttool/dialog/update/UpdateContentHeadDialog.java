package com.newbee.drawdevelopmenttool.dialog.update;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lixiao.build.mybase.dialog.BaseDialog;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/11/17 0017 15:20
 */
public class UpdateContentHeadDialog extends BaseDialog {
    private Context context;
    private ItemClick itemClick;
    private ContentHeadBean contentHeadBean;
    private TextView titleTV;
    private EditText updateNameET;
    private Button updateBT,rbsBT,retrunBT;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.bt_change_content_head){
                if(null!=updateNameET.getText()){
                    contentHeadBean.setName(updateNameET.getText().toString());
                    ContentHeadSqlServer.getInstance().update(contentHeadBean);
                }else {
                    contentHeadBean.setName("");
                    ContentHeadSqlServer.getInstance().update(contentHeadBean);
                }
                itemClick.needFareshView();
                hide();
            }else if(v.getId()==R.id.bt_to_rbs){
                contentHeadBean.setNeedDelect(true);
                ContentHeadSqlServer.getInstance().update(contentHeadBean);
                itemClick.needFareshView();
                hide();
            }else if(v.getId()==R.id.bt_retrun){
                hide();
            }
        }
    };

    public UpdateContentHeadDialog(Context context, ItemClick itemClick){
        this.context=context;
        this.itemClick=itemClick;
    }

    @Override
    protected int bindView() {
        return R.layout.dialog_change_content_head;
    }

    @Override
    protected void initView(View view) {
        titleTV=view.findViewById(R.id.tv_title);
        updateNameET=view.findViewById(R.id.et_change_content_head_name);
        updateBT=view.findViewById(R.id.bt_change_content_head);
        rbsBT=view.findViewById(R.id.bt_to_rbs);
        retrunBT=view.findViewById(R.id.bt_retrun);
        updateBT.setOnClickListener(onClickListener);
        rbsBT.setOnClickListener(onClickListener);
        retrunBT.setOnClickListener(onClickListener);
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected void closeNeedDo() {

    }

    @Override
    protected void showNeedDo(Object... objects) {
        try {
            contentHeadBean= (ContentHeadBean) objects[0];
            titleTV.setText(titleTV.getText()+"("+contentHeadBean.getName()+")");
        }catch (Exception e){

        }

    }


    public interface ItemClick{

        public void needFareshView();
    }


}
