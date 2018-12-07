package com.example.yfr.list.db;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@org.greenrobot.greendao.annotation.Entity
public class Entity {
    @Id
    private Long id;
    private String name;
    private String age;
    @Generated(hash = 1993203835)
    public Entity(Long id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1559012531)
    public Entity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
