package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.Essay;
import com.wb.service.EssayService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EssayServiceImpl extends BaseServiceImpl<Essay> implements EssayService {
    @Override
    public Essay findById(Integer id) {
        return essayMapper.findById(id);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除随笔");
        return essayMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除随笔");
        return essayMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int insert(Essay essay) {
        logService.insertOne(servletContext, session, "新增随笔");
        return essayMapper.insert(essay);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int update(Essay essay) {
        logService.insertOne(servletContext, session, "修改随笔");
        return essayMapper.update(essay);
    }

    @Override
    public List<Essay> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        return essayMapper.selectAll();
    }

    @Override
    public List<Essay> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return essayMapper.fuzzyQuery(data);
    }
}
