package com.sunyj.practice.t0001_socket.simple;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 * @author syj
 * @date 2019年5月15日 下午8:00:49
 */
public class Server {
	public static void main(String[] args) {
		int port = 9212;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("server一直等待客户端连接的到来。。。");
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();	//读取到内存中
			byte[] bytes = new byte[1024];
			StringBuilder sb = new StringBuilder();
			int len;
			while((len = inputStream.read(bytes)) != -1) {
				sb.append(new String(bytes,0,len,"UTF-8"));
			}
			System.out.println("get message from client:"+sb.toString());
			
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("hello client,I get the message.".getBytes("UTF-8"));
			outputStream.close();
			inputStream.close();
			socket.close();
			serverSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
