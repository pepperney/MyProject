package cn.com.learn.io.net.bio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

public class UDP_BIO {

	@Test
	public void sender() throws IOException {
		/*
		 * 向服务器端发送数据
		 */
		InetAddress address = InetAddress.getByName("localhost");// 1.定义服务器的地址、端口号、数据
		int port = 9000;
		byte[] data = "hello,I am client!".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);// 2.创建数据报，包含发送的数据信息
		DatagramSocket socket = new DatagramSocket();// 3.创建DatagramSocket对象
		socket.send(packet);// 4.向服务器端发送数据报

		/*
		 * 接收服务器端响应的数据
		 */
		byte[] data2 = new byte[1024]; // 1.创建数据报，用于接收服务器端响应的数据
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
		socket.receive(packet2);// 2.接收服务器响应的数据
		String reply = new String(data2, 0, packet2.getLength());// 3.读取数据
		System.out.println("我是客户端，服务器说：" + reply);
		socket.close();// 4.关闭资源

	}

	@Test
	public void receiver() throws IOException {
		/**
		 * 接收客户端的数据
		 */
		DatagramSocket socket = new DatagramSocket(9000);// 1.创建服务器端DatagramSocket，指定端口
		byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
		DatagramPacket packet = new DatagramPacket(data, data.length); // 2.创建数据报，用于接收客户端发送的数据
		System.out.println("****服务器端已经启动，等待客户端发送数据");
		socket.receive(packet);// 3.接收客户端发送的数据,此方法在接收到数据报之前会一直阻塞
		String info = new String(data, 0, packet.getLength());// 4.读取数据
		System.out.println("我是服务器，客户端说：" + info);

		/*
		 * 向客户端响应数据
		 */
		InetAddress address = packet.getAddress();// 1.定义客户端的地址、端口号、数据
		int port = packet.getPort();
		byte[] data2 = "欢迎您!".getBytes();
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);// 2.创建数据报，包含响应的数据信息
		socket.send(packet2); // 3.响应客户端
		socket.close();// 4.关闭资源
	}

}
