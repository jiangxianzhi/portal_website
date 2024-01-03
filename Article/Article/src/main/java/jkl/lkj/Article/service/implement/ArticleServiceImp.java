package jkl.lkj.Article.service.implement;

import jkl.lkj.Article.entity.Article;
import jkl.lkj.Article.repository.ArticleRepository;
import jkl.lkj.Article.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//不知道为什么要添加service
@Service
public class ArticleServiceImp implements ArticleService {

    public ArticleServiceImp(ArticleRepository articleRepository){
        this.articleRepository= articleRepository;
    }

    private final ArticleRepository articleRepository;
    @Override
    public  int  create(String title, String content){
      try {
          System.out.println(content);
          articleRepository.save(new Article(title,content));
          return 1;
      }catch (Exception e){
          System.out.println("article upload successful but create failed"+e);
          return 0;
      }



    }

    @Override
    public List<Article> getAllArticle(){
     try {
         return  articleRepository.findAll();
     }catch (Exception e){
         System.out.println("can not get all article ");
         return new ArrayList<>();
//         返回空
     }
    }

    @Override
    public  int deleteById(Long Id){
        try{
            articleRepository.deleteById(Id);
            return 1;

        }catch (Exception e){
            System.out.println("delete article failed");
            return 0;
        }
    }

    @Override
    public  int update(Article article){
        try{
            articleRepository.save(article);
            return 1;
        }catch (Exception e){
            System.out.println("article update failed");
            return 0;
        }
    }
}
