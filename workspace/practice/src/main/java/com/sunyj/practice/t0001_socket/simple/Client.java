package com.sunyj.practice.t0001_socket.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author syj
 * @date 2019年5月15日 下午8:24:18
 */
public class Client {
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 9212;
		try {
			Socket socket = new Socket(host,port);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("你好".getBytes("utf-8"));
			//通过shutdownoutput来告诉服务器已经发送完数据，后续只能接受数据
			socket.shutdownOutput();
			
			InputStream inputStream = socket.getInputStream();
			byte[] bytes = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while((len = inputStream.read(bytes)) != -1) {
				sb.append(new String(bytes,0,len,"UTF-8"));
			}
			System.out.println("get message from server:"+sb.toString());
			inputStream.close();
			outputStream.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
