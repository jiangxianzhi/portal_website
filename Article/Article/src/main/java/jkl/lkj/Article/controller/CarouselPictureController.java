package jkl.lkj.Article.controller;

import jkl.lkj.Article.entity.CarouselPicture;

import jkl.lkj.Article.service.CarouselPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/carouselPicture")
public class CarouselPictureController {
    private final CarouselPictureService carouselPictureService;

    @Autowired
    public CarouselPictureController(CarouselPictureService carouselPictureService) {
        this.carouselPictureService = carouselPictureService;
    }


    //   上传接口
    @PostMapping("/PictureUpload")
    public int uploadPicture(@RequestParam(value = "image", required = false) MultipartFile file) {
        System.out.println("1");
//        收到照片
//       判断是不是空&& 判断是不是图像
        if (file.isEmpty() && !file.getContentType().startsWith("image")) {
            return 0;
        } else {
            return carouselPictureService.create(file);
        }
    }

//    查询所有
    @GetMapping
  public   List<CarouselPicture> getAllCarouselPicture(){
        return  carouselPictureService.getAllCarouselPicture();
    }
//    通过id删除
    @GetMapping("/{id}")
    int deleteById(@PathVariable("id") Long id){
        return carouselPictureService.deleteById(id);
    }
//    通过id改变status
@GetMapping("/changeStatusById/{id}")
    public int changeStatusById(@PathVariable("id") Long id, @RequestParam int status) {
    return carouselPictureService.changeStatusById(id,status);
}
}
