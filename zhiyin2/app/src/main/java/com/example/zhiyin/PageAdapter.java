package com.example.zhiyin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    private String[] title=new String[]{"首页","课程表","我"};
    public PageAdapter(FragmentManager manager){
        super(manager);
    }
    @Override
    public Fragment getItem(int i) {
        if(i==0){
            return new FirstFragment();
        }else if(i==1){
            return new SecondFragment();
        }else {
            return new ThirdFragment();
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        return title[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
