package com.wb.service.impl;

import com.github.pagehelper.PageHelper;
import com.wb.entity.User;
import com.wb.service.UserService;
import com.wb.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Override
    public User login(String userName, String password) {
        return userMapper.login(userName, password);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        logService.insertOne(servletContext, session, "通过ID删除用户");
        return userMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteByIds(int[] ids) {
        logService.insertOne(servletContext, session, "通过ID批量删除用户");
        return userMapper.deleteByIds(ids);
    }

    @Override
    @Transactional
    public int insert(User user) {
        logService.insertOne(servletContext, session, "新增用户");
        return userMapper.insert(user);
    }

    @Override
    @Transactional
    public int update(User user) {
        logService.insertOne(servletContext, session, "修改用户");
        return userMapper.update(user);
    }

    @Override
    public List<User> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAll();
    }

    @Override
    public List<User> fuzzyQuery(String data) {
        PageHelper.startPage(1, 10);
        return userMapper.fuzzyQuery(data);
    }
    @Override
    public int findByUserEmail(String userEmail) {
        return userMapper.findByUserEmail(userEmail);
    }

    @Override
    public int findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

}
