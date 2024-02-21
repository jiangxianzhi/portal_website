package jkl.lkj.Article.PictureExplode;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//直接把本地图片映射到相应URL上
//客户端访问http:// ../img/{{imageName}} 就可以访问
@CrossOrigin
@Configuration
public class PictureExplode implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        映射此文件夹下所有文件
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + System.getProperty("user.dir")+"/src/main/resources/static/assets/images/" );
        registry.addResourceHandler("/carouselImages/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/static/assets/carouselImages/");
    }
}
