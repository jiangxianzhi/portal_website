package jkl.lkj.Article.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Data
@NoArgsConstructor
@Table //@Table来指定和哪个数据表对应;如果省略默认表名就类名；
public class CarouselPicture {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
//使用状态
    private int status;

    //    通过传递两个值就可以实例化 CarouselPicture
    public CarouselPicture(String name, int status) {
        this.name = name;
        this.status = status;
    }
}




