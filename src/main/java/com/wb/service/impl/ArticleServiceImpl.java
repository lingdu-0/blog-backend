package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.common.Constant;
import com.wb.entity.Article;
import com.wb.service.ArticleService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {


    @Override
    public Article findById(Integer id) {
        return articleMapper.findById(id);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除文章");
        return articleMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除文章");
        return articleMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int insert(Article article) {
        logService.insertOne(servletContext, session, "增加文章");
        return articleMapper.insert(article);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int update(Article article) {
        logService.insertOne(servletContext, session, "修改文章");
        return articleMapper.update(article);
    }

    @Override
    public List<Article> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.selectAll();
        return list;
    }

    @Override
    public List<Article> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return articleMapper.fuzzyQuery(data);
    }

    @Override
    public List<Article> findByTypeId(Integer pageNum, Integer articleTypeId) {
        if (pageNum == null){
            pageNum = 1;
        }
        if (articleTypeId == 0) {
            PageHelper.startPage(pageNum, Constant.ARTICLE_PAGE_SIZE);
            return articleMapper.selectAll();
        }
        PageHelper.startPage(pageNum, Constant.ARTICLE_PAGE_SIZE);
        return articleMapper.selectByTypeId(articleTypeId);
    }
}
