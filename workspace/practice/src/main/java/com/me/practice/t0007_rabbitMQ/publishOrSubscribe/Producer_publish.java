package com.me.practice.t0007_rabbitMQ.publishOrSubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 二、发布/订阅模式-生产者
 * @author syj
 * @date 2019年7月8日 下午7:59:28
 */
public class Producer_publish {
	private static final String QUEUE_EMAIL = "publish/subscribe_email";
	private static final String QUEUE_SMS = "publish/subscribe_sms";
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
		channel.queueDeclare(QUEUE_EMAIL,true,false,false,null);
		channel.queueDeclare(QUEUE_SMS, true, false, false, null);
		
		//声明交换机
		channel.exchangeDeclare(EXCHANGE_FANOUT, BuiltinExchangeType.FANOUT);
		//交换机绑定队列
		channel.queueBind(QUEUE_EMAIL, EXCHANGE_FANOUT, "");
		channel.queueBind(QUEUE_SMS, EXCHANGE_FANOUT, "");
		
		for(int i=0;i<5;i++) {
			String message = "send inform to user";
			channel.basicPublish(EXCHANGE_FANOUT, "", null, message.getBytes());
			System.out.println("send message:"+message);
		}
		
		channel.close();
		connection.close();
	}
}
