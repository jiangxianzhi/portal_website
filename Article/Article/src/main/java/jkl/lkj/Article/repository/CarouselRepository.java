package jkl.lkj.Article.repository;


import jkl.lkj.Article.entity.CarouselPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
public interface CarouselRepository extends JpaRepository<CarouselPicture,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE CarouselPicture c SET c.status = :status WHERE c.id = :id")
    void changeStatusById(@Param("id") Long id, @Param("status") int status);


}
