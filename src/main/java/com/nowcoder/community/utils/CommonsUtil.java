package com.nowcoder.community.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author mary
 * @version 1.1
 * @love
 */
public class CommonsUtil {

    // 生成无-的UUID字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String encrypt(String plaintext) {
        return DigestUtils.md5DigestAsHex(plaintext.getBytes());
    }
}
