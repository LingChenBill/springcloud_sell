package com.lc.order.utils;

import java.util.Random;

/**
 * Key生成工具类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间戳 + 随机数
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
