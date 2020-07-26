package com.sunyj.practice.t0007_rabbitMQ.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 三、路由模式-生产者
 * @author syj
 * @date 2019年7月8日 下午7:59:28
 */
public class Producer_routing {
	private static final String QUEUE_EMAIL = "routing_email";
	private static final String QUEUE_SMS = "routing_sms";
	private static final String EXCHANGE_DIRECT = "exchange_direct";
	private static final String ROUTING_KEY_EMAIL = "routing_key_email";
	private static final String ROUTING_KEY_SMS = "routing_key_sms";
	
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
		channel.exchangeDeclare(EXCHANGE_DIRECT, BuiltinExchangeType.DIRECT);
		//交换机绑定队列
		channel.queueBind(QUEUE_EMAIL, EXCHANGE_DIRECT, ROUTING_KEY_EMAIL);
		channel.queueBind(QUEUE_SMS, EXCHANGE_DIRECT, ROUTING_KEY_SMS);
		
		for(int i=0;i<5;i++) {
			String message = "send email inform to user";
			channel.basicPublish(EXCHANGE_DIRECT, ROUTING_KEY_EMAIL, null, message.getBytes());
			System.out.println("send message:"+message);
		}
		for(int i=0;i<5;i++) {
			String message = "send sms inform to user";
			channel.basicPublish(EXCHANGE_DIRECT, ROUTING_KEY_SMS, null, message.getBytes());
			System.out.println("send message:"+message);
		}
		
		channel.close();
		connection.close();
	}
}
