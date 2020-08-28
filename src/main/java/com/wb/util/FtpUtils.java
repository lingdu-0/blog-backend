package com.wb.util;

import com.wb.entity.FtpConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * 正在使用
 */
public class FtpUtils {

    private static final Log logger = LogFactory.getLog(FtpUtils.class);

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(FtpConfig ftpConfig, String filename, InputStream input) {
        FTPClient ftp = new FTPClient();
        try {
            if (connectFtp(ftpConfig, ftp)) {
                return false;
            }
            // 设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();// 这个设置允许被动连接--访问远程ftp时需要
            // 上传文件
            if (!ftp.storeFile(filename, input)) {
                logger.error("文件上传失败！");
                return false;
            }
            input.close();
            ftp.logout();
        } catch (IOException e) {
            logger.error("文件上传异常" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("ftp关闭异常:" + ioe.getMessage());
                }
            }
        }
        return true;
    }

    private static boolean connectFtp(FtpConfig ftpConfig, FTPClient ftp) throws IOException {
        int reply;
        // 连接FTP服务器
        // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
        ftp.connect(ftpConfig.getHost(), ftpConfig.getPort());
        // 登录
        ftp.login(ftpConfig.getUsername(), ftpConfig.getPassword());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return true;
        }
        // 切换到上传目录
        if (!ftp.changeWorkingDirectory(ftpConfig.getBasePath() + ftpConfig.getFilePath())) {
            // 如果目录不存在创建目录
            String[] dirs = ftpConfig.getFilePath().split("/");
            String tempPath = ftpConfig.getBasePath();
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) {
                    continue;
                }
                tempPath += "/" + dir;
                if (!ftp.changeWorkingDirectory(tempPath)) {
                    if (!ftp.makeDirectory(tempPath)) {
                        return true;
                    } else {
                        ftp.changeWorkingDirectory(tempPath);
                    }
                }
            }
        }
        return false;
    }

    public static boolean deleteFile(FtpConfig ftpConfig, String filename) {
        FTPClient ftp = new FTPClient();
        boolean result;
        try {
            connectFtp(ftpConfig, ftp);
            result = ftp.deleteFile(filename);
        } catch (IOException e) {
            logger.error("ftp操作:" + e);
            return false;
        }
        return result;
    }
}