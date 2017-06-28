package cn.com.learn.readNetty.P3_NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;


/**
 * 负责轮询多路复用选择器Selector，可以处理多个客户端的并发接入
 * @Description 
 * @author niepei
 * @date 2017年5月8日 下午3:31:36 
 * @version V1.3.1
 */
public class TimeServer implements Runnable {

	private Selector selector;

	private ServerSocketChannel serverChannel;

	private volatile boolean stop;

	/**
	 * 初始化Selector，绑定监听端口
	 * Creates a new instance of TimeServer.
	 * Description 
	 * @param port
	 */
	public TimeServer(int port) {
		
		try {
			serverChannel = ServerSocketChannel.open();// 打开通道
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);// 绑定监听端口
			serverChannel.configureBlocking(false);// 设置为非阻塞
			selector = Selector.open();// 创建选择器
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);// 将ServerSocketChannel注册到Selector上，并监听ACCEPT事件
			System.out.println("--------------------------> the time server start at port : " + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		this.stop = true;
	}


	@Override
	public void run() {

		try {
			// 多路复用选择器Selector在线程run方法的无限循环体内轮询准备就绪的key
			while (!stop) {
				// 无论是否有读写事件发生，Selector没隔1s被唤醒一次，返回就绪状态的Channel的SelectionKey集合
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					handleInput(key);
					if (key != null) {
						key.cancel();
						if (key.channel() != null) {
							key.channel().close();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Selector关闭后，所有注册在上面的Channel和Pipe资源都会自动注册去关闭，所以不需要重复释放资源
		if(selector!=null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			// 处理新接入的消息
			if (key.isAcceptable()) {
				ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
				// Selector监听到有新的客户端接入，处理新的接入请求，完成tcp三次握手，建立物理链路
				SocketChannel socketChannel = serverChannel.accept();
				// 设置客户端为非阻塞模式
				socketChannel.configureBlocking(false);
				// 将新接入的客户端注册到Selector上，监听读操作，用来读取客户端发送的网络消息
				socketChannel.register(selector, SelectionKey.OP_READ);
			}
			if (key.isReadable()) {
				SocketChannel socketChannel = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				// 异步读取客户端请求消息到缓冲区
				int readBytes = socketChannel.read(buffer);
				if (readBytes > 0) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					buffer.get(bytes);
					// 对消息进行编解码
					String body = new String(bytes, "UTF-8");
					System.out.println("the time server receive order from client : " + body);
					String currentTime = "QUERY TIME　ORDER".equalsIgnoreCase(body)
							? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
					// 调用SocketChannel的异步write接口，将消息异步发送给客户端
					doWrite(socketChannel, currentTime);
				} else if (readBytes < 0) {
					// 对端链路关闭
					key.cancel();
					socketChannel.close();
				} else {
					;// 读到0字节 忽略
				}
			}
		}
	}

	

	public void doWrite(SocketChannel socketChannel, String resp) throws IOException {
		if (resp != null && resp.trim().length() > 0) {
			byte[] bytes = resp.getBytes();
			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			buffer.put(bytes);
			buffer.flip();
			socketChannel.write(buffer);
		}
	}

}
