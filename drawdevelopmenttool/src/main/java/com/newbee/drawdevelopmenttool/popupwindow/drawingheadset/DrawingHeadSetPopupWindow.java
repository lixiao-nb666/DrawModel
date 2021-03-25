package com.newbee.drawdevelopmenttool.popupwindow.drawingheadset;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
import com.lixiao.build.mybase.popupwindow.BasePoputWindowListen;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.configset.ConfigSetActivity;
import com.newbee.drawdevelopmenttool.activity.user.UserPrivateAgreemeetActivity;
import com.newbee.drawdevelopmenttool.activity.wastepaper.WastepaperBasketActivity;
import com.newbee.drawdevelopmenttool.adapters.HeadSetContentAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawingHeadSetPopupWindow extends BasePopupWindow {
    private Context context;
    private BasePoputWindowListen basePoputWindowListen;
    private HeadSetContentAdapter.ItemClick headSetContentAdapterItemClick= new HeadSetContentAdapter.ItemClick() {
        @Override
        public void clickHeadSetContent(String headSetContent) {
            if(TextUtils.isEmpty(headSetContent)){
                return;
            }
            hide();
            if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_me))){
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_screen_is_v))){
//                if(myWindowSet.screenIsLandscape()){
//                    myWindowSet.setScreenPortrait();
//                }else {
//                    myWindowSet.setScreenLandscape();
//                }
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_auto_order))){

            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_def_config))){
                toActivity(ConfigSetActivity.class);
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_wastepaper_basket))){
                toActivity(WastepaperBasketActivity.class);
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_note_book))){

            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_system_v))){
//                toActivity(SystemVersionActivity.class);
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_private))){
                toActivity(UserPrivateAgreemeetActivity.class);
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_app_v))){
//                toActivity(AppVersionActivity.class);
            }else if(headSetContent.equals(BaseApplication.getRsString(R.string.head_set_item_close))){

            }
        }
    };

    // 直接跳转活动
    public void toActivity(Class toClass) {
        Intent intent = new Intent(context, toClass);
        context.startActivity(intent);
    }

    public DrawingHeadSetPopupWindow(Context context, BasePoputWindowListen basePoputWindowListen) {
        this.context = context;
        this.basePoputWindowListen=basePoputWindowListen;
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected int bindView() {
        return R.layout.popupwindow_head_set;
    }

    @Override
    protected void initView(View view) {
        RecyclerView headSetRV = view.findViewById(R.id.rv_head_set);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        headSetRV.setLayoutManager(linearLayoutManager);
        List<String> headSetContentList = new ArrayList<>();
//        headSetContentList.add(MyApplication.getRsString(R.string.head_set_item_me));
//                headSetContentList.add(MyApplication.getRsString(R.string.head_set_item_screen_is_v));
//        headSetContentList.add(MyApplication.getRsString(R.string.head_set_item_auto_order));
//        headSetContentList.add(MyApplication.getRsString(R.string.head_set_item_note_book));
        headSetContentList.add(BaseApplication.getRsString(R.string.head_set_item_def_config));
        headSetContentList.add(BaseApplication.getRsString(R.string.head_set_item_wastepaper_basket));
//        headSetContentList.add(MyApplication.getRsString(R.string.head_set_item_app_v));
//        headSetContentList.add(MyApplication.getRsString(R.string.head_set_item_system_v));
        headSetContentList.add(BaseApplication.getRsString(R.string.head_set_item_private));
        headSetContentList.add(BaseApplication.getRsString(R.string.head_set_item_close));


        headSetRV.setAdapter(new HeadSetContentAdapter(context, headSetContentList, headSetContentAdapterItemClick));
    }

    @Override
    protected boolean clickOutHide() {
        return true;
    }

    @Override
    protected void closeNeedDo() {
        context = null;
    }
}
