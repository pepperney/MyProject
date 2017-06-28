package cn.com.learn.readNetty.P2_BIO_OPTIMIZED;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * 伪异步IO--通过线程池改进同步io的问题--每新建一个客户端到服务端的连接都需要new一个新的线程处理
 * 
 * @Description
 * @author niepei
 * @date 2017年5月8日 上午9:09:31
 * @version V1.3.1
 */
public class Server {

	public static final Integer PORT = 10010;

	public static void main(String[] args) {
		ServerSocket server = null;
		System.out.println("--------------------------> the time server start at " + PORT);
		try {
			server = new ServerSocket(PORT);
			Socket socket = null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);//创建IO线程池
			while (true) {
				socket = server.accept();
				singleExecutor.execute(new TimeServerHandler(socket));//将任务放入线程池等待调度执行
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				System.out.println("--------------------------> The time server is closed");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server = null;

			}
		}
	}

}
