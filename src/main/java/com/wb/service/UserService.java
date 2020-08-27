package com.wb.service;

import com.wb.entity.User;
import com.wb.service.base.BaseService;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserService extends BaseService<User> {

    User login(String userName, String password);

    int findByUserEmail(String userEmail);
    int findByUserName(String userName);
}
