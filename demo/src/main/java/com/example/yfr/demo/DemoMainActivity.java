package com.example.yfr.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yfr.demo.entity.MainPicEntity;
import com.example.yfr.demo.util.SharedPreferenceUtil;
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
    private Button button;

    private HorizontalVerticalViewPager mViewPager;

    private List<MainPicEntity> picEntityList= Lists.newArrayList();

    private NetWorkChangeBroadcast broadcast;
    private CircleProgressView circleProgressView;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.demo_main_layout);

        initData();
        initView();
        initEvent();
        boolean isFirstRun = SharedPreferenceUtil.getInstance(this,"share").getParam("isFirstRun",true);
        Long param = SharedPreferenceUtil.getInstance(this, "share").getParam("time", System.currentTimeMillis());
        System.out.println("currentTime"+System.currentTimeMillis());

        System.out.println("param:"+param);
        Log.i("isFirstRun", "isFirstRun:"+isFirstRun);
        if (isFirstRun) {
            Log.i("first", "第一次进入");
            // 、 进行第一次判断
            guideViewStub.inflate();
            Toast.makeText(DemoMainActivity.this, "第一次进入", Toast.LENGTH_SHORT).show();
            SharedPreferenceUtil.getInstance(this,"share").setParam("isFirstRun",false);

        }
//        System.out.println("llllllllllllllllllllllll");
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("开启推送？");
//        builder.setPositiveButton("确定", (dialog, which) -> {
//
//        }).setNegativeButton("取消", ((dialog, which) -> {
//
//        }));
//        AlertDialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//        button=findViewById(R.id.setup);
//        button.setOnClickListener(v->{
//            Intent intent=new Intent(DemoMainActivity.this,LikedFallActivity.class);
//            startActivity(intent);
//        });
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
        circleProgressView= findViewById(R.id.circle);
//        mViewPager=findViewById(R.id.show_viewpager);
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
//
//
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                System.out.println(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });//设置页面切换时的监听器(可选，用了之后要重写它的回调方法处理页面切换时候的事务)
//        mViewPager.setAdapter(new DemoMainPicAdapter(getSupportFragmentManager(), picEntityList));

//        Intent intent=new Intent();
//        intent.setAction("andorid.net.conn.CONNECTIVITY_CHANGE");
//        broadcast=new NetWorkChangeBroadcast();
//        broadcast.onReceive(getApplicationContext(),intent);
//        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.
    }

    private class NetWorkChangeBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("change","网络改变");
            Toast.makeText(context,"网络改变",Toast.LENGTH_SHORT).show();
        }
    }
}
