package com.wb.util;

import java.security.MessageDigest;

public class MD5Utils {

    public static String code(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int j = 0; j < bytes.length; j++) {
                i = bytes[j];
                if (i < 0)
                    i += 265;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("admin123"));
    }
}
