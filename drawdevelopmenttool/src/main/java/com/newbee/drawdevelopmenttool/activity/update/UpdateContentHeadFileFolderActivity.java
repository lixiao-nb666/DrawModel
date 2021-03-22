package com.newbee.drawdevelopmenttool.activity.update;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.appliction.BaseApplication;

import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/1 0001 9:47
 */
public class UpdateContentHeadFileFolderActivity extends BaseCompatActivity {

    private EditText addContentHeadNameET;
    private ContentHeadBean contentHeadBean;
    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_update_content_file_folder;
    }

    @Override
    public void initView() {
        addContentHeadNameET = view.findViewById(R.id.et_add_content_head_name);
        addContentHeadNameET.setHint(BaseApplication.getRsString(R.string.please_input_note_book_name));
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view.findViewById(R.id.creat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentHeadBean.setName(addContentHeadNameET.getText().toString());
                ContentHeadSqlServer.getInstance().update(contentHeadBean);
                finish();
            }
        });
    }

    @Override
    public void initData() {
        String intentString=getIntentString();
        if(!TextUtils.isEmpty(intentString)){
            contentHeadBean= MyGson.getInstance().fromJson(intentString,ContentHeadBean.class);
        }
        if(null==contentHeadBean){
            finish();
            return;
        }else {
            addContentHeadNameET.setText(contentHeadBean.getName());
        }

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
