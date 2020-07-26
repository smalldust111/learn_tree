package com.sunyj.practice.t0007_rabbitMQ.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一、工作队列模式-生产者
 * @author syj
 * @date 2019年7月8日 下午7:08:39
 */
public class Producer {
	private static final String QUEUE = "helloworld";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		//1.创建连接
		Connection connection = factory.newConnection();
		//2.创建通道
		Channel channel = connection.createChannel();
		//3.声明队列
		channel.queueDeclare(QUEUE, true, false, false, null);
		//4.发布
		String message = "hello world test001";
		channel.basicPublish("", QUEUE, null, message.getBytes());
		System.out.println("Producer send message:"+message);
		channel.close();
		connection.close();
	}
}
