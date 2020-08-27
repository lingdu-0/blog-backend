package com.wb.mapper;

import com.wb.entity.Comment;
import com.wb.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 查询同一类别或者指定类别的评论
     *
     * @param typeId
     * @param id
     * @return
     */
    List<Comment> findByIds(@Param("typeId") Integer typeId, @Param("id") Integer id);
}