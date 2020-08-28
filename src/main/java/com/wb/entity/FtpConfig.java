package com.wb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * fpt配置类
 */
@Component
@ConfigurationProperties(prefix = "ftp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FtpConfig {
    // FTP服务器hostname
    private String host;
    //FTP端口号
    private Integer port;
    //FTP登录账号
    private String username;
    //FTP登录密码
    private String password;
    //FTP服务器基础目录
    private String basePath;
    //FTP服务器文件存放路径
    private String filePath;

}
