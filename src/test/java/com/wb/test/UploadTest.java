package com.wb.test;

import org.junit.Test;

public class UploadTest {
    private static final String IMG_PATH = "http://www.lingdu.live:9527/myblog/img";
//    @Autowired
//    private FTP ftp;
//    @Autowired
//    private MessageProperties messageProperties;

    @Test
    public void upload() {
        String str = "http://www.lingdu.live:9527/myblog/img/1e292ec036cf4e60a450a577f88a0002.jpg";
        int index = str.lastIndexOf("/");
        System.out.println(str.substring(index+1));
    }

}
