package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.LeaveWord;
import com.wb.service.LeaveWordService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeaveWordServiceImpl extends BaseServiceImpl<LeaveWord> implements LeaveWordService {
    @Override
    public LeaveWord findById(Integer id) {
        return leaveWordMapper.findById(id);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除留言");
        return leaveWordMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除留言");
        return leaveWordMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int insert(LeaveWord leaveWord) {
        logService.insertOne(servletContext, session, "新增留言");
        return leaveWordMapper.insert(leaveWord);
    }

    @Override
    @Transactional
//    @CacheEvict
    public int update(LeaveWord leaveWord) {
        logService.insertOne(servletContext, session, "修改留言");
        return leaveWordMapper.update(leaveWord);
    }

    @Override
    public List<LeaveWord> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, pageSize);
        return leaveWordMapper.selectAll();
    }

    @Override
    public List<LeaveWord> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return leaveWordMapper.fuzzyQuery(data);
    }
}
