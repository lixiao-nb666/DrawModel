package com.newbee.drawdevelopmenttool.dialog.wastepaper;

import android.content.Context;
import android.view.View;
import com.lixiao.build.mybase.dialog.BaseDialog;
import com.newbee.drawdevelopmenttool.R;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2020/11/16 0016 17:46
 */
public class WastepaperBasketDialog extends BaseDialog {

    private Context context;
    private ItemClick itemClick;
    public WastepaperBasketDialog(Context context, ItemClick itemClick) {
        this.context=context;
        this.itemClick=itemClick;
    }



    @Override
    protected int bindView() {
        return R.layout.dialog_wastapaper_basket;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.bt_retrun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.pagerRetrun();
            }
        });
        view.findViewById(R.id.bt_que_delect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.delectQue();
            }
        });
        view.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
    }

    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected void closeNeedDo() {

    }

    public interface ItemClick{

        public void pagerRetrun();

        public void delectQue();


    }
}
