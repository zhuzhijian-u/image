package com.zhuzhijian.image.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Image {
    private int id;
    private String name;
    private String url;
    private int categoryId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
