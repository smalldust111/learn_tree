package com.me.practice.t0006_produceConsumer.type1;


import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;

/**
 * @author syj
 * @date 2019年7月8日 下午4:14:40
 */
public class Consumer implements Runnable {
    public Object obj;
    public ArrayList<Integer> list;

    public Consumer(Object obj, ArrayList<Integer> list) {
        this.obj = obj;
        this.list = list;
    }

    public void consumer() throws InterruptedException {
        synchronized (obj) {
            while (CollectionUtils.isEmpty(list)) {
                System.out.println(Thread.currentThread().getName() + " waiting");
                obj.wait();
            }
            list.clear();
            System.out.println(Thread.currentThread().getName() + " consumering");
            obj.notifyAll();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
