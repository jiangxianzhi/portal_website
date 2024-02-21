package jkl.lkj.Article.controller;
import jkl.lkj.Article.service.FakeJWTVerify;
import jkl.lkj.Article.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jkl.lkj.Article.entity.Page;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/page")
public class pageController {
    //    验证密码， 通过token
    private final FakeJWTVerify fakeJWTVerify;

private final PageService pageService;

    @Autowired
    public pageController(PageService pageService, FakeJWTVerify fakeJWTVerify) {
        this.pageService = pageService;
        this.fakeJWTVerify = fakeJWTVerify;
    }

//    得到所有
    @GetMapping
    List<Page> getAllPage(){
        return pageService.getAllPage();
    }
    @GetMapping("/{id}")
    int deleteById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader){
        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }

//        判断http head 是否有 accessKey
        if(tmp){
            return pageService.deleteById(id);
        }else {
            return 0;
        }
    }
    @PostMapping
    int uploadArticle(@RequestBody Page page,
                      @RequestHeader("Authorization") String authorizationHeader) {

        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (tmp) {
            if (page.getId() != null) {
                //        有id的就是修改的
                return pageService.update(page);
            } else {
                return pageService.create(page.getParentId(),page.getTitle(), page.getContent());
            }

        } else {
            return 0;
        }


    }



}
