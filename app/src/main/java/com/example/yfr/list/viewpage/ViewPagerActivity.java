package com.example.yfr.list.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yfr.list.R;
import com.google.common.collect.Lists;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private List<PicFragment> picFragmentList;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle startInstance) {
        super.onCreate(startInstance);
        setContentView(R.layout.view_pager_main);

        initData();

        viewPager=findViewById(R.id.pic_view);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){


            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                    Toast.makeText(ViewPagerActivity.this,"第"+position+"图",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });//设置页面切换时的监听器(可选，用了之后要重写它的回调方法处理页面切换时候的事务)
        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), picFragmentList));
        viewPager.setPageTransformer(true,new DepthPageTransformer());


    }

    private void initData() {
        picFragmentList= Lists.newArrayList();

        PicFragment fragment1=new PicFragment();
        PicFragment fragment2=new PicFragment();
        PicFragment fragment3=new PicFragment();
        PicFragment fragment4=new PicFragment();


        Bundle bundle=new Bundle();
        bundle.putParcelable("picEntity",new ViewPageEntity(R.mipmap.common_icon_black_back,"第一张图",0,false));
        fragment1.setArguments(bundle);
        picFragmentList.add(fragment1);

        Bundle bundle2=new Bundle();
        bundle2.putParcelable("picEntity",new ViewPageEntity(R.mipmap.common_icon_right_arrow,"第二张图",1,false));
        fragment2.setArguments(bundle2);
        picFragmentList.add(fragment2);

        Bundle bundle3=new Bundle();
        bundle3.putParcelable("picEntity",new ViewPageEntity(R.mipmap.img,"第三张图",2,false));
        fragment3.setArguments(bundle3);
        picFragmentList.add(fragment3);

        Bundle bundle4=new Bundle();
        bundle4.putParcelable("picEntity",new ViewPageEntity(R.mipmap.firstpic,"第四张图",3,false));
        fragment4.setArguments(bundle4);
        picFragmentList.add(fragment4);

    }
}
