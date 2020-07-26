package com.sunyj.practice.t0006_produceConsumer.type1;

import java.util.ArrayList;

/**
 * 
 * @author syj
 * @date 2019年7月8日 下午4:21:26
 */
public class Main {
	public static void main(String[] args) {
		Object obj = new Object();
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<2; i++) {
			Thread producer = new Thread(new Producer(obj,list));
			producer.setName("生产者["+i+"]");
			producer.start();
			Thread consumer = new Thread(new Consumer(obj,list));
			consumer.setName("消费者["+i+"]");
			consumer.start();
		}
	}
}
