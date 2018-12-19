package com.example.yfr.list.rxjava.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:34 2018/12/18
 * @Modified_By:
 */
public class Cast  implements Serializable {
    private String alt;
    private List<Avatar> avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public List<Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
