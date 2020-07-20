package com.me.practice.t0002_netty.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

/**
 * 
 * @author syj
 * @date 2019年5月20日 下午10:52:32
 */
public class ServerNetty {
	private int port;
	public ServerNetty(int port) {
		this.port = port;
	}
	/**
	 * 服务端启动
	 * @throws InterruptedException 
	 */
	public void start() throws InterruptedException {
		//用来接收连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//用来处理已经被接收的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		//nio的启动类
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		try {
			//配置nio的服务参数
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) //说明一个新的channle如何接收进来
					.option(ChannelOption.SO_BACKLOG, 128) //tcp最大缓存链接个数
					.childOption(ChannelOption.SO_KEEPALIVE, true) //保持链接
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel ch) throws Exception {
							//序列化对象的解码
							//ch.pipeline().addLast(new MarshallingDecoder());
							//序列化对象的编码
							//ch.pipeline().addLast(new MarshallingEncoder());
							//网络超时时间
							ch.pipeline().addLast(new ReadTimeoutHandler(10));
							//处理接收到的请求
							ch.pipeline().addLast(new ServerHandler()); //相当于过滤器，可以配置多个
							ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
						}
					});
			System.out.println("服务开启。。。。");
			//绑定端口，开始接受链接
			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			//开多个端口
//			ChannelFuture channelFuture2 = serverBootstrap.bind(20000).sync();
//			channelFuture2.channel().closeFuture().sync();
			//等待服务端口的关闭
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
}
