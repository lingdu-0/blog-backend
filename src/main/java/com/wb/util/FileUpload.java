package com.wb.util;

import com.wb.entity.FTPConfig;
import com.wb.vo.ReqVo;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FileUpload {
    private static final String IMG_PATH = "http://www.wbblog.top:9527/myblog/img/";

    /**
     * 生成新的文件名
     *
     * @param fileName 源文件名
     * @return
     */
    public static String getFileName(String fileName) {
        String toSuffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid + toSuffix;
    }

    /**
     * 文件上传
     *
     * @param ftpConfig
     * @param reqVo
     * @return
     */
    public static String upload(FTPConfig ftpConfig, ReqVo reqVo) {
        String filename = reqVo.getFile().getOriginalFilename();
        String filename2 = getFileName(filename);
        InputStream inputStream = null;
        try {
            inputStream = reqVo.getFile().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (FtpUtils.uploadFile(ftpConfig, filename2, inputStream)) {
            return IMG_PATH + filename2;
        } else {
            return "";
        }
    }
}
