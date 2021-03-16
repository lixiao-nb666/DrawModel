package com.newbee.drawdevelopmenttool.activity.init;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.drawui.activity.user.UserPrivateAgreemeetActivity;
import com.lixiao.drawui.activity.version.AppVersionActivity;
import com.lixiao.drawui.activity.version.SystemVersionActivity;
import com.lixiao.drawui.activity.wastepaper.WastepaperBasketActivity;
import com.lixiao.drawui.fragment.head.HeadFavoritesFragment;
import com.lixiao.drawui.fragment.head.HeadManuscriptsFragment;
import com.lixiao.drawui.fragment.head.HeadSearchFragment;
import com.mannxin.notebook.R;
import com.newbee.taozinoteboard.adapters.DrawHeadActivityAdapter;
import com.newbee.taozinoteboard.adapters.FragmentAdapter;
import com.newbee.taozinoteboard.adapters.HeadSetContentAdapter;
import com.newbee.taozinoteboard.bean.head.DrawHeadBean;
import com.newbee.taozinoteboard.popupwindow.drawingheadset.DrawingHeadSetPopupWindow;
import com.newbee.taozinoteboard.utils.share.CanNotDelectShare;
import com.newbee.taozinoteboard.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class DrawingHeadActivity extends BaseCompatActivity {
    private ImageView setIV;
    private NoScrollViewPager viewPager;
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    private RecyclerView headRV;
    private String lastSelectHeadStr;
    private DrawHeadActivityAdapter.ViewHodler lastSelectViewHodler;
    private DrawHeadActivityAdapter drawHeadActivityAdapter;
    private DrawingHeadSetPopupWindow drawingHeadSetPopupWindow;
    private DrawHeadActivityAdapter.ItemClick itemClick = new DrawHeadActivityAdapter.ItemClick() {
        @Override
        public void itemClick(DrawHeadActivityAdapter.ViewHodler viewHodler, DrawHeadBean drawHeadBean, int positon) {
            try {
                if (lastSelectHeadStr.equals(drawHeadBean.getHeadStr()) && null != lastSelectViewHodler) {
                    return;
                }
                if (null != lastSelectViewHodler) {
                    lastSelectViewHodler.headTV.setTextColor(getResources().getColor(R.color.head_tv_false));
                    lastSelectViewHodler.headTV.setTextSize(16);
                }
                viewHodler.headTV.setTextColor(getResources().getColor(R.color.head_tv_true));
                viewHodler.headTV.setTextSize(20);
                lastSelectViewHodler = viewHodler;
                lastSelectHeadStr = drawHeadBean.getHeadStr();
                if (getString(R.string.head_manuscripts).equals(lastSelectHeadStr)) {
                    viewPager.setCurrentItem(0);
                } else if (getString(R.string.head_search).equals(lastSelectHeadStr)) {
                    viewPager.setCurrentItem(1);
                } else if (getString(R.string.head_favorites).equals(lastSelectHeadStr)) {
                    viewPager.setCurrentItem(2);
                }
                CanNotDelectShare.getInstance().putHeadLastSelectStr(lastSelectHeadStr);
            } catch (Exception e) {
            }
        }
    };


    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_draw_head;
    }

    @Override
    public void initView() {
        setIV = findViewById(R.id.iv_head_set);
        viewPager = findViewById(R.id.vp_head);
        headRV = findViewById(R.id.rv_head);
    }

    @Override
    public void initData() {

        setIV.setImageResource(R.drawable.head_set);

        fragmentList = new ArrayList<>();
        fragmentList.add(new HeadManuscriptsFragment());
        fragmentList.add(new HeadSearchFragment());
        fragmentList.add(new HeadFavoritesFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        List<DrawHeadBean> drawHeadBeanList = new ArrayList<>();
        drawHeadBeanList.add(new DrawHeadBean(getString(R.string.head_manuscripts), R.drawable.head_manuscripts_false));
        drawHeadBeanList.add(new DrawHeadBean(getString(R.string.head_search), R.drawable.head_search_false));
        drawHeadBeanList.add(new DrawHeadBean(getString(R.string.head_favorites), R.drawable.head_favorites_false));
        lastSelectHeadStr = CanNotDelectShare.getInstance().getHeadLastSelectStr();
        if (TextUtils.isEmpty(lastSelectHeadStr)) {
            lastSelectHeadStr = getString(R.string.head_manuscripts);
        }
        drawHeadActivityAdapter = new DrawHeadActivityAdapter(context, drawHeadBeanList, itemClick, lastSelectHeadStr);
    }


    @Override
    public void initControl() {

        setIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetPopupWindow();
            }
        });

        viewPager.setAdapter(fragmentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRV.setLayoutManager(linearLayoutManager);
        headRV.setAdapter(drawHeadActivityAdapter);
//        Class needToCls = CanNotDelectShare.getInstance().getLastOpenActivity();
//        if (null != needToCls&&!needToCls.equals(getClass())) {
//            toActivity(needToCls);
//        }

    }

    @Override
    public void closeActivity() {
        if(null!=drawingHeadSetPopupWindow){
            drawingHeadSetPopupWindow.close();
        }
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

    private HeadSetContentAdapter.ItemClick headSetContentAdapterItemClick = new HeadSetContentAdapter.ItemClick() {
        @Override
        public void clickHeadSetContent(String headSetContent) {
            hideSetPopupWindow();
            if(TextUtils.isEmpty(headSetContent)){
                return;
            }
            if(headSetContent.equals(getString(R.string.head_set_item_me))){
            }else if(headSetContent.equals(getString(R.string.head_set_item_screen_is_v))){
                if(myWindowSet.screenIsLandscape()){
                    myWindowSet.setScreenPortrait();
                }else {
                    myWindowSet.setScreenLandscape();
                }
            }else if(headSetContent.equals(getString(R.string.head_set_item_auto_order))){

            }else if(headSetContent.equals(getString(R.string.head_set_item_def_config))){

            }else if(headSetContent.equals(getString(R.string.head_set_item_wastepaper_basket))){
                toActivity(WastepaperBasketActivity.class);
            }else if(headSetContent.equals(getString(R.string.head_set_item_note_book))){

            }else if(headSetContent.equals(getString(R.string.head_set_item_system_v))){
                toActivity(SystemVersionActivity.class);
            }else if(headSetContent.equals(getString(R.string.head_set_item_private))){
                toActivity(UserPrivateAgreemeetActivity.class);
            }else if(headSetContent.equals(getString(R.string.head_set_item_app_v))){
                toActivity(AppVersionActivity.class);
            }
        }
    };

    private void showSetPopupWindow() {
        if(null==drawingHeadSetPopupWindow){
            drawingHeadSetPopupWindow=new DrawingHeadSetPopupWindow(context,headSetContentAdapterItemClick);
        }
        drawingHeadSetPopupWindow.showByDown(setIV,-100,-120);

    }

    private void hideSetPopupWindow() {
        if(null!=drawingHeadSetPopupWindow){
            drawingHeadSetPopupWindow.hide();
        }
    }
}
