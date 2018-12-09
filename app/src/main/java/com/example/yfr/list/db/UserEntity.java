package com.example.yfr.list.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    @NotNull
    @Property(nameInDb = "user_name")
    private String userName;
    @NotNull
    private String passWord;
    @Index
    @NotNull
    private String userid;
    @Generated(hash = 284336377)
    public UserEntity(Long id, @NotNull String userName, @NotNull String passWord,
            @NotNull String userid) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.userid = userid;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

}
