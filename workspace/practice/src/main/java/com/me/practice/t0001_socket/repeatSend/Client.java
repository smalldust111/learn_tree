package com.me.practice.t0001_socket.repeatSend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 可重复发送信息SOCKET - client
 * @author syj
 * @date 2019年5月15日 下午9:17:45
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException,IOException {
		String host = "127.0.0.1";
		int port = 9212;
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(host,port), 10000);
			OutputStream outputStream = socket.getOutputStream();
			String msg = "你好，12312sfsaf";
			byte[] bytes = msg.getBytes("utf-8");
			outputStream.write(bytes.length >> 8);
			System.out.println(bytes.length);
			System.out.println(bytes.length >> 8);
			outputStream.write(bytes.length);
			outputStream.write(msg.getBytes("utf-8"));
			outputStream.flush();
			
			msg = "second send message...";
			outputStream.write(bytes.length >> 8);
			outputStream.write(bytes.length);
			outputStream.write(msg.getBytes("utf-8"));
			outputStream.flush();
			
			msg = "第三次发送...";
			outputStream.write(bytes.length >> 8);
			outputStream.write(bytes.length);
			outputStream.write(msg.getBytes("utf-8"));
			outputStream.flush();
			
			outputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
