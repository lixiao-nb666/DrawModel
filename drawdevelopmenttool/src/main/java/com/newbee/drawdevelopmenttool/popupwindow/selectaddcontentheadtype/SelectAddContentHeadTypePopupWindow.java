package com.newbee.drawdevelopmenttool.popupwindow.selectaddcontentheadtype;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;

import com.lixiao.build.mybase.popupwindow.BasePoputWindowListen;
import com.lixiao.build.mybase.popupwindow.util.PopupManagerUtil;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.addcontent.AddContentHeadFileFolderActivity;
import com.newbee.drawdevelopmenttool.activity.addcontent.AddContentHeadNoteBookActivity;
import com.newbee.drawdevelopmenttool.adapters.ManuscriptsSelectAddTypeAdapter;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadType;
import com.newbee.drawdevelopmenttool.bean.manuscript.ManuscriptAddTypeInfoBean;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.popupwindow.addcontent.AddFileFolderPopupWindow;
import com.newbee.drawdevelopmenttool.popupwindow.addcontent.AddNoteBookPopupWindow;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusSubscriptionSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventType;


import java.util.ArrayList;
import java.util.List;

public class SelectAddContentHeadTypePopupWindow extends BasePopupWindow {
    private Context context;

    private ManuscriptsSelectAddTypeAdapter.ItemClick selectAddTypeItemClick = new ManuscriptsSelectAddTypeAdapter.ItemClick() {
        @Override
        public void itemClick(ContentHeadType contentHeadType, int positon) {
            hide();
            if(null==contentHeadType){
                return;
            }

            switch (contentHeadType) {
                case FAST_CREATE:
                    ContentHeadBean addContentHeadBean = new ContentHeadBean();
                    addContentHeadBean.setType(ContentHeadType.NOTE_BOOK.ordinal());
                    addContentHeadBean.setBgType(ContentHeadRsType.NOTE_BOOK_BLACK.ordinal());
                    ContentHeadAddEventBusSubscriptionSubject.getInstance().eventListen(ContentHeadAddEventType.FAST_ADD_NOTE_BOOK,addContentHeadBean);
                    break;
                default:
                    switch (MyDrawBoardConfig.getAddContentHeadType()){
                        case USE_ACTIVITY:
                            addTypeNeedDoIsActivity(contentHeadType);
                            break;
                        case USE_POPUPWINDOW:
                            addTypeNeedDoIsPopupWindow(contentHeadType);
                            break;
                    }
                    break;
            }


        }

        private void addTypeNeedDoIsActivity(ContentHeadType contentHeadType){
            switch (contentHeadType){
                case FILE_FOLDER:
                    toActivity(AddContentHeadFileFolderActivity.class);
                    break;
                case NOTE_BOOK:
                    toActivity(AddContentHeadNoteBookActivity.class);
                    break;
            }

        }
        private void addTypeNeedDoIsPopupWindow(ContentHeadType contentHeadType){
            switch (contentHeadType){
                case FILE_FOLDER:
                    popupManagerUtil.showByTop(AddFileFolderPopupWindow.class.getSimpleName(),getView(),0,300);

                    break;
                case NOTE_BOOK:
                    popupManagerUtil.showByTop(AddNoteBookPopupWindow.class.getSimpleName(),getView(),0,300);
                    break;
            }

        }

    };



    // 直接跳转活动
    public void toActivity(Class toClass) {
        Intent intent = new Intent(context, toClass);
        context.startActivity(intent);
    }
    private BasePoputWindowListen basePoputWindowListen;
    private PopupManagerUtil popupManagerUtil;
    private PopupManagerUtil.Listen popupManagerUtilListen=new PopupManagerUtil.Listen() {
        @Override
        public void err(String err) {

        }

        @Override
        public BasePopupWindow createPopupWindow(String popupType) {
            if(null==popupType){
                return null;
            }
            if(popupType.equals(AddNoteBookPopupWindow.class.getSimpleName())){
                return new AddNoteBookPopupWindow(getContext());
            }else if(popupType.equals(AddFileFolderPopupWindow.class.getSimpleName())){
                return new AddFileFolderPopupWindow(getContext());
            }



            return null;
        }
    };

    public SelectAddContentHeadTypePopupWindow(Context context,BasePoputWindowListen basePoputWindowListen) {
        this.context = context;
        this.basePoputWindowListen=basePoputWindowListen;
        popupManagerUtil=new PopupManagerUtil(popupManagerUtilListen);
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected int bindView() {
        return R.layout.dialog_select_add_type;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.bt_select_add_type_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.rv_select_add_type);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<ManuscriptAddTypeInfoBean> manuscriptAddTypeInfoBeanList = new ArrayList<>();
        manuscriptAddTypeInfoBeanList.add(new ManuscriptAddTypeInfoBean(ContentHeadType.NOTE_BOOK, BaseApplication.getRsString(R.string.dialog_item_note_book), R.drawable.pop_ic_note_book));
        manuscriptAddTypeInfoBeanList.add(new ManuscriptAddTypeInfoBean(ContentHeadType.FILE_FOLDER, BaseApplication.getRsString(R.string.dialog_item_folder), R.drawable.pop_ic_file));
        manuscriptAddTypeInfoBeanList.add(new ManuscriptAddTypeInfoBean(ContentHeadType.FAST_CREATE, BaseApplication.getRsString(R.string.dialog_item_fast_create), R.drawable.pop_ic_celerity));
        ManuscriptsSelectAddTypeAdapter manuscriptsSelectAddTypeAdapter = new ManuscriptsSelectAddTypeAdapter(getContext(), manuscriptAddTypeInfoBeanList, selectAddTypeItemClick);
        recyclerView.setAdapter(manuscriptsSelectAddTypeAdapter);
    }

    @Override
    protected boolean clickOutHide() {
        return true;
    }

    @Override
    protected void closeNeedDo() {
        context = null;
        popupManagerUtil.close();
    }
}
