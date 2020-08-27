package com.wb.service;

import com.wb.entity.Article;
import com.wb.service.base.BaseService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleService extends BaseService<Article> {
    List<Article> findByTypeId(Integer pageNum, Integer articleTypeId);
}
