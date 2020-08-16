package com.sunyj.practice.t0011_进程与线程;

/**
 * @author jia
 * @since 2020-08-15 23:37
 */
public class Runnable01 implements Runnable {

    @Override
    public void run() {
        System.out.println("runnable01运行。。。");
    }

    public static void main(String[] args) {
        new Thread(new Runnable01()).start();
    }
}
