package com.sunyj.practice.t0011_进程与线程;

import java.util.concurrent.Callable;

/**
 * @author jia
 * @since 2020-08-15 23:39
 */
public class Callable01 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("callable01运行。。。");
        return "success";
    }

    public static void main(String[] args) {
        Callable01 callable01 = new Callable01();
        try {
            String call = callable01.call();
            System.out.println(call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
