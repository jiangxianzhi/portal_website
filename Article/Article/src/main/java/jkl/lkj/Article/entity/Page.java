package jkl.lkj.Article.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Data
@NoArgsConstructor
@Table //@Table来指定和哪个数据表对应;如果省略默认表名就类名；
public class Page{

    @Id
    @GeneratedValue
    private Long id;

    private Long parentId;

    private String title;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    //    通过传递三个值就可以实例化
    public Page(Long parentId,String title, String content) {
        this.parentId=parentId;
        this.title = title;
        this.content = content;
    }
}
