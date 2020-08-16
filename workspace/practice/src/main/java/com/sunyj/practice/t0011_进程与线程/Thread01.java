package com.sunyj.practice.t0011_进程与线程;

/**
 * @author jia
 * @since 2020-08-15 23:35
 */
public class Thread01 extends Thread {
    public static void main(String[] args) {
        Thread01 thread01 = new Thread01();
        thread01.start();
    }

    @Override
    public void run() {
        System.out.println("thread01运行。。。");
    }
}
