package com.zhuzhijian.image.service;

import com.zhuzhijian.image.entity.Category;
import com.zhuzhijian.image.entity.Image;

import java.util.List;

public interface ImageService {
    //查找所有图片
    List<Image> findAll ();
    //根据分类查找图片
    List<Image> find (int categoryId);
    //添加图片
    int addImage(Image image);
    //根据修改图片
    int updateImage(Image image);
    //删除图片
    int deleteImageById(int id);
    //查询所有分类
    List<Category> findCategory();
}
