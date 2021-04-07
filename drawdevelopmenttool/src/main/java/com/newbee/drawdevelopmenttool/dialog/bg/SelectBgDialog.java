package com.newbee.drawdevelopmenttool.dialog.bg;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;
import com.lixiao.build.mybase.appliction.BaseApplication;
import com.lixiao.build.mybase.dialog.BaseDialog;
import com.lixiao.build.mybase.dialog.BaseDialogListen;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.adapters.ShowBgAdapter;
import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
import com.newbee.drawdevelopmenttool.bean.content.ContentPagerBean;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

import java.util.ArrayList;


public class SelectBgDialog extends BaseDialog {

    private Context context;
    private TextView titleTV;
    private RecyclerView recyclerView;
    private ShowBgAdapter adapter;
    private int nowSelectBgTypeIndex=-1;
    private ShowBgAdapter.ItemClick itemClick=new ShowBgAdapter.ItemClick() {
        @Override
        public void init(int position) {
            nowSelectBgTypeIndex=position;
        }

        @Override
        public void clickPenRs(int rs, int position) {
            nowSelectBgTypeIndex=position;
        }

    };
    private Button oneBT,defBT,allBT,cancelBT;
    private BaseDialogListen dialogListen;

    public SelectBgDialog(Context context,BaseDialogListen dialogListen){
        this.context=context;
        this.dialogListen=dialogListen;
    }

    @Override
    protected int bindView() {
        return R.layout.dialog_select_bg;
    }

    @Override
    protected void initView(View view) {
        titleTV=view.findViewById(R.id.tv_title);
        recyclerView=view.findViewById(R.id.rv_show);
        titleTV.setText(BaseApplication.getRsString(R.string.show_bg_title));
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new ShowBgAdapter(getContext(),itemClick);
        recyclerView.setAdapter(adapter);
        oneBT=view.findViewById(R.id.bt_one);
        defBT=view.findViewById(R.id.bt_def);
        allBT=view.findViewById(R.id.bt_all);
        cancelBT=view.findViewById(R.id.bt_cancel);
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null==headBean||nowSelectBgTypeIndex==-1){
                    return;
                }
                ContentPagerBean pagerBean= DrawShare.getInstance().getContentPagerBean(headBean.getId());

                if(v.getId()==R.id.bt_one){
                    pagerBean.getDrawBgMap().put(pagerBean.getShowPagerNumb()+"",nowSelectBgTypeIndex);
                    pagerBean.saveToShare();
                    dialogListen.event(SelectBgDialog.this.getClass().getSimpleName());
                }else if(v.getId()==R.id.bt_def){
                    pagerBean.getDrawBgMap().put(pagerBean.getShowPagerNumb()+"",nowSelectBgTypeIndex);
                    headBean.getContentHeadOtherBean().setDefDrawBgType(nowSelectBgTypeIndex);
                    pagerBean.saveToShare();
                    ContentHeadSqlServer.getInstance().update(headBean);
                    dialogListen.event(SelectBgDialog.this.getClass().getSimpleName());
                }else if(v.getId()==R.id.bt_all){
                    pagerBean.getDrawBgMap().clear();
                    headBean.getContentHeadOtherBean().setDefDrawBgType(nowSelectBgTypeIndex);
                    pagerBean.saveToShare();
                    ContentHeadSqlServer.getInstance().update(headBean);
                    dialogListen.event(SelectBgDialog.this.getClass().getSimpleName());
                }else if(v.getId()==R.id.bt_cancel){
                }
                hide();
            }
        };
        oneBT.setOnClickListener(onClickListener);
        oneBT.setVisibility(View.GONE);
        defBT.setOnClickListener(onClickListener);
        defBT.setVisibility(View.GONE);
        allBT.setOnClickListener(onClickListener);
        cancelBT.setOnClickListener(onClickListener);
    }

    @Override
    protected Context getContext() {
        return context;
    }

    ContentHeadBean headBean;
    @Override
    public void show(Object... objects) {
        super.show(objects);
        if(objects.length>0){
           headBean= (ContentHeadBean) objects[0];
           adapter.setHeadBean(headBean);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void closeNeedDo() {

    }
}
