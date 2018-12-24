package com.example.yfr.demo.demomain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfr.demo.R;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午4:24 2018/12/24
 * @Modified_By:
 */
public class DemoMainFragment extends Fragment {


    private View mView;
    private TextView tag;
    private ImageView pic;


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
        tag=mView.findViewById(R.id.);
        pic=mView.findViewById(R.id.);
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
}
