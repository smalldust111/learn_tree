package com.me.practice.t0007_rabbitMQ.workqueue;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一、工作队列模式-消费者
 * @author syj
 * @date 2019年7月8日 下午7:21:27
 */
public class Consumer {
	private static final String QUEUE = "helloworld";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE, true, false, false, null);
		channel.basicConsume(QUEUE, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
//				String exchange = envelope.getExchange();
				String message = new String(body,"UTF-8");
				System.out.println("receive message:"+message);
			}
		});
	}
}
