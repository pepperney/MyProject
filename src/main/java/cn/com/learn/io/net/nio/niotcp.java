package cn.com.learn.io.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.junit.Test;

public class niotcp {

	// 缓冲区的长度
	private final int BUFSIZE = 256;
	// select方法等待通道准备好的最长时间
	private final int TIMEOUT = 3000;

	@Test
	public void client() throws IOException {
		byte[] argument = "hello,I am client".getBytes();

		SocketChannel clientChannel = SocketChannel.open();// 创建一个通道
		clientChannel.configureBlocking(false);// 设为非阻塞模式
		// 向服务端发起连接请求
		if (!clientChannel.connect(new InetSocketAddress("127.0.0.1", 2000))) {
			// 不断地轮询连接状态，直到完成连接
			while (!clientChannel.finishConnect()) {
				// 在等待连接的时间里，可以执行其他任务，以充分发挥非阻塞IO的异步特性,这里为了演示该方法的使用，只是一直打印"."
				System.out.print("ccc");
			}
		}
		// 为了与后面打印的"."区别开来，这里输出换行符
		System.out.print("\n");
		// 分别实例化用来读写的缓冲区
		ByteBuffer writeBuf = ByteBuffer.wrap(argument);
		ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
		// 接收到的总的字节数
		int totalBytesRcvd = 0;
		// 每一次调用read（）方法接收到的字节数
		int bytesRcvd;
		// 循环执行，直到接收到的字节数与发送的字符串的字节数相等
		while (totalBytesRcvd < argument.length) {
			// 如果用来向通道中写数据的缓冲区中还有剩余的字节，则继续将数据写入通道
			if (writeBuf.hasRemaining()) {
				clientChannel.write(writeBuf);
			}
			// 如果read（）接收到-1，表明服务端关闭，抛出异常
			if ((bytesRcvd = clientChannel.read(readBuf)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			// 计算接收到的总字节数
			totalBytesRcvd += bytesRcvd;
			// 在等待通信完成的过程中，程序可以执行其他任务，以体现非阻塞IO的异步特性,这里为了演示该方法的使用，同样只是一直打印"-"
			System.out.print("ccc");
		}
		// 打印出接收到的数据
		System.out.println();
		System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));
		// 关闭通道
		clientChannel.close();
	}

	@Test
	public void server() throws IOException {

		Selector selector = Selector.open();// 创建一个选择器
		ServerSocketChannel serverChannel = ServerSocketChannel.open();// 实例化一个通道
		serverChannel.socket().bind(new InetSocketAddress(2000));// 将该通道绑定到指定端口
		serverChannel.configureBlocking(false);// 配置通道为非阻塞模式
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);// 将选择器注册到各个通道
		// 不断轮询select方法，获取准备好的通道所关联的Key集
		while (true) {// 一直等待,直至有通道准备好了I/O操作
			if (selector.select(TIMEOUT) == 0) {// 在等待通道准备的同时，也可以异步地执行其他任务，这里只是简单地打印"."
				System.out.print("s");
				continue;
			}
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();// 获取准备好的通道所关联的Key集合的iterator实例
			while (it.hasNext()) {// 循环取得集合中的每个键值
				SelectionKey key = it.next();
				if (key.isAcceptable()) {// 如果服务端通道感兴趣的I/O操作为accept
					SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
					clientChannel.configureBlocking(false);
					// 将选择器注册到连接到的客户端通道，并指定该通道key值的属性为OP_READ，同时为该通道指定关联的附件
					clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUFSIZE));
				} else if (key.isReadable()) {// 如果客户端通道感兴趣的I/O操作为read
					SocketChannel clientChannel = (SocketChannel) key.channel();
					ByteBuffer buf = (ByteBuffer) key.attachment();// 获取该通道所关联的附件，这里为缓冲区
					long bytesRead = clientChannel.read(buf);
					// 如果read()方法返回-1，说明客户端关闭了连接，那么客户端已经接收到了与自己发送字节数相等的数据，可以安全地关闭
					if (bytesRead == -1) {
						clientChannel.close();
					} else if (bytesRead > 0) {// 如果缓冲区读入了数据，则将该通道感兴趣的操作设置为为可读可写
						key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					}
				} else if (key.isValid() && key.isWritable()) {// 如果该键值有效，并且其对应的客户端通道感兴趣的I/O操作为write
					ByteBuffer buf = (ByteBuffer) key.attachment();// 获取与该通道关联的缓冲区，里面有之前读取到的数据
					buf.flip();// 重置缓冲区，准备将数据写入通道
					SocketChannel clientChannel = (SocketChannel) key.channel();
					clientChannel.write(buf);// 将数据写入到通道中
					if (!buf.hasRemaining()) {// 如果缓冲区中的数据已经全部写入了通道，则将该通道感兴趣的操作设置为可读
						key.interestOps(SelectionKey.OP_READ);
					}
					buf.compact();// 为读入更多的数据腾出空间
				}
				it.remove();// 这里需要手动从键集中移除当前的key
			}
		}
	}

}
