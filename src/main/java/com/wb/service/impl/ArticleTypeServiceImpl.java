package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.ArticleType;
import com.wb.service.ArticleTypeService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleTypeServiceImpl extends BaseServiceImpl<ArticleType> implements ArticleTypeService {

    @Override
    public ArticleType findById(Integer id) {
        return articleTypeMapper.findById(id);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除类别");
        return articleTypeMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除类别");
        return articleTypeMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int insert(ArticleType articleType) {
        logService.insertOne(servletContext, session, "新增类别");
        return articleTypeMapper.insert(articleType);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int update(ArticleType articleType) {
        logService.insertOne(servletContext, session, "修改类别");
        return articleTypeMapper.update(articleType);
    }

    @Override
    public List<ArticleType> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return articleTypeMapper.selectAll();
    }

    @Override
    public List<ArticleType> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return articleTypeMapper.fuzzyQuery(data);
    }

    public List<ArticleType> findAll() {
        return articleTypeMapper.selectAll();
    }

}
