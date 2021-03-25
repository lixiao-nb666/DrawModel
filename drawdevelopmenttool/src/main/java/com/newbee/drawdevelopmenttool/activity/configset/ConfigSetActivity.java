package com.newbee.drawdevelopmenttool.activity.configset;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lixiao.build.mybase.activity.BaseCompatActivity;
import com.lixiao.build.mybase.activity.util.ActivityManager;
import com.lixiao.build.mybase.activity.util.ActivityUtil;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.view.radiogroup.MyRadioDataInfoBean;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.configset.adapter.ConfigSetAdapter;
import com.newbee.drawdevelopmenttool.activity.configset.bean.ConfigSetInfoBean;
import com.newbee.drawdevelopmenttool.activity.init.DrawingHeadActivity;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.config.type.AddContentHeadType;
import com.newbee.drawdevelopmenttool.config.type.ShowContentHeadType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 11:09
 */
public class ConfigSetActivity extends BaseCompatActivity {
    private RecyclerView rv;
    private ConfigSetAdapter adapter;

    private ConfigSetAdapter.ItemClick itemClick=new ConfigSetAdapter.ItemClick() {
        @Override
        public void nowSelect(String name, MyRadioDataInfoBean dataInfoBean) {
            if(name.equals(BaseApplication.getRsString(R.string.show_content_head_type))){
                    MyDrawBoardConfig.setShowContentHeadType(ShowContentHeadType.valueOf(dataInfoBean.getType()));
                    ActivityManager.getInstance().finishAllActivity();
                    toActivity(DrawingHeadActivity.class);
                    showToast(BaseApplication.getRsString(R.string.set_ok_need_restart));
            }else if(name.equals(BaseApplication.getRsString(R.string.add_content_head_type))){
                    MyDrawBoardConfig.setAddContentHeadType(AddContentHeadType.valueOf(dataInfoBean.getType()));

            }else if(name.equals(BaseApplication.getRsString(R.string.search_need_rbs))){
                    MyDrawBoardConfig.setSearchNeedDelect(Boolean.valueOf(dataInfoBean.getType()));
            }
        }


    };

    @Override
    public int getViewLayoutRsId() {
        return R.layout.activity_config_set;
    }

    @Override
    public void initView() {
        rv=findViewById(R.id.rv);
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        List<ConfigSetInfoBean> dateList = new ArrayList<>();
        dateList.add(getAddContentHeadTypeConfigSetInfoBean());
        dateList.add(getShowContentHeadConfigSetInfoBean());
        dateList.add(getSearchNeedConfigSetInfoBean());
        adapter=new ConfigSetAdapter(context,dateList,itemClick);
    }

    private ConfigSetInfoBean getShowContentHeadConfigSetInfoBean(){
        ConfigSetInfoBean showContentHeadConfigSetInfoBean=new ConfigSetInfoBean();
        showContentHeadConfigSetInfoBean.setShowName(BaseApplication.getRsString(R.string.show_content_head_type));
        showContentHeadConfigSetInfoBean.setDefType(MyDrawBoardConfig.getShowContentHeadType().toString());
        MyRadioDataInfoBean showContentHeadIsRv=new MyRadioDataInfoBean();
        showContentHeadIsRv.setShowText(BaseApplication.getRsString(R.string.scroll_rv));
        showContentHeadIsRv.setType(ShowContentHeadType.RV.toString());
        MyRadioDataInfoBean showContentHeadIsPager=new MyRadioDataInfoBean();
        showContentHeadIsPager.setShowText(BaseApplication.getRsString(R.string.scroll_pager));
        showContentHeadIsPager.setType(ShowContentHeadType.PAGER.toString());
        showContentHeadConfigSetInfoBean.add(showContentHeadIsRv);
        showContentHeadConfigSetInfoBean.add(showContentHeadIsPager);
        return showContentHeadConfigSetInfoBean;
    }

    private ConfigSetInfoBean getAddContentHeadTypeConfigSetInfoBean(){
        ConfigSetInfoBean addContentHeadTypeConfigSetInfoBean=new ConfigSetInfoBean();
        addContentHeadTypeConfigSetInfoBean.setShowName(BaseApplication.getRsString(R.string.add_content_head_type));
        addContentHeadTypeConfigSetInfoBean.setDefType(MyDrawBoardConfig.getAddContentHeadType().toString());
        MyRadioDataInfoBean data1 =new MyRadioDataInfoBean();
        data1.setShowText(BaseApplication.getRsString(R.string.popupwindow));
        data1.setType(AddContentHeadType.USE_POPUPWINDOW.toString());
        MyRadioDataInfoBean data2 =new MyRadioDataInfoBean();
        data2.setShowText(BaseApplication.getRsString(R.string.new_activity));
        data2.setType(AddContentHeadType.USE_ACTIVITY.toString());
        addContentHeadTypeConfigSetInfoBean.add(data1);
        addContentHeadTypeConfigSetInfoBean.add(data2);
        return addContentHeadTypeConfigSetInfoBean;
    }

    private ConfigSetInfoBean getSearchNeedConfigSetInfoBean(){
        ConfigSetInfoBean configSetInfoBean=new ConfigSetInfoBean();
        configSetInfoBean.setShowName(BaseApplication.getRsString(R.string.search_need_rbs));
        configSetInfoBean.setDefType(MyDrawBoardConfig.getSerarchNeedDelect()+"");
        MyRadioDataInfoBean data1 =new MyRadioDataInfoBean();
        data1.setShowText(BaseApplication.getRsString(R.string.str_true));
        data1.setType(true+"");
        MyRadioDataInfoBean data2 =new MyRadioDataInfoBean();
        data2.setShowText(BaseApplication.getRsString(R.string.str_false));
        data2.setType(false+"");
        configSetInfoBean.add(data1);
        configSetInfoBean.add(data2);
        return configSetInfoBean;
    }

    @Override
    public void initControl() {
        rv.setAdapter(adapter);
    }

    @Override
    public void closeActivity() {

    }

    @Override
    public void viewIsShow() {

    }

    @Override
    public void viewIsPause() {
        finish();
    }

    @Override
    public void changeConfig() {

    }
}
