package com.example.yfr.list.viewpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfr.list.R;

public class PicFragment extends Fragment {
    private ViewPageEntity viewPageEntity;
    private View mView;
    private TextView tag;
    private ImageView pic;
    private GestureDetector mGestureDetector;
    private int verticalMinistance = 100;            //水平最小识别距离
    private int minVelocity = 10;            //最小识别速度

    @Override
    public void onActivityCreated(Bundle saveInstaceState) {
        super.onActivityCreated(saveInstaceState);
        init();
        initData();
    }

    private void initData() {
        tag.setText(viewPageEntity.getTag());
        pic.setImageResource(viewPageEntity.getPicImg());
    }

    private void init() {
        tag=mView.findViewById(R.id.fragment_text);
        pic=mView.findViewById(R.id.fragment_pic);
//
//        mGestureDetector = new GestureDetector(getActivity(), new LearnGestureListener());
//        //为fragment添加OnTouchListener监听器
//        mView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return mGestureDetector.onTouchEvent(event);
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.view_pager_fragment, container, false);
        Bundle bundle=getArguments();
        if(bundle!=null){
            viewPageEntity=bundle.getParcelable("picEntity");
        }
        return mView;
    }
//
//    public static class LearnGestureListener extends GestureDetector.SimpleOnGestureListener {
//        private int verticalMinistance = 100;            //水平最小识别距离
//        private int minVelocity = 10;
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            if (e1.getX() - e2.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
//
//            } else if (e2.getX() - e1.getX() > verticalMinistance && Math.abs(velocityX) > minVelocity) {
//
//            } else if (e1.getY() - e2.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
//
//            } else if (e2.getY() - e1.getY() > verticalMinistance && Math.abs(velocityY) > minVelocity) {
//
//            }
//            return false;
//        }
//
//        //此方法必须重写且返回真，否则onFling不起效
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//    }
}
