package com.me.practice.t0009_javabase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 原始值：a=1, b=b, c=值引用.Vo(c=c)
 * 方法内：a=2, b=update, c=值引用.Vo(c=update)
 * 修改结果：a=1, b=b, c=值引用.Vo(c=update)
 *
 * @author jia
 * @since 2020/7/2 23:50
 */
@Slf4j
public class 值引用 {
    public static void main(String[] args) {
        int a = 1;
        String b = "b";
        Vo c = new Vo("c");
        System.out.println("原始值：a=" + a + ", b=" + b + ", c=" + c);
        update(1, "1", c);
        System.out.println("修改结果：a=" + a + ", b=" + b + ", c=" + c);
    }

    public static void update(int a, String b, Vo c) {
        a++;
        b = "update";
        c.setC("update");
        System.out.println("方法内：a=" + a + ", b=" + b + ", c=" + c);
    }

    @Data
    @AllArgsConstructor
    public static class Vo {
        String c;
    }
}
