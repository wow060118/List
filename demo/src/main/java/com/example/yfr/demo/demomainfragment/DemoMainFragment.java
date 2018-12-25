package com.example.yfr.demo.demomainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yfr.demo.R;
import com.example.yfr.demo.channel.ChannelManagementActivity;
import com.example.yfr.demo.entity.MainPicEntity;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午4:24 2018/12/24
 * @Modified_By:
 */
public class DemoMainFragment extends Fragment {

    private View mView;

    //主页面信息
    private MainPicEntity mainPicEntity;

    //频道选择
    private ImageView moreChannel;

    //关注频道
    private ImageView followChannel;

    //评论
    private ImageView comment;

    //喜欢
    private ImageView liked;

    //个人信息
    private ImageView userInformation;

    //头像
    private ImageView headIcon;

    //名称
    private TextView userName;

    //主图片
    private ImageView mainPic;

    //更多
    private ImageView moreShare;

    @Override
    public void onActivityCreated(Bundle saveInstaceState) {
        super.onActivityCreated(saveInstaceState);
        init();
        initData();
        initEvent();
    }

    private void initEvent() {
        moreChannel.setOnClickListener(v->{
            Intent intent=new Intent(getContext(),ChannelManagementActivity.class);
            startActivity(intent);
        });
        followChannel.setOnClickListener(v->{});
        comment.setOnClickListener(v->{});
        liked.setOnClickListener(v->{});
        userInformation.setOnClickListener(v->{});
        headIcon.setOnClickListener(v->{});
        moreShare.setOnClickListener(v->{});
    }

    private void initData() {
        userName.setText(mainPicEntity.getUserName());
        mainPic.setImageResource(mainPicEntity.getMainPic());
        headIcon.setImageResource(mainPicEntity.getHeadIcon());
    }

    private void init() {
        moreChannel=mView.findViewById(R.id.more_channel);
        followChannel=mView.findViewById(R.id.follow_channel);
        comment=mView.findViewById(R.id.demo_comment);
        liked=mView.findViewById(R.id.pic_liked);
        userInformation =mView.findViewById(R.id.user_information);
        headIcon=mView.findViewById(R.id.head_pic);
        userName=mView.findViewById(R.id.username);
        mainPic = mView.findViewById(R.id.demo_main_pic);
        moreShare=mView.findViewById(R.id.share);

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
        mView = inflater.inflate(R.layout.demo_main_fragment, container, false);
//        Bundle bundle=getArguments();
//        if(bundle!=null){
//            mainPicEntity=bundle.getParcelable("mainEntity");
//        }
        return mView;
    }

    public void setMainPicEntity(MainPicEntity mainPicEntity){
        this.mainPicEntity=mainPicEntity;
    }

    public MainPicEntity getMainPicEntity() {
        return mainPicEntity;
    }
}
