package com.newbee.drawdevelopmenttool.activity.init;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.popupwindow.BasePopupWindow;
import com.lixiao.build.mybase.popupwindow.BasePoputWindowListen;
import com.lixiao.build.mybase.popupwindow.util.PopupManagerUtil;
import com.lixiao.view.NoScrollViewPager;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.adapters.DrawHeadActivityAdapter;
import com.newbee.drawdevelopmenttool.adapters.FragmentAdapter;
import com.newbee.drawdevelopmenttool.bean.head.DrawHeadBean;
import com.newbee.drawdevelopmenttool.fragment.head.HeadFavoritesFragment;
import com.newbee.drawdevelopmenttool.fragment.head.HeadManuscriptsFragment;
import com.newbee.drawdevelopmenttool.fragment.head.HeadSearchFragment;
import com.newbee.drawdevelopmenttool.popupwindow.drawingheadset.DrawingHeadSetPopupWindow;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import java.util.ArrayList;
import java.util.List;

public class DrawingHeadActivity extends BaseCompatActivity {
    private ImageView setIV;
    private NoScrollViewPager viewPager;
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    private RecyclerView headRV;
    private String lastSelectHeadStr;
    private PopupManagerUtil popupManagerUtil;
    private PopupManagerUtil.Listen popupManagerUtilListen=new PopupManagerUtil.Listen() {
        @Override
        public void err(String err) {
        }

        @Override
        public BasePopupWindow createPopupWindow(String popupType) {
            if(TextUtils.isEmpty(popupType)){
                return null;
            }
            if(popupType.equals(DrawingHeadSetPopupWindow.class.getSimpleName())){
                return new DrawingHeadSetPopupWindow(context,basePoputWindowListen);
            }
            return null;
        }
    };
    private BasePoputWindowListen basePoputWindowListen = new BasePoputWindowListen() {
        @Override
        public void getWAndH(int w, int h) {

        }

        @Override
        public void event(String eventType, Object... objects) {
                if(TextUtils.isEmpty(eventType)){
                    return;
                }
                if(eventType.equals(DrawingHeadSetPopupWindow.class.getSimpleName())){
                    String headSetContent= (String) objects[0];
                    headSetPopup(headSetContent);
                    return;
                }
        }

        private void headSetPopup(String headSetContent){

        }

    };

    private DrawHeadActivityAdapter.ViewHodler lastSelectViewHodler;
    private DrawHeadActivityAdapter drawHeadActivityAdapter;

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
                DrawShare.getInstance().putHeadLastSelectStr(lastSelectHeadStr);
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
        lastSelectHeadStr = DrawShare.getInstance().getHeadLastSelectStr();
        if (TextUtils.isEmpty(lastSelectHeadStr)) {
            lastSelectHeadStr = getString(R.string.head_manuscripts);
        }
        drawHeadActivityAdapter = new DrawHeadActivityAdapter(context, drawHeadBeanList, itemClick, lastSelectHeadStr);
        popupManagerUtil=new PopupManagerUtil(popupManagerUtilListen);
    }


    @Override
    public void initControl() {

        setIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupManagerUtil.showByDown(DrawingHeadSetPopupWindow.class.getSimpleName(),setIV,-100,-120);
            }
        });

        viewPager.setAdapter(fragmentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headRV.setLayoutManager(linearLayoutManager);
        headRV.setAdapter(drawHeadActivityAdapter);
        Class needToCls = DrawShare.getInstance().getLastOpenActivity();
        if (null != needToCls&&!needToCls.equals(getClass())) {
            toActivity(needToCls);
        }

    }

    @Override
    public void closeActivity() {
        popupManagerUtil.close();
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
