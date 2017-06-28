package cn.com.learn.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 * 
 * 1. 通道（Channel）：负责连接
 * 		
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 
 * 2. 缓冲区（Buffer）：负责数据的存取
 * 
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 * 
 * 
 * 
 */
public class NIO_Non_Block {

	/**
	 * 客户端
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	@Test
	public void client() throws IOException {
		
		// 1. 获取通道
		SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		// 2. 切换非阻塞模式
		clientChannel.configureBlocking(false);
		
		// 3. 分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		// 4. 发送数据给服务端
		buffer.put(("-------------------> Now is : " + LocalDate.now().toString()).getBytes());
		buffer.flip();
		clientChannel.write(buffer);
		buffer.clear();
		
		// 5. 关闭通道
		clientChannel.close();
	}

	
	
	/**
	 * 服务端
	 * @Description 
	 * @author niepei
	 * @throws IOException
	 */
	@Test
	public void server() throws IOException {
		
		// 1. 获取通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		
		// 2. 切换非阻塞模式
		serverChannel.configureBlocking(false);
		
		// 3. 绑定监听的端口号
		serverChannel.bind(new InetSocketAddress(9898));
		
		// 4. 获取多路复用选择器
		Selector selector = Selector.open();
		
		// 5. 将通道注册到选择器上, 并且指定监听"接收"事件
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		// 6. 轮询式的获取选择器上已经"准备就绪"的事件
		while (selector.select() > 0) {
			
			// 7. 获取当前选择器中所有注册的"选择键(已就绪的监听事件)"
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> it = selectedKeys.iterator();
			while (it.hasNext()) {
				
				// 8. 获取准备"就绪"的是事件
				SelectionKey key = it.next();
				
				// 9. 判断具体是什么事件准备就绪
				if (key.isAcceptable()) {
					
					// 10. 若"接收就绪"，获取客户端连接
					SocketChannel clientChannel = serverChannel.accept();
					
					// 11. 切换非阻塞模式
					clientChannel.configureBlocking(false);
					
					// 12. 将该通道注册到选择器上,并监听"读"事件
					clientChannel.register(selector, SelectionKey.OP_READ);
					
				} else if (key.isReadable()) {
					
					// 13. 获取当前选择器上"读就绪"状态的通道
					SocketChannel clientChannel = (SocketChannel) key.channel();
					
					// 14. 读取数据
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					int length = 0;
					while ((length = clientChannel.read(buffer)) > 0) {
						buffer.flip();
						System.out.println(new String(buffer.array(), 0, length));
						buffer.clear();
					}
				}
				
				// 15. 取消选择键 SelectionKey
				it.remove();
			}
		}
	}
}
