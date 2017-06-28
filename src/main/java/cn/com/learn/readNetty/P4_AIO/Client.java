package cn.com.learn.readNetty.P4_AIO;

public class Client {
	
	public static final Integer PORT = 10010;

	public static void main(String[] args) {
		new Thread(new TimeClient("127.0.0.1", PORT), "AIO-TimeClient-001").start();
	}
}
