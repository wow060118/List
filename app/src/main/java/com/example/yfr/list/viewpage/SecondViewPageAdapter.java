package com.example.yfr.list.viewpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SecondViewPageAdapter extends FragmentPagerAdapter {

    List list;

    public SecondViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public SecondViewPageAdapter(FragmentManager fm,List list) {
        super(fm);
        this.list=list;
    }//写构造方法，方便赋值调用

    @Override
    public Fragment getItem(int arg0) {
        return (Fragment) list.get(arg0);
    }//根据Item的位置返回对应位置的Fragment，绑定item和Fragment

    @Override
    public int getCount() {
        return list.size();
    }//设置Item的数量
}
