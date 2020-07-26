package com.sunyj.practice.t0006_produceConsumer.type1;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * 
 * @author syj
 * @date 2019年7月8日 下午4:07:48
 */
public class Producer implements Runnable {
	public Object object;
	public ArrayList<Integer> list;	//存放生产的数据
	
	public Producer(Object obj,ArrayList<Integer> list) {
		this.object = obj;
		this.list = list;
	}
	public void producer() throws InterruptedException {
		synchronized(object) {
			while(!CollectionUtils.isEmpty(list)) {
				System.out.println(Thread.currentThread().getName()+" waiting");
				object.wait();
			}
			int data = 9999;
			list.add(data);
			System.out.println(Thread.currentThread().getName()+" producing");
			object.notifyAll();
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
