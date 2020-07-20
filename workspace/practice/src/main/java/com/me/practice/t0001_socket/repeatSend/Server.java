package com.me.practice.t0001_socket.repeatSend;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 可重复发送的SOCKET
 * @author syj
 * @date 2019年5月15日 下午9:06:52
 */
public class Server {
	public static void main(String[] args) throws IOException {
		int port = 9212;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("-----server一直等待客户端连接的到来...");
			serverSocket.setSoTimeout(10000);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			byte[] bytes;
			while(true) {
				int first = inputStream.read();		//读取第一个字节
				if(first == -1) {
					break;
				}
				int second = inputStream.read();
				System.out.println(first << 8);
				int len = (first << 8) + second;
				System.out.println(len);
				bytes = new byte[len];
				inputStream.read(bytes, 0, len);
				System.out.println("------get message from client:"+new String(bytes,"utf-8"));
			}
			inputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
