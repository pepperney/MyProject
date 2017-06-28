package cn.com.learn.readNetty.P1_BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞式IO
 * 
 * @Description
 * @author niepei
 * @date 2017年5月5日 下午4:41:32
 * @version V1.3.1
 */
public class Server {
	
	public static final Integer PORT = 10010; 

	public static void main(String[] args) throws InterruptedException {
		ServerSocket server = null;
		System.out.println("--------------------------> the time server start at " + PORT);
		try {
			while (true) {
				Socket socket = null;
				server = new ServerSocket(PORT);
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (server != null) {
					System.out.println("--------------------------> the time server close");
					server.close();
					server = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}


