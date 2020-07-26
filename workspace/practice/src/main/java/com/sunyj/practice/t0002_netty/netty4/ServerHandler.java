package com.sunyj.practice.t0002_netty.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author syj
 * @date 2019年5月20日 下午11:29:17
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

	//读取数据
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		simpleRead(ctx,msg);
		//有分隔符处理信息
//		delimiterRead(ctx,msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("服务器读取数据完毕");
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("server 读取数据出现异常："+cause.getMessage());
		ctx.close();
	}


	private void simpleRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
		ByteBuf byteBuf = (ByteBuf) msg;
		//创建一个和buf等长的byte数组
		byte[] reqBytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(reqBytes);
		String reqStr = new String(reqBytes,"UTF-8");
		System.out.println("server 接收到客户端的请求："+reqStr);
		
		String respStr = new StringBuilder("来自服务器的响应:").append(reqStr).toString();
		//返回给客户端响应
		ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));//.addListener(new ChannelFutureListener.CLOSE);
		//有了写操作，就不用释放msg
		ReferenceCountUtil.release(msg);
	}
	@SuppressWarnings("unused")
	private void delimiterRead(ChannelHandlerContext ctx, Object msg) {
		//如果把msg直接转成String，需要在服务中心加 socketChannel.pipeline().addLast(new StringDecoder());
		String reqStr = (String)msg;
		System.out.println("server接收到请求信息是："+reqStr);
		String respStr = new StringBuilder("来自服务器的响应：").append(reqStr).toString();
		ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));
	}
	
}
