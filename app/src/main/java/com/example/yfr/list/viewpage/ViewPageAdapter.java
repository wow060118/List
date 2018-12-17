package com.example.yfr.list.viewpage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {
    List<PicFragment> list;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(FragmentManager fm,List<PicFragment> list) {
        super(fm);
        this.list=list;
    }//写构造方法，方便赋值调用

    @Override
    public PicFragment getItem(int arg0) {
        return list.get(arg0);
    }//根据Item的位置返回对应位置的Fragment，绑定item和Fragment

    @Override
    public int getCount() {
        return list.size();
    }//设置Item的数量

}
