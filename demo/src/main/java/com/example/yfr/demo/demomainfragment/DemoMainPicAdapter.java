package com.example.yfr.demo.demomainfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.example.yfr.demo.entity.MainPicEntity;

import java.util.List;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午7:57 2018/12/24
 * @Modified_By:
 */
public class DemoMainPicAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MainPicPagerAdapter";
    private Context mContext;
    private List<MainPicEntity> picEntityList;
    private FragmentManager mFragmentManager;
    protected FragmentTransaction mCurTransaction = null;
    protected Fragment mCurrentPrimaryItem = null;


    public DemoMainPicAdapter(FragmentManager fm,List<MainPicEntity> picEntityList) {
        super(fm);
//        mFragmentManager = fm;
        this.picEntityList=picEntityList;
    }

    @Override
    public int getCount() {
        // return arrUrl.lenth;
        //

        return picEntityList.size();
    }

//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }

    @Override
    public Fragment getItem(int i) {
        DemoMainFragment demoMainFragment=new DemoMainFragment();
        demoMainFragment.setMainPicEntity(picEntityList.get(i));
        return demoMainFragment;
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        if(mCurTransaction==null){
//            mCurTransaction=mFragmentManager.beginTransaction();
//        }
//        Fragment fragment=mFragmentManager.findFragmentByTag("MainPicPagerAdapter"+position);
//        if(fragment!=null){
//            ((DemoMainFragment)fragment).setMainPicEntity(picEntityList.get(position));
//            mCurTransaction.attach(fragment);
//        }else {
////            System.out.println(position);
//            fragment = getItem(position);
////            System.out.println(fragment);
//            mCurTransaction.add(container.getId(), fragment,
//                    "MainPicPagerAdapter"+position);
//        }
//        if (fragment != mCurrentPrimaryItem) {
//            fragment.setMenuVisibility(false);
//            fragment.setUserVisibleHint(false);
//        }
//
//        return fragment;
//    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//
//        container.removeView((View) object);
//    }
}
