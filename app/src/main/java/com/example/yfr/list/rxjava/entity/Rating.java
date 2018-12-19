package com.example.yfr.list.rxjava.entity;

import java.io.Serializable;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:31 2018/12/18
 * @Modified_By:
 */
public class Rating implements Serializable {
    private int max;
    private double average;
    private int stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
