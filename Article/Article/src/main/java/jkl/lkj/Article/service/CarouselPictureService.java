package jkl.lkj.Article.service;

import jkl.lkj.Article.entity.CarouselPicture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarouselPictureService {

    int create(MultipartFile file);

    /**
     *返回所有
     *
     */
    List<CarouselPicture> getAllCarouselPicture();

    /**
     * 根据Id删除
     *
     * @param Id
     */
    int deleteById(Long Id);
    /**
     * 根据Id改变status
     *
     *
     */
    int changeStatusById(Long id, int status);

}
