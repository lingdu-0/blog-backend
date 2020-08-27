package com.wb.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @ClassName UserVo
 * @Deacription TODO
 * @Author W
 * @Date 2020/3/22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private static final long serialVersionUID = 8677828480293110960L;
    //用户编号
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    //用户名
    @NotNull(message = "用户名不能为空！")
    @Pattern(regexp = "\\w+", message = "用户名只能为字母数字或者下划线！")
    private String userName;

    //密码
    @NotNull( message = "密码不能为空！")
    @Size(min = 8, max = 20, message = "密码长度不能低于8,不能超过20")
    private String password;
    //用户邮箱
    @NotNull(message = "邮箱不能为空！")
    @Email( message = "邮箱格式错误！")
    private String userEmail;
    //用户状态
    @NotNull(message = "用户状态不能为空！")
    private Integer userState;
    //用户权限
    @NotNull(message = "用户权限不能为空！")
    private Integer userLevel;

}