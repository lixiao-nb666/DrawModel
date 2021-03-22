package com.newbee.drawdevelopmenttool.fragment.head.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newbee.drawdevelopmenttool.R;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/19 0019 20:59
 */
public class HeadFragmentShowContentPagerView {
    private TextView showPagerTV;
    private ImageView nextIV,lastIv;
    public int nowPager=1;
    public int countPager=1;

    public HeadFragmentShowContentPagerView(View view){
        view.setVisibility(View.VISIBLE);
        showPagerTV=view.findViewById(R.id.tv_pager_numb);
        nextIV=view.findViewById(R.id.iv_next_pager);
        lastIv=view.findViewById(R.id.iv_last_pager);
        nextIV.setImageResource(R.drawable.icon_next_pager);
        nextIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=listen){
                    listen.needNext();
                }
            }
        });
        lastIv.setImageResource(R.drawable.icon_last_pager);
        lastIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=listen){
                    listen.needLast();
                }
            }
        });
    }

    private IvClickListen listen;
    public void setList(IvClickListen listen){
        this.listen=listen;
    }

    public void close(){

    }

    public void setText(int nowPager,int countPager){
        showPagerTV.setText(nowPager+"/"+countPager);
    }

    public interface IvClickListen{
        public void needLast();

        public void needNext();

    }

}
