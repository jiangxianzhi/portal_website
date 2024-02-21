package jkl.lkj.Article.service;

import jkl.lkj.Article.entity.Article;

import java.util.List;

public interface ArticleService {
    /**
     * 新增一篇文章
     *
     * @param title
     * @param content
     */
    int create(String title, String content);

    /**
     *返回所有文章
     *
     */
    List<Article> getAllArticle();

    /**
     * 根据Id删除文章
     *
     * @param Id
     */
    int deleteById(Long Id);

    /**
     * 根据Id修改文章
     *
     * @param article
     */
    int update(Article article);



}
