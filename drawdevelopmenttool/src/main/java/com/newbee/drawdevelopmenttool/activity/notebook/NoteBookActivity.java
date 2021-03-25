package com.newbee.drawdevelopmenttool.activity.notebook;


import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.gson.MyGson;
import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.view.NoScrollViewPager;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.adapters.ContentHeadAdapter;
import com.newbee.drawdevelopmenttool.adapters.FragmentAdapter;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.fragment.draw.base.BaseDrawViewFragment;
import com.newbee.drawdevelopmenttool.fragment.draw.notebook.NoteBookFragment;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;
import java.util.ArrayList;
import java.util.List;


public class NoteBookActivity extends BaseCompatActivity {
    private ContentHeadBean selectContentHeadBean;
    private NoScrollViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;
    private BaseDrawViewFragment lastSelectFragment;
    private ResultContentHeadBean openResultContentHeadBean;
    private RecyclerView contentHeadRV;
    private ContentHeadAdapter contentHeadAdapter;
    private ContentHeadAdapter.ItemClick itemClick = new ContentHeadAdapter.ItemClick() {
        private TextView lastContentHeadTV;
        private RelativeLayout lastContentHeadRL;

        @Override
        public void contentHeadClose(ContentHeadBean contentHeadBean) {
            openResultContentHeadBean.remove(contentHeadBean);
            if (checkAndPutShare()) {
                reSetContentHeadRV();
                reSetViewPager();
            }
        }


        @Override
        public void itemClick(TextView contentHeadTV, RelativeLayout contentHeadRL, ContentHeadBean contentHeadBean) {
            if (null != lastContentHeadRL && null != lastContentHeadTV) {
                if (lastContentHeadRL == contentHeadRL && lastContentHeadTV == contentHeadTV) {
                    return;
                } else {
                    lastContentHeadRL.setBackgroundColor(getResources().getColor(R.color.gray));
                    lastContentHeadTV.setTextColor(getResources().getColor(R.color.draw_content_head_tv));
                }
            }
            lastContentHeadRL = contentHeadRL;
            lastContentHeadTV = contentHeadTV;


            selectContentHeadBean = contentHeadBean;
            lastContentHeadRL.setBackgroundColor(getResources().getColor(R.color.draw_bg));
            lastContentHeadTV.setTextColor(getResources().getColor(R.color.black));
            long selectId = selectContentHeadBean.getId();
            if (null != fragmentList && selectId != 0) {
                for (int i = 0; i < fragmentList.size(); i++) {
                    final BaseDrawViewFragment drawingFragment = (BaseDrawViewFragment) fragmentList.get(i);
                    if (selectId == (drawingFragment.getContentHeadId())) {
                        viewPager.setCurrentItem(i);
                        drawingFragment.needInitDrawView();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if (null != lastSelectFragment) {
                                    lastSelectFragment.autoSave();
                                }
                                lastSelectFragment = drawingFragment;
                            }
                        }).start();
                        return;
                    }
                }
            }
        }
    };
    private int maxShowNumb;


    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_note_book_main;
    }

    @Override
    public void initView() {
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = 0
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  // hide naviagtion,and whow again when touch
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        viewPager = findViewById(R.id.vp_fm);
        contentHeadRV = findViewById(R.id.rv_draw_content_head);
    }

    @Override
    public void initData() {
        maxShowNumb = DrawShare.getInstance().getContentMaxShowNumb();
        if (maxShowNumb < 1 || maxShowNumb > 6) {
            maxShowNumb = 6;
        }
        openResultContentHeadBean = DrawShare.getInstance().getOpenContentHeadList(tag);
        if (null == openResultContentHeadBean || null == openResultContentHeadBean.getContentHeadBeanList() || openResultContentHeadBean.getContentHeadBeanList().size() == 0) {
            openResultContentHeadBean = new ResultContentHeadBean();
        } else {
            ResultContentHeadBean needResultContenHeadBean = new ResultContentHeadBean();
            for (ContentHeadBean contentHeadBean : openResultContentHeadBean.getContentHeadBeanList()) {
                ResultContentHeadBean resultContentHeadBean= ContentHeadSqlServer.getInstance().queById(contentHeadBean.getId());
                if(null!=resultContentHeadBean&&null!=resultContentHeadBean.getContentHeadBeanList()&&resultContentHeadBean.getContentHeadBeanList().size()==1){
                    needResultContenHeadBean.add(contentHeadBean);
                }
            }
            openResultContentHeadBean = needResultContenHeadBean;
        }
    }

    @Override
    public void initControl() {

    }

    @Override
    public void closeActivity() {
        viewPager.removeAllViews();
        LG.i(tag, "now is close()---------------11111111111");
    }

    @Override
    public void viewIsShow() {
        selectContentHeadBean = MyGson.getInstance().fromJson(getIntentString(), ContentHeadBean.class);
        if (null != selectContentHeadBean) {
            openResultContentHeadBean.addToFirst(selectContentHeadBean);
        }
        if (checkAndPutShare()) {
            setContentHeadRV();
            setViewPager();
        }
    }

    @Override
    public void viewIsPause() {
        if (null != lastSelectFragment) {
            lastSelectFragment.autoSave();
        }

    }

    @Override
    public void changeConfig() {
        if (checkAndPutShare()) {
            setContentHeadRV();
            setViewPager();
        }
    }


    private void setContentHeadRV() {
        long selectId = 0;
        if (null != selectContentHeadBean) {
            selectId = selectContentHeadBean.getId();
        }
        contentHeadAdapter = new ContentHeadAdapter(getApplicationContext(), openResultContentHeadBean.getContentHeadBeanList(), itemClick, getNeedShowNumb(), selectId);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, getNeedShowNumb());
        contentHeadRV.setLayoutManager(gridLayoutManager);
        contentHeadRV.setAdapter(contentHeadAdapter);
    }

    private void reSetContentHeadRV() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, getNeedShowNumb());
        contentHeadRV.setLayoutManager(gridLayoutManager);
        contentHeadAdapter.setList(openResultContentHeadBean.getContentHeadBeanList());
        contentHeadAdapter.notifyDataSetChanged();
    }

    private int getNeedShowNumb() {
        int needShowNumb = openResultContentHeadBean.getContentHeadBeanList().size();
        if (needShowNumb > maxShowNumb) {
            needShowNumb = maxShowNumb;
        }
        if (needShowNumb < 1) {
            needShowNumb = 1;
        }
        return needShowNumb;
    }

    private void setViewPager() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < getNeedShowNumb(); i++) {
            ContentHeadBean contentHeadBean = openResultContentHeadBean.getContentHeadBeanList().get(i);

            NoteBookFragment noteBookFragment=new NoteBookFragment(contentHeadBean);


            if (null == lastSelectFragment) {
                lastSelectFragment = noteBookFragment;
                lastSelectFragment.needInitDrawView();
            }
            fragmentList.add(noteBookFragment);
        }
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);

    }

    private void reSetViewPager() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < getNeedShowNumb(); i++) {
            ContentHeadBean contentHeadBean = openResultContentHeadBean.getContentHeadBeanList().get(i);
            NoteBookFragment drawingFragment = new NoteBookFragment(contentHeadBean);
            fragmentList.add(drawingFragment);
        }
        fragmentAdapter.setList(fragmentList);
        fragmentAdapter.notifyDataSetChanged();
    }

    private boolean checkAndPutShare() {
        if (null == openResultContentHeadBean || openResultContentHeadBean.getContentHeadBeanList().size() == 0) {
            finish();
            DrawShare.getInstance().putOpenContentHeadList(tag, null);
            return false;
        }
        ResultContentHeadBean resultContentHeadBean = ContentHeadSqlServer.getInstance().getAll();
        if (null == resultContentHeadBean) {
            openResultContentHeadBean = new ResultContentHeadBean();
        } else {
            for (int i = 0; i < openResultContentHeadBean.getContentHeadBeanList().size(); i++) {
                ContentHeadBean openContentHead = openResultContentHeadBean.getContentHeadBeanList().get(i);
                boolean isExist = false;
                for (int j = 0; j < resultContentHeadBean.getContentHeadBeanList().size(); j++) {
                    ContentHeadBean checkContentHead = resultContentHeadBean.getContentHeadBeanList().get(j);
                    if (openContentHead.getId() != 0 && openContentHead.getId() == (checkContentHead.getId())) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    openResultContentHeadBean.getContentHeadBeanList().remove(i);
                    i--;
                }
            }
        }
        if (null == openResultContentHeadBean || openResultContentHeadBean.getContentHeadBeanList().size() == 0) {
            DrawShare.getInstance().putOpenContentHeadList(tag, null);
            finish();
            return false;
        }
        DrawShare.getInstance().putOpenContentHeadList(tag, openResultContentHeadBean);
        return true;
    }
}
