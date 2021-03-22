package com.newbee.drawdevelopmenttool.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<Fragment> list;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fm = fm;
        this.list = list;

    }

    public void setList(List<Fragment> list){
        this.list = list;
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public List<Fragment> getList() {
        return list;
    }

    public void close() {
        for (Fragment basePlayFragmen : list) {
            try {
                basePlayFragmen.onDestroy();
            } catch (Exception e) {
            }
        }
    }

}
