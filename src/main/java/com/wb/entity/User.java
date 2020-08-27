package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 5815973772784050353L;
    //用户编号
    private Integer userId;
    //用户名
    private String userName;
    //邮箱
    private String userEmail;
    //密码
    private String password;
    //用户级别
    private Integer userLevel;
    //用户状态
    private Integer userState;


}