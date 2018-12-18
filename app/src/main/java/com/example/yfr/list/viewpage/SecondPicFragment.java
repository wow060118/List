package com.example.yfr.list.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yfr.list.R;
import com.google.common.collect.Lists;

import java.util.List;

public class SecondPicFragment extends Fragment {
    private List picFragmentList;
    private ViewPageEntity viewPageEntity;
    private View mView;
    private TextView tag;
    private ImageView pic;
    private VerticalViewPager viewPager;
    private GestureDetector mGestureDetector;

    @Override
    public void onActivityCreated(Bundle saveInstaceState) {
        super.onActivityCreated(saveInstaceState);
        init();
        initData();

        viewPager = mView.findViewById(R.id.second_pic_view_fragment);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getContext(), "向下滑动第" + position + "图", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });//设置页面切换时的监听器(可选，用了之后要重写它的回调方法处理页面切换时候的事务)
        viewPager.setAdapter(new SecondViewPageAdapter(getChildFragmentManager(), picFragmentList));

    }

    private void initData() {
        tag.setText(viewPageEntity.getTag());
        pic.setImageResource(viewPageEntity.getPicImg());

        picFragmentList = Lists.newArrayList();

        SecondPicFragmentExt fragment1 = new SecondPicFragmentExt();
        SecondPicFragmentExt fragment2 = new SecondPicFragmentExt();
        SecondPicFragmentExt fragment3 = new SecondPicFragmentExt();
        SecondPicFragmentExt fragment4 = new SecondPicFragmentExt();


        Bundle bundle = new Bundle();
        bundle.putParcelable("picEntity", new ViewPageEntity(R.mipmap.common_icon_black_back, "第一张图", 0, false));
        fragment1.setArguments(bundle);
        picFragmentList.add(fragment1);

        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("picEntity", new ViewPageEntity(R.mipmap.common_icon_right_arrow, "第二张图", 1, false));
        fragment2.setArguments(bundle2);
        picFragmentList.add(fragment2);

        Bundle bundle3 = new Bundle();
        bundle3.putParcelable("picEntity", new ViewPageEntity(R.mipmap.img, "第三张图", 2, false));
        fragment3.setArguments(bundle3);
        picFragmentList.add(fragment3);

        Bundle bundle4 = new Bundle();
        bundle4.putParcelable("picEntity", new ViewPageEntity(R.mipmap.firstpic, "第四张图", 3, false));
        fragment4.setArguments(bundle4);
        picFragmentList.add(fragment4);

    }

    private void init() {
        tag = mView.findViewById(R.id.second_fragment_text);
        pic = mView.findViewById(R.id.second_fragment_pic);



        mGestureDetector = new GestureDetector(getActivity(), new LearnGestureListener());
        //为fragment添加OnTouchListener监听器
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.second_view_pager_fragment, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            viewPageEntity = bundle.getParcelable("picEntity");
        }
        return mView;
    }


    public static class LearnGestureListener extends GestureDetector.SimpleOnGestureListener {
        private int verticalMinistance = 100;            //水平最小识别距离
        private int minVelocity = 10;           //最小识别速度

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
                Log.i("sout","向左滑动");
            } else if (e2.getX() - e1.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
                Log.i("sout","向下滑动");
            } else if (e1.getY() - e2.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
                Log.i("sout","向上滑动");
            } else if (e2.getY() - e1.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
                Log.i("sout","向下滑动");
            }
            return false;
        }

        //此方法必须重写且返回真，否则onFling不起效
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

}

