package com.sunyj.practice.t0006_produceConsumer.type2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author syj
 * @date 2019年7月8日 下午4:14:40
 */
public class Consumer implements Runnable {
	public Object obj;
	public ArrayList<Integer> list;
	public ReentrantLock reentrantLock = new ReentrantLock();
	public Condition condition = reentrantLock.newCondition();

	public Consumer(Object obj, ArrayList<Integer> list) {
		this.obj = obj;
		this.list = list;
	}

	public void consumer() throws InterruptedException {
		reentrantLock.lock();
		while (list.size() <= 0) {
			System.out.println(Thread.currentThread().getName() + " waiting");
			condition.await();
		}
		list.clear();
		System.out.println(Thread.currentThread().getName() + " consumering");
		condition.signalAll();
		reentrantLock.unlock();
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
