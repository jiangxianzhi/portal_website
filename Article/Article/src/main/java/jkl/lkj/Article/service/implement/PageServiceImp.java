package jkl.lkj.Article.service.implement;

import jkl.lkj.Article.entity.Article;
import jkl.lkj.Article.repository.PageRepository;
import jkl.lkj.Article.service.PageService;
import org.springframework.stereotype.Service;
import jkl.lkj.Article.entity.Page;


import java.util.ArrayList;
import java.util.List;

@Service
public class PageServiceImp implements PageService {

    private final PageRepository pageRepository;

    public PageServiceImp(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public int create(Long parentId, String title, String content) {

        try {
            pageRepository.save(new Page(parentId, title, content));
            return 1;
        } catch (Exception e) {
            System.out.println("article upload successful but create failed" + e);
            return 0;
        }
    }

    @Override
    public List<Page> getAllPage() {
        try {
            return pageRepository.findAll();
        } catch (Exception e) {
            System.out.println("can not get all page ");
            return new ArrayList<>();
        }
    }

    @Override
    public int deleteById(Long Id) {
        try {
            pageRepository.deleteById(Id);
            return 1;
        } catch (Exception e) {
            System.out.println("delete article failed");
            return 0;
        }
    }

    @Override
    public int update(Page page) {
        try {
            pageRepository.save(page);
            return 1;
        } catch (Exception e) {
            System.out.println("article update failed");
            return 0;
        }
    }


}
