package com.example.yfr.list.rxjava.entity;

import java.io.Serializable;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:54 2018/12/18
 * @Modified_By:
 */
public class Image implements Serializable {
    private String smalll;
    private String large;
    private String medium;

    public String getSmalll() {
        return smalll;
    }

    public void setSmalll(String smalll) {
        this.smalll = smalll;
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
