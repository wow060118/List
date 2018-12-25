package com.example.yfr.demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:04 2018/12/24
 * @Modified_By:
 */
public class MainPicEntity implements Parcelable {
    private int mainPic;
    private int headIcon;
    private String userName;

    public MainPicEntity() {

    }

    public MainPicEntity(Parcel in) {
        this.mainPic =in.readInt();
        this.headIcon = in.readInt();
        this.userName = in.readString();
    }

    public MainPicEntity(int mainPic, int headIcon, String userName) {
        this.mainPic = mainPic;
        this.headIcon = headIcon;
        this.userName = userName;
    }

    public static final Creator<MainPicEntity> CREATOR = new Creator<MainPicEntity>() {
        @Override
        public MainPicEntity createFromParcel(Parcel in) {
            return new MainPicEntity(in);
        }

        @Override
        public MainPicEntity[] newArray(int size) {
            return new MainPicEntity[size];
        }
    };

    public int getMainPic() {
        return mainPic;
    }

    public void setMainPic(int mainPic) {
        this.mainPic = mainPic;
    }

    public int getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(int headIcon) {
        this.headIcon = headIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mainPic);
        dest.writeInt(headIcon);
        dest.writeString(userName);
    }
}
