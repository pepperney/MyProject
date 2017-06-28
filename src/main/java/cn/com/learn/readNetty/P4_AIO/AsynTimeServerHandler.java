package cn.com.learn.readNetty.P4_AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsynTimeServerHandler implements Runnable {

	@SuppressWarnings("unused")
	private int port;
	
	private CountDownLatch latch;
	
	private AsynchronousServerSocketChannel serverChannel;

	public AsynTimeServerHandler(int port) {
		this.port = port;
		try {
			serverChannel = AsynchronousServerSocketChannel.open();// 创建一个异步的服务端通道
			serverChannel.bind(new InetSocketAddress(port));// 绑定监听端口
			System.out.println("--------------------------> the time server start at " + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 接收客户端的连接
	 * @Description 
	 * @author niepei
	 */
	private void doAccept() {
		serverChannel.accept(this,new CompletionHandler<AsynchronousSocketChannel, AsynTimeServerHandler>() {

			@Override
			public void completed(AsynchronousSocketChannel result, AsynTimeServerHandler attachment) {
				attachment.serverChannel.accept(attachment,this);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				result.read(buffer,buffer,new ReadCompletionHandler(result));
				
			}

			@Override
			public void failed(Throwable exc, AsynTimeServerHandler attachment) {
				exc.printStackTrace();
				attachment.latch.countDown();
				
			}
		});
	}

}
