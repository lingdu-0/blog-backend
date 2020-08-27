package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.Log;
import com.wb.entity.User;
import com.wb.service.LogService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
    @Override
    public Log findById(Integer id) {
        return logMapper.findById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return logMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        return logMapper.deleteByIds(ids);
    }

    @Override
    public int insert(Log log) {
        return logMapper.insert(log);
    }

    @Override
    public int update(Log log) {
        return logMapper.update(log);
    }

    @Override
    public List<Log> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return logMapper.selectAll();
    }

    @Override
    public List<Log> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return logMapper.fuzzyQuery(data);
    }

    @Override
    public int delete() {
        return logMapper.delete();
    }

    @Override
    public int insertOne(ServletContext application, HttpSession session, String operation) {
        Log log = new Log();
        User user = (User) session.getAttribute("user");
        log.setUser(user);
        String ip = (String) application.getAttribute("ip");
        log.setLogIp(ip);
        log.setOperation(operation);
        return logMapper.insert(log);
    }
}
