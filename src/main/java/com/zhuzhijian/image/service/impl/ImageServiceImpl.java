package com.zhuzhijian.image.service.impl;


import com.zhuzhijian.image.entity.Category;
import com.zhuzhijian.image.entity.Image;
import com.zhuzhijian.image.mapper.ImageMapper;
import com.zhuzhijian.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;
    //查找所有图片
    @Override
    public List<Image> findAll() {
        return imageMapper.findAll();
    }
    //根据分类id查找图片
    @Override
    public List<Image> find(int categoryId) {
        return imageMapper.find(categoryId);
    }
    //增加图片
    @Override
    public int addImage(Image image) {
        return imageMapper.addImage(image)?1:-1;
    }
    //修改图片
    @Override
    public int updateImage(Image image) {
        return imageMapper.updateImage(image)?1:-1;
    }
    //删除图片
    @Override
    public int deleteImageById(int id) {
        return imageMapper.deleteImageById(id)?1:-1;
    }
    //查询所有分类
    @Override
    public List<Category> findCategory() {
        return imageMapper.findCategory();
    }
}
