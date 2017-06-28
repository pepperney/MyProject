package cn.com.learn.io.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import org.junit.Test;

public class UDP_NIO {

	@Test
	public void sender() throws IOException {
		DatagramChannel dc = DatagramChannel.open();
		dc.configureBlocking(false);
		String str = "< sender > : hello , I am client!";// 向服务端发送的数据
		ByteBuffer buffer = ByteBuffer.allocate(str.length());// 分配缓冲区大小
		buffer.put(str.getBytes());// 将数据放入缓冲区
		buffer.flip();// 切换缓冲区模式
		dc.send(buffer, new InetSocketAddress("127.0.0.1", 9898));
		buffer.clear();// 清空缓冲区
		dc.close();// 关闭通道

	}

	@Test
	public void receiver() throws IOException {
		DatagramChannel dc = DatagramChannel.open();
		dc.configureBlocking(false);
		dc.bind(new InetSocketAddress(9898));
		Selector selector = Selector.open();
		dc.register(selector, SelectionKey.OP_READ);
		while (selector.select() > 0) {
			for (SelectionKey key : selector.selectedKeys()) {
				if (key.isReadable()) {
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					dc.receive(buffer);
					buffer.flip();
					System.out.println(new String(buffer.array(), 0, buffer.limit()));
					buffer.clear();
				}
			}
			selector.selectedKeys().clear();
		}
	}
}
