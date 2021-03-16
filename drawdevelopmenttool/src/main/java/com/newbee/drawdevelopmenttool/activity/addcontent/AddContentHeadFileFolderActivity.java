package com.newbee.drawdevelopmenttool.activity.addcontent;

import android.view.View;
import android.widget.EditText;

import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.util.event.DialogEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.DialogEventType;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/1 0001 9:47
 */
public class AddContentHeadFileFolderActivity extends BaseCompatActivity {

    private EditText addContentHeadNameET;

    private View.OnClickListener clickListener=new View.OnClickListener() {
        boolean isClick=false;
        @Override
        public void onClick(View v) {
            if(isClick){
                return;
            }
            isClick=true;
            if(v.getId()==R.id.cancel){
                finish();
            }else if(v.getId()== R.id.creat){
                ContentHeadBean contentHeadBean=new ContentHeadBean();
                contentHeadBean.setName(addContentHeadNameET.getText().toString());
                contentHeadBean.setBgType(ContentHeadRsType.FLIE_FOLDER.ordinal());
                contentHeadBean.setType(ContentHeadType.FILE_FOLDER.ordinal());
                DialogEventBusSubscriptionSubject.getInstance().eventListen(DialogEventType.ADD_NOTE_BOOK,contentHeadBean);
                finish();
            }


        }
    };

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_add_content_file_folder;
    }

    @Override
    public void initView() {
        addContentHeadNameET = view.findViewById(R.id.et_add_content_head_name);


    }

    @Override
    public void initData() {
        addContentHeadNameET.setHint(getRsString(R.string.please_input_note_book_name));
    }

    @Override
    public void initControl() {
        view.findViewById(R.id.cancel).setOnClickListener(clickListener);
        view.findViewById(R.id.creat).setOnClickListener(clickListener);
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
