package com.example.yfr.demo.channel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfr.demo.R;
import com.example.yfr.demo.entity.HotChannelEntity;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.FLOOR;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午5:13 2018/12/26
 * @Modified_By:
 */
public class HotChannelAdapter extends RecyclerView.Adapter<HotChannelAdapter.HotChannelHolder> {
    private List<HotChannelEntity> channelList;

    @NonNull
    @Override
    public HotChannelHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_hot_channel, viewGroup, false);
        HotChannelHolder hotChannelHolder= new HotChannelHolder(itemView);
        return hotChannelHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotChannelHolder hotChannelHolder, int i) {
        HotChannelEntity channelEntity=channelList.get(i);
        hotChannelHolder.followButton.setOnClickListener(v->{
            int followNum=channelEntity.getFollowNum()+1;
            channelEntity.setFollowNum(followNum);
            hotChannelHolder.followDesc.setText(getDesc(followNum));
        });
        hotChannelHolder.headIcon.setImageResource(channelEntity.getHeadIcon());
        hotChannelHolder.hotChannelName.setText(channelEntity.getHotChannelName());
        hotChannelHolder.hotChannelTag.setText(channelEntity.getHotChannelTag());
        hotChannelHolder.followDesc.setText(getDesc(channelEntity.getFollowNum()));
    }

    public String getDesc(int num){
        BigDecimal followNum=BigDecimal.valueOf(num).divide(BigDecimal.valueOf(1000));
        if(followNum.compareTo(BigDecimal.valueOf(1.0))>0){
            return followNum.setScale(1,FLOOR)+"k关注";
        }
        return num+"关注";
    }
    @Override
    public int getItemCount() {
        return channelList.size();
    }

    public List<HotChannelEntity> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<HotChannelEntity> channelList) {
        this.channelList = channelList;
    }

    public class HotChannelHolder extends RecyclerView.ViewHolder{
        private ImageView headIcon;
        private TextView hotChannelName;
        private TextView hotChannelTag;
        private TextView followDesc;
        private TextView followButton;

        public HotChannelHolder(@NonNull View itemView) {
            super(itemView);
            headIcon = itemView.findViewById(R.id.hot_channel_head);
            hotChannelName= itemView.findViewById(R.id.hot_channel_name);
            hotChannelTag= itemView.findViewById(R.id.hot_channel_tag);
            followDesc=itemView.findViewById(R.id.hot_channel_desc);
            followButton=itemView.findViewById(R.id.hot_channel_button);
        }
    }
}
