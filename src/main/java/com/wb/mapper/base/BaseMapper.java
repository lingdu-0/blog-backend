package com.wb.mapper.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {

    T findById(Integer id);

    Integer deleteById(Integer id);

    Integer deleteByIds(@Param("ids") int[] ids);

    int insert(T entity);

    int update(T entity);

    List<T> selectAll();

    List<T> fuzzyQuery(String data);

}
