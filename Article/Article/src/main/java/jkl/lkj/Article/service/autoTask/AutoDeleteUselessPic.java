package jkl.lkj.Article.service.autoTask;

import jkl.lkj.Article.repository.ArticleRepository;
import jkl.lkj.Article.repository.PageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jkl.lkj.Article.entity.Page;
import jkl.lkj.Article.entity.Article;

@Component
public class AutoDeleteUselessPic {
    public AutoDeleteUselessPic(ArticleRepository articleRepository, PageRepository pageRepository
    ) {
        this.articleRepository = articleRepository;
        this.pageRepository = pageRepository;
    }

    private final ArticleRepository articleRepository;
    private final PageRepository pageRepository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //once per month at first day midnight
    @Scheduled(cron = "0 0 0 1 * ?")
    public void deleteUselessPic() {
//        引入图片名字
        File folder = new File("src/main/resources/static/assets/images");
        if (folder.isDirectory() && folder.list().length == 0) {
            return;
        }
        File[] files = folder.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        String[] picFilesName = fileNames.toArray(new String[0]);
//        在文章+page很多的时候会不会很消耗内存？
//        判断不在数据库表article+page.content 中
//        获取content
        List<Page> allPages = pageRepository.findAll();
        List<Article> allArticles = articleRepository.findAll();

        List<String> allContents = allArticles.stream()
                .map(Article::getContent)
                .collect(Collectors.toList());

        List<String> pageContents = allPages.stream()
                .map(Page::getContent)
                .collect(Collectors.toList());

        for (String picName : picFilesName) {
            boolean isPresent = pageContents.stream()
                    .anyMatch(content -> content.contains(picName));
            boolean isPresent1 =
                    allContents.stream()
                            .anyMatch(content -> content.contains(picName));

//           都不在
            if (!(isPresent1 || isPresent)) {
                System.out.println("false part");
//                删除图片
                File fileToDelete = new File("src/main/resources/static/assets/images/" + picName);
                if (fileToDelete.delete()) {
                    System.out.println(picName + " has been deleted successfully.");
                } else {
                    System.out.println("Failed to delete " + picName);
                }
            }

        }

    }
}
