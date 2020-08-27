package com.wb.mapper;

import com.wb.entity.Article;
import com.wb.mapper.base.BaseMapper;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据类别编号查询相关文章
     *
     * @param articleTypeId 文章类别编号
     * @return
     */
    List<Article> selectByTypeId(Integer articleTypeId);
}