package com.chenyi.mall.common.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author chenyi
 * @className ChenYiMallUtils
 * @date 2022/7/29 22:39
 */
public class ChenYiMallUtils {
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result.append(random.nextInt(10));
        }
        return newDate + result;
    }

}
