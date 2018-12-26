package com.example.yfr.demo.entity;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 上午11:07 2018/12/26
 * @Modified_By:
 */
public class CategoryEntity {
    private String category;

    public CategoryEntity(){}
    public CategoryEntity(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
