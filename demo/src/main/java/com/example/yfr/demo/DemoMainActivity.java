package com.example.yfr.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yfr.demo.demomainfragment.DemoMainPicAdapter;
import com.example.yfr.demo.entity.MainPicEntity;
import com.example.yfr.demo.viewpager.HorizontalVerticalViewPager;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午3:06 2018/12/21
 * @Modified_By:
 */
public class DemoMainActivity extends AppCompatActivity {
    //提示文案 点击关闭
    private ViewStub guideViewStub;
    private TextView textView;
    private View view;


    private HorizontalVerticalViewPager mViewPager;

    private List<MainPicEntity> picEntityList= Lists.newArrayList();


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.demo_main_layout);

        initData();
        initView();
        initEvent();

        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            Log.i("first", "第一次进入");
            // 、 进行第一次判断
            guideViewStub.inflate();
            Toast.makeText(DemoMainActivity.this, "", Toast.LENGTH_SHORT).show();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }

    }

    private void initData() {

        picEntityList.add(new MainPicEntity(R.drawable.common_icon_theme_bg,R.drawable.player_big_like,"杨丰瑞"));
        picEntityList.add(new MainPicEntity(R.drawable.demo_pic,R.drawable.player_big_like,"杨丰瑞"));
        picEntityList.add(new MainPicEntity(R.drawable._p_icon_follow,R.drawable.player_big_like,"杨丰瑞"));
        picEntityList.add(new MainPicEntity(R.drawable.common_icon_right_arrow,R.drawable.player_big_like,"杨丰瑞"));
        picEntityList.add(new MainPicEntity(R.drawable.common_icon_black_back,R.drawable.player_big_like,"杨丰瑞"));

    }

    //初始化事件
    private void initEvent() {
        //不知道为什么一直报空指异常。。。
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                guideViewStub.setVisibility(View.INVISIBLE);
//            }
//        });
    }

    //初始化视图
    private void initView() {
        guideViewStub = findViewById(R.id.guide);
        textView=findViewById(R.id.guide_text);
        mViewPager=findViewById(R.id.show_viewpager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){


            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                System.out.println(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });//设置页面切换时的监听器(可选，用了之后要重写它的回调方法处理页面切换时候的事务)
        mViewPager.setAdapter(new DemoMainPicAdapter(getSupportFragmentManager(), picEntityList));
    }

}
