package cn.com.learn.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 本程序使用NIO完成阻塞式网络通信： 客户端读取本地文件发送到服务端，服务端接收到消息后给客户端发送反馈信息
 * 
 *  阻塞与非阻塞是相对于网络IO而言的，所以FileChannel不能切换为非阻塞式
 * 
 * @Description
 * @author niepei
 * @date 2017年5月10日 下午5:26:55
 * @version V1.3.1
 */
public class NIO_Block {

	// 客户端--读取本地文件发送给服务端
	@Test
	public void client() throws IOException {
		// 1. 获取通道
		SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

		// 2. 分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 3. 读取本地文件，并发送到服务端
		FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\XiaoShou\\Desktop\\dream.txt"),
				StandardOpenOption.READ);

		while (fileChannel.read(buffer) != -1) {
			buffer.flip();
			clientChannel.write(buffer);
			buffer.clear();
		}

		// 告诉服务端数据已经全部发送完成--如果没有这行代码，程序将一直阻塞无法结束，因为服务端不知道客户端什么有没有发送完成
		clientChannel.shutdownOutput();

		// 4.接收服务端的反馈
		int length = 0;
		while ((length = clientChannel.read(buffer)) != -1) {
			buffer.flip();
			System.out.println(new String(buffer.array(), 0, length));
			buffer.clear();
		}

		// 5. 关闭通道
		fileChannel.close();
		clientChannel.close();
	}

	// 服务端--接收到客户端的消息后返回反馈信息
	@Test
	public void server() throws IOException {
		// 1. 获取通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();

		// 2. 绑定连接
		serverChannel.bind(new InetSocketAddress(9898));

		// 3. 获取客户端连接的通道
		SocketChannel clientChannel = serverChannel.accept();

		// 4. 分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 5. 接收客户端的数据，并保存到本地
		FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\XiaoShou\\Desktop\\dream.txt"),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		while (clientChannel.read(buffer) != -1) {
			buffer.flip();
			fileChannel.write(buffer);
			buffer.clear();
		}

		// 6.发送反馈给客户端
		buffer.put("--------------------> server has receive file succeed".getBytes());
		buffer.flip();
		clientChannel.write(buffer);

		// 7. 关闭通道
		clientChannel.close();
		fileChannel.close();
		serverChannel.close();

	}

}
