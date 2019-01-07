package com.example.yfr.demo.entity;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午5:55 2018/12/26
 * @Modified_By:
 */
public class HotChannelEntity {
    private int headIcon;
    private String hotChannelName;
    private String hotChannelTag;
    private int followNum;

    public HotChannelEntity(){}

    public HotChannelEntity(int headIcon, String hotChannelName, String hotChannelTag, int followNum) {
        this.headIcon = headIcon;
        this.hotChannelName = hotChannelName;
        this.hotChannelTag = hotChannelTag;
        this.followNum = followNum;
    }

    public int getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(int headIcon) {
        this.headIcon = headIcon;
    }

    public String getHotChannelName() {
        return hotChannelName;
    }

    public void setHotChannelName(String hotChannelName) {
        this.hotChannelName = hotChannelName;
    }

    public String getHotChannelTag() {
        return hotChannelTag;
    }

    public void setHotChannelTag(String hotChannelTag) {
        this.hotChannelTag = hotChannelTag;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }
}
