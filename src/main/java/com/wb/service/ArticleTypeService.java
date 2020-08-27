package com.wb.service;

import com.wb.entity.ArticleType;
import com.wb.service.base.BaseService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleTypeService extends BaseService<ArticleType> {
    List<ArticleType> findAll();

}
