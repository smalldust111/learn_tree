package com.me.practice.t0007_rabbitMQ.publishOrSubscribe;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 二、发布/订阅模式-消费者
 * @author syj
 * @date 2019年7月8日 下午8:17:17
 */
public class Consumer_subscribe_email {
	private static final String QUEUE_EMAIL = "publish/subscribe_email";
	private static final String EXCHANGE_FANOUT = "exchange_fanout";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_FANOUT, BuiltinExchangeType.FANOUT);
		channel.queueDeclare(QUEUE_EMAIL,true,false,false,null);
		
		channel.queueBind(QUEUE_EMAIL, EXCHANGE_FANOUT, "");
		channel.basicConsume(QUEUE_EMAIL, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body,"UTF-8");
				System.out.println("receive message:"+msg);
			}
		});
	}
}
