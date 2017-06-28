package cn.com.learn.io.net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class TCP_BIO {

	
	@Test
	public void server() throws IOException {
		// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = new ServerSocket(9000);
		// 2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		// 3、获取输入流，并读取客户端信息
		InputStream in = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String infoFromClient = null;
		while ((infoFromClient = br.readLine()) != null) {
			System.out.println("【来自客户端的消息】：" + infoFromClient);
		}
		// 关闭输入流
		socket.shutdownInput();
		// 4、获取输出流，响应客户端的请求
		OutputStream out = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(out);
		pw.write("hello客户端");
		pw.flush();
		// 5、关闭资源
		pw.close();
		out.close();
		br.close();
		in.close();
		socket.close();
		serverSocket.close();
	}

	
	@Test
	public void client() throws IOException {
		// 1、创建客户端Socket，指定服务器地址和端口
		Socket socket = new Socket("127.0.0.1", 9000);
		// 2、获取输出流，向服务器端发送信息
		OutputStream os = socket.getOutputStream();// 字节输出流
		PrintWriter pw = new PrintWriter(os);// 将输出流包装成打印流
		pw.write("hello服务器");
		pw.flush();
		socket.shutdownOutput();
		// 3、获取输入流，并读取服务器端的响应信息
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("【来自服务器的消息】：" + info);
		}
		// 4、关闭资源
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}
}
