package com.newbee.drawdevelopmenttool.activity.addcontent;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.bean.content.bg.NoteBookBgType;
import com.newbee.drawdevelopmenttool.util.event.DialogEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.DialogEventType;


import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/8/1 0001 9:47
 */
public class AddContentHeadNoteBookActivity extends BaseCompatActivity {

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
            }else if(v.getId()==R.id.tv_create){
                contentHeadBean.setName(addContentHeadNameET.getText().toString());
                contentHeadBean.setType(ContentHeadType.NOTE_BOOK.ordinal());
                DialogEventBusSubscriptionSubject.getInstance().eventListen(DialogEventType.ADD_NOTE_BOOK,contentHeadBean);
                finish();
            }


        }
    };

    private RadioGroup bgRG;
    private boolean defScreen;
    private RadioGroup selectSCreenRG;
    private RadioButton autoTV,horizotalTV,vercatalTV,selectTV;
    private RadioGroup selectDrawDefBgRG;
    private ContentHeadBean contentHeadBean;

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_add_content_note_book;
    }

    @Override
    public void initView() {
        addContentHeadNameET = view.findViewById(R.id.et_add_content_head_name);
        bgRG = view.findViewById(R.id.rg_select_bg);
        autoTV=view.findViewById(R.id.tv_auto);
        vercatalTV=view.findViewById(R.id.tv_vercital);
        horizotalTV=view.findViewById(R.id.tv_horizotal);
        selectSCreenRG= view.findViewById(R.id.rg_select_screen);
        selectDrawDefBgRG  = view.findViewById(R.id.rg_select_def_draw_bg);
    }

    @Override
    public void initData() {
        String intentString=getIntentString();
        if(!TextUtils.isEmpty(intentString)){
            contentHeadBean= MyGson.getInstance().fromJson(intentString,ContentHeadBean.class);
        }
        if(null==contentHeadBean){
            contentHeadBean=new ContentHeadBean();
            addContentHeadNameET.setHint(getRsString(R.string.please_input_note_book_name));
        }else {
            addContentHeadNameET.setText(contentHeadBean.getName());
        }
        int bgIndex=2;
        List<Integer> bjList= ContentHeadRsType.getRsTypeList(ContentHeadType.NOTE_BOOK);
        for (int i = 0; i < bjList.size(); i++) {
            int bg=bjList.get(i);
            int bgrs = ContentHeadRsType.getRs(ContentHeadType.NOTE_BOOK,bg);
            RadioButton radioButton = new RadioButton(context);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(150, 200);
            radioButton.setLayoutParams(layoutParams);
            layoutParams.setMargins(11, 0, 11, 0);
            radioButton.setBackgroundResource(bgrs);
            if (bgIndex == i) {
                contentHeadBean.setBgType(bg);
                radioButton.setChecked(true);
            }
            radioButton.setId(bg);
            bgRG.addView(radioButton);
        }
        setSelectTV(autoTV);
        setNoteBookDrawBg(NoteBookBgType.values()[contentHeadBean.getContentHeadOtherBean().getDefDrawBgType()]);
    }

    @Override
    public void initControl() {
        bgRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                contentHeadBean.setBgType(checkedId);
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(clickListener);
        view.findViewById(R.id.tv_create).setOnClickListener(clickListener);
        addContentHeadNameET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        selectSCreenRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                contentHeadBean.setLandscape(false);
                if(checkedId==R.id.tv_auto){
                    setSelectTV(autoTV);
                    contentHeadBean.setLandscape(defScreen);
                }else if(checkedId==R.id.tv_vercital){
                    setSelectTV(vercatalTV);
                    contentHeadBean.setLandscape(false);
                }else if(checkedId==R.id.tv_horizotal){
                    setSelectTV(horizotalTV);
                    contentHeadBean.setLandscape(true);
                }


            }
        });
        selectDrawDefBgRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId== R.id.rb_def_draw_bg_0){
                    setNoteBookDrawBg(NoteBookBgType.NONE);
                }else if(checkedId==R.id.rb_def_draw_bg_1){
                    setNoteBookDrawBg(NoteBookBgType.LIST);
                }else if(checkedId==R.id.rb_def_draw_bg_2){
                    setNoteBookDrawBg(NoteBookBgType.GRIL);
                }else if(checkedId==R.id.rb_def_draw_bg_3){
                    setNoteBookDrawBg(NoteBookBgType.PINYIN);
                }else if(checkedId== R.id.rb_def_draw_bg_4){
                    setNoteBookDrawBg(NoteBookBgType.TIANZI);
                }
            }
        });
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

    private void setSelectTV(RadioButton selectTV){
        if(null==selectTV){
            return;
        }
        if(null!=this.selectTV){
            this.selectTV.setChecked(false);
        }
        this.selectTV=selectTV;
        this.selectTV.setChecked(true);
        switch (selectTV.getId()){}
    }



    private void setNoteBookDrawBg(NoteBookBgType noteBookDrawBg){
        contentHeadBean.getContentHeadOtherBean().setDefDrawBgType( noteBookDrawBg.ordinal());
    }
}
