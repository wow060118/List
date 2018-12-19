package com.example.yfr.list.rxjava.entity;

import java.io.Serializable;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:35 2018/12/18
 * @Modified_By:
 */
public class Avatar implements Serializable {
    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
