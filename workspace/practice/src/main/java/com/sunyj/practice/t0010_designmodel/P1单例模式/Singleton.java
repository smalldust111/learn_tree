package com.sunyj.practice.t0010_designmodel.P1单例模式;

/**
 * @author jia
 * @since 2020/7/22 22:15
 */
public class Singleton {
    // 使用volatile保证内存一致性
    private static volatile Singleton singleton = null;

    // 私有构造方法
    private Singleton() {

    }

    // 返回单例
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
