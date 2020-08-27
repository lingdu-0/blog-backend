package com.wb.service;

import com.wb.entity.Comment;
import com.wb.service.base.BaseService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentService extends BaseService<Comment> {
    List<Comment> findByTypeId(Integer typeId, Integer id, Integer pageSize);
}
