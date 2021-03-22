package com.newbee.drawdevelopmenttool.popupwindow.addcontent;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventType;

import java.util.Objects;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/7/15 0015 9:51
 */
public class AddFileFolderPopupWindow extends BasePopupWindow {
    private Context context;
    private EditText addContentHeadNameET;

    public AddFileFolderPopupWindow(Context context){
        this.context=context;
    }

    @Override
    protected int bindView() {
        return R.layout.activity_add_content_file_folder;
    }

    @Override
    protected void initView(View view) {
        addContentHeadNameET = view.findViewById(R.id.et_add_content_head_name);
        addContentHeadNameET.setHint(BaseApplication.getRsString(R.string.please_input_note_book_name));
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContentHeadNameET.setText("");
                hide();
            }
        });
        view.findViewById(R.id.creat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hide();
                ContentHeadBean contentHeadBean=new ContentHeadBean();
                contentHeadBean.setName(addContentHeadNameET.getText().toString());
                contentHeadBean.setBgType(ContentHeadRsType.FLIE_FOLDER.ordinal());
                contentHeadBean.setType(ContentHeadType.FILE_FOLDER.ordinal());
                ContentHeadAddEventBusSubscriptionSubject.getInstance().eventListen(ContentHeadAddEventType.ADD_NOTE_BOOK,contentHeadBean);
                addContentHeadNameET.setText("");
            }
        });






    }



    @Override
    protected boolean clickOutHide() {
        return true;
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected void closeNeedDo() {

    }

    @Override
    public void nowIsShow(Object... objects) {
        super.nowIsShow();
        if(null!=getPopupWindow()){
            getPopupWindow().setFocusable(true);
        }
    }
}
