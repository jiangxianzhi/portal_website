package jkl.lkj.Article.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Data
@NoArgsConstructor
@Table //@Table来指定和哪个数据表对应;如果省略默认表名就类名；
public class LeaveMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String ip;

    private String phone;

    private String message;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date;

    public LeaveMessage(String name, String email,String phone,String message,String ip){
        this.name=name;
        this.email=email;
        this.phone =phone;
        this.message = message;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        this.date = formattedDate;
        this.ip = ip;
    }

}