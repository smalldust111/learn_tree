package com.me.practice.t0006_produceConsumer.type2;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

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
//		new Main().f1();
	}
	public void f1() {
		Set<Integer> set = Sets.newConcurrentHashSet();
		set.add(11);
		set.add(22);
//		set.stream().forEach(setstr -> System.out.println(setstr));
		Set<Integer> set2 = Sets.newConcurrentHashSet();
		set2.add(33);
		set2.add(22);
		Stream<Integer> concat = Stream.concat(set.stream(), set2.stream());
		Set<Integer> set3 = concat.collect(Collectors.toSet());
		set3.stream().filter(param->param<50).limit(4).forEach(System.out::println);
//		concat.forEach(System.out::println);
	}
}
