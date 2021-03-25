package com.newbee.drawdevelopmenttool.activity.configset.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lixiao.build.mybase.LG;
import com.lixiao.view.radiogroup.MyRadioDataInfoBean;
import com.lixiao.view.radiogroup.MyRadioGroup;
import com.newbee.drawdevelopmenttool.R;
import com.newbee.drawdevelopmenttool.activity.configset.bean.ConfigSetInfoBean;
import com.newbee.drawdevelopmenttool.adapters.DrawHeadActivityAdapter;
import com.newbee.drawdevelopmenttool.bean.head.DrawHeadBean;

import java.util.List;

/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/3/24 0024 11:35
 */
public class ConfigSetAdapter extends RecyclerView.Adapter{
    private String tag = getClass().getName() + ">>>>";
    private LayoutInflater layoutInflater;
    private List<ConfigSetInfoBean> list;
    private ItemClick itemClick;

    public ConfigSetAdapter(Context context, List<ConfigSetInfoBean> list, ItemClick itemClick) {
        layoutInflater = LayoutInflater.from(context);
        this.list=list;
        this.itemClick = itemClick;

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHodler viewHodler=new ViewHodler(layoutInflater.inflate(R.layout.adapter_config_set,null));
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHodler viewHodler= (ViewHodler) holder;
        final ConfigSetInfoBean configSetInfoBean=list.get(position);
        viewHodler.nameTV.setText(configSetInfoBean.getShowName());
        MyRadioGroup.ItemClick mrgItemClick=new MyRadioGroup.ItemClick() {
            @Override
            public void initSelect(MyRadioDataInfoBean dataInfoBean) {

            }

            @Override
            public void nowSelect(MyRadioDataInfoBean dataInfoBean) {
                itemClick.nowSelect(configSetInfoBean.getShowName(),dataInfoBean);
                LG.i(tag,"nowSelectshenmegui:"+configSetInfoBean.getShowName()+"---------"+dataInfoBean);
            }
        };
        viewHodler.mrg.setListen(mrgItemClick);
        viewHodler.mrg.setList(configSetInfoBean.getDataInfoBeanList(),configSetInfoBean.getDefType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        TextView nameTV;
        MyRadioGroup mrg;

        public ViewHodler(View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.name);
            mrg=itemView.findViewById(R.id.mrg);
            mrg.setSize(MyRadioGroup.LayoutParams.MATCH_PARENT,MyRadioGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public interface ItemClick{

        public void nowSelect(String name,MyRadioDataInfoBean dataInfoBean);
    }
}
