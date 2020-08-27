package com.wb.test;

import com.wb.service.ArticleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void lastOneArticle() {
//        List<Article> list = articleService.findAll(1,4);
//        for (Article a : list)
//            System.out.println(a);
    }
}
