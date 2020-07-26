package com.sunyj.practice.t0003_nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Nio服务器
 * @author syj
 * @date 2019年5月28日 上午10:16:49
 */
public class NioServer {
	private static final int port = 10038;
	private Selector selector;
	
	/**
	 * 获得一个ServerSocket通道，并对该通道进行一些初始化的处理
	 * @param port
	 * @throws IOException
	 */
	public void initServer() throws IOException {
		//获得一个serverSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		//设置通道为非阻塞
		serverChannel.configureBlocking(false);
		//将端口绑定到通道对应的ServerSocket
		serverChannel.socket().bind(new InetSocketAddress(port));
		//获得一个通道管理器
		this.selector = Selector.open();
		//将通道管理器与该通道绑定，同时为该通道注册SelectionKey.OP_ACCEPT事件，注册该事件后，
		//当该事件到达时，selector.select()会返回，如果该事件没到达selector.open(),则会一直阻塞
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	public void listen() throws IOException {
		System.out.println("服务端启动成功");
		//轮询访问Selector
		while(true) {
			//当注册的事件发生时返回，否则一直阻塞
			selector.select();
			//获取selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()) {
				SelectionKey selectionKey = it.next();
				//删除已选的key，以防重复处理
				it.remove();
				//客户端请求连接事件
				if(selectionKey.isAcceptable()) {
					ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
					//获取和客户端连接的通道
					SocketChannel socketChannel = serverChannel.accept();
					//设置成非阻塞
					socketChannel.configureBlocking(false);
					
					//在这里写向客户端发送的消息
					socketChannel.write(ByteBuffer.wrap("向客户端发送了一条消息.".getBytes()));
					//在和客户端建立连接成功后，为了可以接受到客户端的信息，需要给通道设置读的权限
					socketChannel.register(this.selector,SelectionKey.OP_READ);
					
					//获取了可读的事件
				} else if(selectionKey.isReadable()) {
					read(selectionKey);
				}
			}
		}
	}

	/**
	 * 读取客户端发来信息
	 * @param selectionKey
	 * @throws IOException 
	 */
	private void read(SelectionKey selectionKey) throws IOException {
		//服务器可读取消息，得到事件发生的socket通道
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		int read = socketChannel.read(byteBuffer);
		if(read != -1) {
			String msg = new String(byteBuffer.array());
			System.out.println("服务端接收到消息："+msg);
			
			//将消息回送给客户端
			String respMsg = "服务器回复："+msg;
			socketChannel.write(ByteBuffer.wrap(respMsg.getBytes()));
		} else {
			System.out.println("服务器关闭");
			socketChannel.close();
		}
	}
	public static void main(String[] args) throws IOException {
		NioServer server = new NioServer();
		server.initServer();
		server.listen();
	}
}
