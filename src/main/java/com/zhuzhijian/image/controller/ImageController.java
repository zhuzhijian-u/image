package com.zhuzhijian.image.controller;


import com.zhuzhijian.image.dto.ImageDto;
import com.zhuzhijian.image.entity.Category;
import com.zhuzhijian.image.entity.Image;
import com.zhuzhijian.image.service.FileUploadService;
import com.zhuzhijian.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/image")
@RestController
@CrossOrigin
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private FileUploadService fileUploadService;
    //查找所有图片
    @RequestMapping("/findall")
    public ImageDto findAllImage(){

        ImageDto imageDto = new ImageDto();
        List<Image> images = imageService.findAll();
        if (images!=null){
            imageDto.setData(images);
            imageDto.setStatus(200);
            imageDto.setMassage("查找成功");
            return imageDto;
        }
        imageDto.setStatus(500);
        imageDto.setMassage("系统繁忙");
        return imageDto;
    }
    //根据分类id查找图片
    @RequestMapping("/findbycategoryid")
    public ImageDto findByCategoryId(int id){
        ImageDto imageDto = new ImageDto();
        List<Image> images = imageService.find(id);

        if (images!=null){
            imageDto.setData(images);
            imageDto.setStatus(200);
            imageDto.setMassage("查找成功");
            return imageDto;
        }
        imageDto.setStatus(500);
        imageDto.setMassage("系统繁忙");
        return imageDto;
    }
    //添加图片
    @PostMapping("/add")
    public ImageDto addImage( MultipartFile file)throws IOException {
        System.out.println(file);
        ImageDto imageDto = new ImageDto();
//        String fileName = UUID.randomUUID().toString();
//        String url = fileUploadService.upload(fileName, "image/", file.getInputStream());
//        Image image = new Image();
//        image.setCategoryId(4);
//        image.setUrl(url);
//        if (imageService.addImage(image)==1){
//            imageDto.setStatus(200);
//            imageDto.setMassage("增加成功");
//            return imageDto;
//        }
//        imageDto.setStatus(500);
//        imageDto.setMassage("系统繁忙");
        return imageDto;
    }
    //修改图片
    @RequestMapping("/update")
    public ImageDto updateImage(Image image){
        ImageDto imageDto = new ImageDto();
        if (imageService.updateImage(image)==1){
            imageDto.setStatus(200);
            imageDto.setMassage("修改成功");
            return imageDto;
        }
        imageDto.setStatus(500);
        imageDto.setMassage("系统繁忙");
        return imageDto;
    }
    //根据图片ID删除图片
    @RequestMapping("/delete")
    public ImageDto deleteImageById(int id){
        ImageDto imageDto = new ImageDto();
        if (imageService.deleteImageById(id)==1){
            imageDto.setStatus(200);
            imageDto.setMassage("删除成功");
            return imageDto;
        }
        imageDto.setStatus(500);
        imageDto.setMassage("系统繁忙");
        return imageDto;
    }
    //查询所有分类
    @RequestMapping("/findCategory")
    public ImageDto findCategory(){
        ImageDto imageDto = new ImageDto();
        List<Category> categorys = imageService.findCategory();
        if (categorys!=null){
            imageDto.setData(categorys);
            imageDto.setStatus(200);
            imageDto.setMassage("查询成功");
            return imageDto;
        }
        imageDto.setStatus(500);
        imageDto.setMassage("系统繁忙");
        return imageDto;
    }
    @PostMapping("/filesUpload")
    public String filesUpload (@RequestParam("myfiles") MultipartFile[] files,
                              HttpServletRequest request)throws IOException {
        System.out.println("filesUpload");
        List<String> list = new ArrayList<String>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];

                fileUploadService.upload("file"+i, "image/", file.getInputStream());
            }
        }
        return "";//跳转的页面
    }


}
