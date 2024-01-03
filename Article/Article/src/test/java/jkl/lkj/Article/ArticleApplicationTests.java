package jkl.lkj.Article;

import jkl.lkj.Article.controller.ArticleController;
import jkl.lkj.Article.entity.Article;
import jkl.lkj.Article.repository.ArticleRepository;
import jkl.lkj.Article.service.ArticleService;
import jkl.lkj.Article.service.implement.ArticleServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleApplicationTests {

    ArticleApplicationTests() {
    }

    @Test
    void contextLoads() {
    }

    @Autowired
    private ArticleRepository articleRepository;

//    测试
//    @Test
//    void test() throws Exception {
//
//        for (var i = 0; i < 20; i++) {
//            articleRepository.save(new Article("aa" + i, "this is aa" + i));
//
//        }
//        Assert.assertEquals(20, articleRepository.findAll().size());
//        System.out.println(articleRepository.findAll());
//        System.out.println(articleRepository.findById(4L));
//    }

    @Autowired
    private ArticleServiceImp articleServiceImp;
//测试service
//    @Test
//    @Transactional
//    @Rollback
//    void testArticleServiceImp() {
//        var k = 3;
//        while (k > 0) {
//            articleServiceImp.create("aa" + k, "bb" + k);
//            k--;
//        }
//
//        Assert.assertEquals(13, articleServiceImp.getAllArticle().size());
//        System.out.println(articleServiceImp.getAllArticle());
//
////        删除所有单数
//        k =3;
//       while(k>0){
//           articleServiceImp.deleteById((long)k);
//           k=k-2;
//       }
//        Assert.assertEquals(1, articleServiceImp.getAllArticle().size());
//for(var i = 0;i<20;i++){
//    Article a = new Article("aa"+i,"cc"+i);
//    articleServiceImp.update(a);
//
//}
//Assert.assertEquals(30,articleServiceImp.getAllArticle().size());
//
//
//    }
@Autowired
    private MockMvc mvc;



//测试接口

//    @Test
//    public void testArtilceController() throws Exception{
//        RequestBuilder request;
//
//      request = post("/article/update").contentType(MediaType.APPLICATION_JSON).content("{ \"id\":253 ,   \"title\":\"googood\",\"content\":\"<h1>adc</h1>\"}");
//
//
//        mvc.perform(request)
//                .andExpect((status().isOk()))
//                .andExpect(content().string(equalTo("1")));
//        request = get("/article");
////打印结果
//        MvcResult result = mvc.perform(request)
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//
//
//
//    }




}
