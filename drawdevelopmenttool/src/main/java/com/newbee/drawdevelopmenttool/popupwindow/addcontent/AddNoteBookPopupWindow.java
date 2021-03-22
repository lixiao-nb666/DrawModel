package com.newbee.drawdevelopmenttool.popupwindow.addcontent;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadOrientationType;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.bean.content.bg.NoteBookBgType;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventType;

import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/7/15 0015 9:51
 */
public class AddNoteBookPopupWindow extends BasePopupWindow {
    private Context context;
    private EditText addContentHeadNameET;
    boolean isClick=false;
    private View.OnClickListener clickListener=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(isClick){
                return;
            }
            isClick=true;
            if(v.getId()==R.id.cancel){
                hide();
            }else if(v.getId()==R.id.tv_create){
                ContentHeadBean contentHeadBean=new ContentHeadBean();
                contentHeadBean.setBgType(bg);
                contentHeadBean.setOrientation(contentHeadOrientationType.ordinal());
                contentHeadBean.getContentHeadOtherBean().setDefDrawBgType( bookBgType.ordinal());
                contentHeadBean.setName(addContentHeadNameET.getText().toString());
                contentHeadBean.setType(ContentHeadType.NOTE_BOOK.ordinal());
                ContentHeadAddEventBusSubscriptionSubject.getInstance().eventListen(ContentHeadAddEventType.ADD_NOTE_BOOK,contentHeadBean);
                hide();
            }


        }
    };
    private RadioGroup bgRG;
    private RadioGroup selectSCreenRG;
    private RadioButton autoTV,horizotalTV,vercatalTV,selectTV;
    private RadioGroup selectDrawDefBgRG;

    public AddNoteBookPopupWindow(Context context){
        this.context=context;
    }

    @Override
    protected int bindView() {
        return R.layout.activity_add_content_note_book;
    }

    @Override
    protected void initView(View view) {
        initView();
        initData();
        initControl();
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
        isClick=false;


    }



    public void initView() {
        addContentHeadNameET = getView().findViewById(R.id.et_add_content_head_name);
        bgRG = getView().findViewById(R.id.rg_select_bg);
        autoTV=getView().findViewById(R.id.tv_auto);
        vercatalTV=getView().findViewById(R.id.tv_vercital);
        horizotalTV=getView().findViewById(R.id.tv_horizotal);
        selectSCreenRG= getView().findViewById(R.id.rg_select_screen);
        selectDrawDefBgRG  =getView().findViewById(R.id.rg_select_def_draw_bg);
    }

    int bg;
    public void initData() {

        addContentHeadNameET.setHint(BaseApplication.getRsString(R.string.please_input_note_book_name));

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
                this.bg=bg;
                radioButton.setChecked(true);
            }
            radioButton.setId(bg);
            bgRG.addView(radioButton);
        }
        setSelectTV(autoTV);


        setNoteBookDrawBg(NoteBookBgType.NONE);
    }

    ContentHeadOrientationType contentHeadOrientationType;
    public void initControl() {
        bgRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bg=checkedId;

            }
        });
        getView().findViewById(R.id.cancel).setOnClickListener(clickListener);
        getView().findViewById(R.id.tv_create).setOnClickListener(clickListener);
        addContentHeadNameET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        contentHeadOrientationType=ContentHeadOrientationType.AUTO;
        selectSCreenRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.tv_auto){
                    setSelectTV(autoTV);
                    contentHeadOrientationType=ContentHeadOrientationType.AUTO;
                }else if(checkedId==R.id.tv_vercital){
                    setSelectTV(vercatalTV);
                    contentHeadOrientationType=ContentHeadOrientationType.vercital;
                }else if(checkedId==R.id.tv_horizotal){
                    setSelectTV(horizotalTV);
                    contentHeadOrientationType=ContentHeadOrientationType.horizotal;
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


    NoteBookBgType bookBgType;
    private void setNoteBookDrawBg(NoteBookBgType noteBookDrawBg){
        this.bookBgType=noteBookDrawBg;

    }

}
