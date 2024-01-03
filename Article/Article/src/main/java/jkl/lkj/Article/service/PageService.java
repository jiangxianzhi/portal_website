package jkl.lkj.Article.service;


import jkl.lkj.Article.entity.Page;

import java.util.List;

public interface PageService {
    /**
     * 新增一篇文章
     *
     * @param title
     * @param content
     */
    int create(Long parentId,String title, String content);

    /**
     *返回所有文章
     *
     */
    List<Page> getAllPage();

    /**
     * 根据Id删除文章
     *
     * @param Id
     */
    int deleteById(Long Id);

    /**
     * 根据Id修改
     *
     * @param page
     */
    int update(Page page);



}
