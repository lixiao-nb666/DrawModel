package com.newbee.drawdevelopmenttool.fragment.head;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lixiao.build.mybase.LG;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.bean.content.ResultContentHeadBean;
import com.newbee.drawdevelopmenttool.config.MyDrawBoardConfig;
import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;
import com.newbee.drawdevelopmenttool.fragment.head.view.HeadFragmentShowContentView;
import com.newbee.drawdevelopmenttool.share.DrawShare;
import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

public class HeadSearchFragment extends BaseHeadFragment {
    private HeadFragmentNeedListen headFragmentNeedListen=new HeadFragmentNeedListen() {
        @Override
        public void clickAdd(View v) {

        }

        @Override
        public void retrunFilePath() {
            doRetrunFile();
        }

        @Override
        public void needReSetFilePath(String filePath) {
            useFilePathQueList(filePath);
        }


    };
    private ImageView startSearchIV, showOldSearchIV;
    private EditText searchContentHeadET;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.iv_start_search){
                setResultContentHeadBean(startSearchGetList(searchContentHeadET.getEditableText().toString()));
            }


        }
    };


    @Override
    public int getViewLayout() {
        return R.layout.fragment_head_search;
    }



    @Override
    protected void initView() {
        lastSearChStr=DrawShare.getInstance().getString(searChShareKey);
        searchContentHeadET = view.findViewById(R.id.et_search_content_head);
        ResultContentHeadBean resultContentHeadBean=null;
        if(!TextUtils.isEmpty(lastSearChStr)){
            searchContentHeadET.setText(lastSearChStr);
            resultContentHeadBean=ContentHeadSqlServer.getInstance().fuzzyQueByName(lastSearChStr, MyDrawBoardConfig.getSerarchNeedDelect());
        }


        headFragmentShowContentView = new HeadFragmentShowContentView(view,this, HeadFragmentShowContentType.SEARCH, getActivity(), resultContentHeadBean, false, false,headFragmentNeedListen);
        startSearchIV = view.findViewById(R.id.iv_start_search);
        showOldSearchIV = view.findViewById(R.id.iv_show_old_search);
        startSearchIV.setOnClickListener(onClickListener);
        showOldSearchIV.setOnClickListener(onClickListener);

        searchContentHeadET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setResultContentHeadBean(startSearchGetList(s.toString()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void viewIsShow() {

    }

    @Override
    protected void viewIsPause() {

    }

    @Override
    protected void close() {
        if (null != headFragmentShowContentView) {
            headFragmentShowContentView.close();
            headFragmentShowContentView = null;
        }
    }

    @Override
    protected void changeConfig() {
        headFragmentShowContentView.setLayoutManager();
    }




    private String searChShareKey=tag+"_searChShareKey";
    private String lastSearChStr;
    private ResultContentHeadBean startSearchGetList(String fuzzyStr) {
        lastSearChStr=fuzzyStr;
        DrawShare.getInstance().putString(searChShareKey,lastSearChStr);
        lastFilePath = "";

        LG.i(tag, "onTextChanged:" + fuzzyStr);
        if (!TextUtils.isEmpty(fuzzyStr)) {
            ResultContentHeadBean resultContentHeadBean = ContentHeadSqlServer.getInstance().fuzzyQueByName(fuzzyStr, MyDrawBoardConfig.getSerarchNeedDelect());

            return  resultContentHeadBean;

        } else {
            return null;

        }
    }


}
