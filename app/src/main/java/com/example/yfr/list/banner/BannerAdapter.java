package com.example.yfr.list.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yfr.list.R;
import com.squareup.picasso.Picasso;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午2:39 2018/12/19
 * @Modified_By:
 */
public class BannerAdapter extends PagerAdapter {
    private static final String TAG = "MyPagerAdapter";
    private Context mContext;
    private String[] mArrUrl;

    public BannerAdapter(Context context, String[] arrUrl) {
        mContext = context;
        mArrUrl = arrUrl;
    }

    @Override
    public int getCount() {
        // return arrUrl.lenth;
        // 循环用
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // position此时的总大小是Integer.MAX_VALUE,需要转换(position本来就跟自然数差1了)
        // 循环用
        position = position % mArrUrl.length;
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 使用picasso加载图片
        Picasso.with(mContext).load(mArrUrl[position]).error(R.mipmap.img).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
