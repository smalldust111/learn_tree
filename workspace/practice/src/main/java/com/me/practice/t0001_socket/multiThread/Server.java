package com.me.practice.t0001_socket.multiThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程方式循环处理多个socket请求-server
 * @author syj
 * @date 2019年5月15日 下午9:52:16
 */
public class Server {
	public static void main(String[] args) {
		int port = 9212;
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
//			serverSocket.setSoTimeout();
			System.out.println("server一直等待client连接的到来...");
			ExecutorService executorService = Executors.newFixedThreadPool(100);
			while(true) {
				Socket socket = serverSocket.accept();
				Runnable runnable = () -> {
					readMsg(socket);
				};
				executorService.submit(runnable);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 读取socket传来的信息
	 * @param socket
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private static void readMsg(Socket socket){
		try {
			InputStream inputStream = socket.getInputStream();
			byte[] bytes = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while((len = inputStream.read(bytes)) != -1) {
				sb.append(new String(bytes,0,len,"utf-8"));
			}
			System.out.println(sb.toString());
			inputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
