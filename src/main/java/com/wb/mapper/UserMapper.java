package com.wb.mapper;

import com.wb.entity.User;
import com.wb.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    User login(@Param("userName") String userName, @Param("password") String password);

    int findByUserEmail(@Param("userEmail") String userEmail);

    int findByUserName(@Param("userName") String userName);
}