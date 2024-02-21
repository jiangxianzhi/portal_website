package jkl.lkj.Article.service.implement;

import jkl.lkj.Article.entity.CarouselPicture;
import jkl.lkj.Article.repository.CarouselRepository;
import jkl.lkj.Article.service.CarouselPictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//不知道为什么要添加service
@Service
public class CarouselPictureImp implements CarouselPictureService {
    private final CarouselRepository carouselRepository;

    public CarouselPictureImp(CarouselRepository carouselRepository) {
        this.carouselRepository = carouselRepository;
    }

    @Override
    public int create(MultipartFile file) {


        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1);
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\carouselImages\\";
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
//                文件储存到指定地址
            file.transferTo(dest);
            System.out.println("store carousel successful");
//         ，文件名+路径储存在mysql
            carouselRepository.save(new CarouselPicture(fileName, 0));
            return 1;
        } catch (Exception e) {
            System.out.println("store carousel meet error");
            return 0;
        }


    }

    @Override
    public List<CarouselPicture> getAllCarouselPicture() {
        try {
            return carouselRepository.findAll();
        } catch (Exception e) {
            System.out.println("can not get all carousel ");
            return new ArrayList<>();
//         返回空
        }
    }

    @Override
    public int deleteById(Long Id) {
        try {
//            本地文件中删除
            Optional<CarouselPicture> carouselPicture = carouselRepository.findById(Id);
//          System.out.println(carouselPicture);
            String name = carouselPicture.map(CarouselPicture::getName).orElse(null);
//            System.out.println(name);
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\carouselImages\\";
//
            if (name != null) {
                File fileToDelete = new File(path + name);
                if (fileToDelete.exists()) {
                    if (fileToDelete.delete()) {
                        System.out.println("File deleted successfully.");
                    } else {
                        System.out.println("Failed to delete the file.");
                        return 0;
                    }
                } else {
                    System.out.println("File does not exist at the specified path.");
                    return 0;
                }
            } else {
                System.out.println("Name is null, cannot delete file.");
                return 0;
            }
//            数据库中删除
            carouselRepository.deleteById(Id);
            System.out.println("delete by id carousel successful");
            return 1;

        } catch (Exception e) {
            System.out.println("delete by id carousel failed");
            return 0;
        }
    }

    @Override
    public int changeStatusById(Long id, int status) {
        try {
//            System.out.println(id);
//            System.out.println(status);
//            jpa方法问题
            carouselRepository.changeStatusById(id, status);
            System.out.println("change status by id carousel success");
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("change status by id carousel failed");
            return 0;
        }
    }
}
