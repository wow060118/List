package com.example.yfr.list.banner;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yfr.list.R;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午3:14 2018/12/19
 * @Modified_By:
 */
public class BannerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        View.OnTouchListener{

    private TextView mTextViewDesc;
    private int position;
    private ViewPager mViewPager;
    private LinearLayout mDotLinearLayout;
    private BannerAdapter bannerAdapter;
    private int[] resTxt = {R.string.title_1, R.string.title_2, R.string.title_3};
    private String[] arrUrl = {"http://img5.imgtn.bdimg.com/it/u=3218480149,1328367548&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3218480149,1328367548&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3218480149,1328367548&fm=21&gp=0.jpg"};
    private AutoCycleTask  mAutoCycleTask;


    @Override
    protected void onCreate(Bundle startInstanceState) {
        super.onCreate(startInstanceState);
        setContentView(R.layout.banner);
        initView();
        performDot();
        performViewPager();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mDotLinearLayout = findViewById(R.id.dot_container);
        mTextViewDesc = findViewById(R.id.tv_img_desc);
    }


    private void performDot() {
        for (int i = 0; i < resTxt.length; i++) {
            View dotView = new View(this);
            dotView.setBackgroundResource(R.drawable.dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            // 第一个原点不设置左边距
            if (i != 0) {
                params.leftMargin = 15;
            }
            dotView.setVisibility(View.INVISIBLE);
            dotView.setLayoutParams(params);
            mDotLinearLayout.addView(dotView);
        }
    }


    private void performViewPager() {
        if (bannerAdapter == null) {
            bannerAdapter = new BannerAdapter(this, arrUrl);
        }
        mViewPager.setAdapter(bannerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);
        // Integer.MAX_VALUE / 2 有可能是任何item的位置 - 余数则让他和集合的pos=0的位置吻合
        mViewPager.setCurrentItem(0);// 设置初始位置
        // 将第一个圆点设置选中的颜色
        mDotLinearLayout.getChildAt(position).setVisibility(View.VISIBLE);
        mTextViewDesc.setText(resTxt[position]);
        // 实现 自动切换
        autoCycle();
    }

    private void autoCycle() {
        if (mAutoCycleTask == null) {
            mAutoCycleTask = new AutoCycleTask();
        }
        mAutoCycleTask.start();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        int newPos = position % mDotLinearLayout.getChildCount();
        mDotLinearLayout.getChildAt(this.position).setVisibility(View.INVISIBLE);
        mDotLinearLayout.getChildAt(newPos).setVisibility(View.VISIBLE);
        mTextViewDesc.setText(resTxt[newPos]);
        this.position = newPos;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mAutoCycleTask.stop();
                break;
            case MotionEvent.ACTION_MOVE:
                mAutoCycleTask.stop();
                break;
            case MotionEvent.ACTION_UP:
                mAutoCycleTask.start();
                break;
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAutoCycleTask.removeCallbacksAndMessages(null);
        mAutoCycleTask = null;
    }

    class AutoCycleTask  extends Handler implements Runnable {
        @Override
        public void run() {
            // 设置轮播下一图
            int currentItem = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++currentItem);
            postDelayed(this, 2000);
        }

        public void start() {
            postDelayed(this, 2000);
        }

        public void stop() {
            removeCallbacks(this);
        }
    }
}
