package com.example.yfr.list.test;

public class User {
    private String name;
    private String say;
    private int img;
    public User(){
    }
    public User(String name, String say, int img) {
        this.name = name;
        this.say = say;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
