package cn.com.learn.io.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

public class TCP_NIO {

	/**
	 * 服务端
	 * 
	 * @Description
	 * @author niepei
	 * @throws IOException
	 */
	@Test
	public void server() throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();// 打开一个通道
		Selector selector = Selector.open();// 打开一个选择器
		ssc.configureBlocking(false);// 设置为非阻塞模式
		ssc.register(selector, SelectionKey.OP_ACCEPT);// 注册选择器到通道,并设置感兴趣的事件为连接事件
		ssc.bind(new InetSocketAddress(9000));// 绑定连接
		while (selector.select() > 0) {// 轮询式的获取选择器上已经“准备就绪”的事件
			for (SelectionKey key : selector.selectedKeys()) {
				if (key.isAcceptable()) { // 若“接收就绪”，则获取客户端连接
					SocketChannel channel = ssc.accept();
					channel.configureBlocking(false);
					channel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {// 获取当前选择器上“读就绪”状态的通道
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					while (channel.read(buffer) > 0) {
						buffer.flip();
						System.out.println("【received message from client】  : " + new String(buffer.array(), 0, buffer.limit()));
						buffer.clear();
					}
				}
			}
			selector.selectedKeys().clear();// 移除已经处理的键
		}
		ssc.close();
	}

	/**
	 * 客户端
	 * 
	 * @Description
	 * @author niepei
	 * @throws IOException
	 */
	@Test
	public void client() throws IOException {
		SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));// 打开一个通道，并初始化到服务器的链接
		sc.configureBlocking(false);// 设置为非阻塞
		String str = "< client > : hello , I am client!";// 向服务端发送的数据
		ByteBuffer buffer = ByteBuffer.allocate(str.length());// 分配缓冲区大小
		buffer.put(str.getBytes());// 将数据放入缓冲区
		buffer.flip();// 切换缓冲区模式
		sc.write(buffer);// 从缓冲区读数据到通道
		buffer.clear();// 清空缓冲区x
		sc.close();// 关闭通道

	}

}
