package com.sunyj.practice.t0002_netty.netty3;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * netty3 服务端
 * @author syj
 * @date 2019年5月28日 下午2:20:55
 */
public class NettyServer {
	private static final int port = 10038;
	public static void main(String[] args) {
		//netty服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		//创建线程池
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();
		
		//设置nio socket工厂类
		bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
		//设置管道工厂类
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				//pipeline.addLast("nettyHandler", new NettyHandler());
				return pipeline;
			}
		});
		//绑定端口
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println("netty3启动。。。");
			
	}
}
