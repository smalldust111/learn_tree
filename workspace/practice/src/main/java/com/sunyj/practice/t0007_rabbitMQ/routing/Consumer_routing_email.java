package com.sunyj.practice.t0007_rabbitMQ.routing;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 三、路由模式-消费者
 * @author syj
 * @date 2019年7月8日 下午8:17:17
 */
public class Consumer_routing_email {
	private static final String QUEUE_EMAIL = "routing_email";
	private static final String EXCHANGE_DIRECT = "exchange_direct";
	private static final String ROUTING_KEY_EMAIL = "routing_key_email";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setVirtualHost("/");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_DIRECT, BuiltinExchangeType.DIRECT);
		channel.queueDeclare(QUEUE_EMAIL,true,false,false,null);
		
		channel.queueBind(QUEUE_EMAIL, EXCHANGE_DIRECT, ROUTING_KEY_EMAIL);
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
