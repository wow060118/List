package com.example.yfr.list.entity;

import android.os.Parcelable;

import com.example.yfr.list.Enum.AdviceEnum;

public class AdviceEntity  {
    private AdviceEnum adviceEnum;
    private String advice;
    private String contact;
    private String version;
    private String phoneModle;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPhoneModle() {
        return phoneModle;
    }

    public void setPhoneModle(String phoneModle) {
        this.phoneModle = phoneModle;
    }

    public AdviceEntity() {
    }

    public AdviceEntity(AdviceEnum adviceEnum, String advice, String contact) {
        this.adviceEnum = adviceEnum;
        this.advice = advice;
        this.contact = contact;
    }

    public AdviceEnum getAdviceEnum() {
        return adviceEnum;
    }

    public void setAdviceEnum(AdviceEnum adviceEnum) {
        this.adviceEnum = adviceEnum;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
