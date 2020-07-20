package com.me.practice.t0006_produceConsumer.type2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author syj
 * @date 2019年7月8日 下午4:07:48
 */
public class Producer implements Runnable {
	public Object object;
	public ArrayList<Integer> list; // 存放生产的数据
	public ReentrantLock reentrantLock = new ReentrantLock();
	public Condition condition = reentrantLock.newCondition();

	public Producer(Object obj, ArrayList<Integer> list) {
		this.object = obj;
		this.list = list;
	}

	public void producer() throws InterruptedException {
		reentrantLock.lock();
		while (list.size() >= 5) {
			System.out.println(Thread.currentThread().getName() + " waiting");
			condition.await();
		}
		int data = 9999;
		list.add(data);
		System.out.println(Thread.currentThread().getName() + " producing");
		condition.signalAll();
		reentrantLock.unlock();
	}


	@Override
	public void run() {
		while (true) {
			try {
				producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
