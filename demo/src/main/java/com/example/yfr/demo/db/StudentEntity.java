package com.example.yfr.demo.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午4:53 2019/2/12
 * @Modified_By:
 */

@Entity
public class StudentEntity {
    @Id
    public long id;
    public String username;
    public String password;
}
