package jkl.lkj.Article.controller;

import jkl.lkj.Article.entity.Article;
import jkl.lkj.Article.service.ArticleService;
import jkl.lkj.Article.service.FakeJWTVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    //    验证密码， 通过token
    private final FakeJWTVerify fakeJWTVerify;


    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService, FakeJWTVerify fakeJWTVerify) {
        this.articleService = articleService;
        this.fakeJWTVerify = fakeJWTVerify;
    }

    //返回所有文章
    @GetMapping
    List<Article> getAllArticle() {
        return articleService.getAllArticle();
    }

    //    删除通过id删除一篇文章
    @GetMapping("/{id}")
    int deleteById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader) {
        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }

//        判断http head 是否有 accessKey
        if(tmp){
            return articleService.deleteById(id);
        }else {
            return 0;
        }


    }

    //    增加或者修改一篇文章
    @PostMapping
    int uploadArticle(@RequestBody Article article,
                      @RequestHeader("Authorization") String authorizationHeader) {

        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (tmp) {
            if (article.getId() != null) {
                //        有id的就是修改的
                return articleService.update(article);
            } else {
                return articleService.create(article.getTitle(), article.getContent());
            }

        } else {
            return 0;
        }


    }


    //    修改一篇文章
    @PostMapping("/update")
    int updateArticle(@RequestBody Article article,  @RequestHeader("Authorization") String authorizationHeader) {
        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }
//         判断id不是空
        if (article.getId() != null&&tmp) {

            return articleService.update(article);
        } else {
            return 0;
        }


    }

}
