package com.geekbang.java.jvm.c01;

import java.io.Serializable;

public class ByteCodeTest implements Serializable {

    /**
     * 求给定数字所包含的所有偶数之和
     *
     * @param number
     * @return
     */
    public static int evenSum(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("数字必须大于等于0");
        }

        int sum = 0;
        for (int i = 0; i < number; i = i + 2) {
            if (i % 2 == 0) { // 偶数
                sum += i;
            }
        }

        return sum;
    }
}
