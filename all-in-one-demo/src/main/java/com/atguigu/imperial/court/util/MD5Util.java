package com.atguigu.imperial.court.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * description  针对明文字符串执行MD5加密
     * date         2022/3/27 16:57
     * @param       source
     * @return      java.lang.String
     */
    public static String encode (String source) {
        if (source == null || "".equals(source)) {
            throw new RuntimeException("用于加密的明文不可为空");
        }
        String algorithm = "md5";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] input = source.getBytes();
        byte[] output = messageDigest.digest(input);
        int signum = 1;
        //// 保证转换完的为正数 符号位为1
        BigInteger bigInteger = new BigInteger(signum, output);
        int radix = 16;
        String encoded = bigInteger.toString(radix).toUpperCase();
        return encoded;
    }

}
