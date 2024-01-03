package jkl.lkj.Article.controller;


import jkl.lkj.Article.entity.ImageData;
import jkl.lkj.Article.entity.ImageResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PictureUploadController {
    @PostMapping("/PictureUpload")
//   上传接口
    @ResponseBody
    public ImageResponse uploadFile(@RequestParam("picture") MultipartFile file){
        if(file.isEmpty()){
            ImageData data = new ImageData(" ", " ", " ");
            return new ImageResponse(1, data);
        }
//        文件重命名
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
//        写入resources,获取更目录路径+相对路径 = 绝对路径
//        路径先这样，最后部署的时候再说，我想放在前端容易读取到的地方（浏览器不能阅读本地文件，用服务器tomcat什么生成链接）
        System.out.println("文件目录前缀"+System.getProperty("user.dir"));
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\assets\\images\\";
        System.out.println("文件目录是"+path);
        File dest = new File(path+fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
//            返回对象形式
//            {
//    "errno": 0, // 注意：值是数字，不能是字符串
//    "data": {
//        "url": "xxx", // 图片 src ，必须
//        "alt": "yyy", // 图片描述文字，非必须
//        "href": "zzz" // 图片的链接，非必须
//    }
//}

            ImageData data = new ImageData("http://localhost:8080/img/"+fileName, "yyy", "");
            System.out.println( new ImageResponse(0, data));
            return new ImageResponse(0, data);

        }catch (Exception e){
            e.printStackTrace();
            ImageData data = new ImageData(" ", " ", " ");
            return new ImageResponse(1, data);
        }
    }

    }

