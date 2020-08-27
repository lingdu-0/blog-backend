package com.wb.service.base;

import java.util.List;

public interface BaseService<T> {
    T findById(Integer id);

    int deleteById(Integer id);

    int deleteByIds(int[] ids);

    int insert(T t);

    int update(T t);

    List<T> findAll(Integer pageNum, Integer pageSize);

    List<T> fuzzyQuery(String data);
}
