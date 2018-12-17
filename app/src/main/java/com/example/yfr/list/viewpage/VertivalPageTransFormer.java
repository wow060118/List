package com.example.yfr.list.viewpage;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class VertivalPageTransFormer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    @Override
    public void transformPage(View view, float position) {

        if (position < -1) { // [-Infinity,-1)
            // 当前页的上一页
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            view.setAlpha(1);

            // 抵消默认幻灯片过渡
            view.setTranslationX(view.getWidth() * -position);

            //设置从上滑动到Y位置
            float yPosition = position * view.getHeight();
            view.setTranslationY(yPosition);

        } else { // (1,+Infinity]
            // 当前页的下一页
            view.setAlpha(0);
        }
    }
}
