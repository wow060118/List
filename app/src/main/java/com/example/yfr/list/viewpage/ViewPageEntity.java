package com.example.yfr.list.viewpage;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewPageEntity implements Parcelable {
    private int picImg;
    private String tag;
    private int position;
    private boolean move;

    public ViewPageEntity(int picImg, String tag, int position, boolean move) {
        this.picImg = picImg;
        this.tag = tag;
        this.position = position;
        this.move = move;
    }

    protected ViewPageEntity(Parcel in) {
        picImg = in.readInt();
        tag = in.readString();
        position = in.readInt();
        move = in.readByte() != 0;
    }

    public int getPicImg() {
        return picImg;
    }

    public void setPicImg(int picImg) {
        this.picImg = picImg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag);
        dest.writeInt(this.picImg);
    }

    public static final Creator<ViewPageEntity> CREATOR = new Creator<ViewPageEntity>() {
        @Override
        public ViewPageEntity createFromParcel(Parcel in) {
            return new ViewPageEntity(in);
        }

        @Override
        public ViewPageEntity[] newArray(int size) {
            return new ViewPageEntity[size];
        }
    };

}


