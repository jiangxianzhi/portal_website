package jkl.lkj.Article.repository;

import jkl.lkj.Article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface ArticleRepository  extends JpaRepository<Article,Long> {






}


